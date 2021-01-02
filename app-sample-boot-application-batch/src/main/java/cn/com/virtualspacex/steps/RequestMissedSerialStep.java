package cn.com.virtualspacex.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


import com.alibaba.fastjson.JSONObject;
import com.virtualspacex.annotation.AutoExceptionHandler;
import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.batch.step.AbstractStep;
import com.virtualspacex.batch.step.StepResult;
import com.virtualspacex.util.entity.NULL;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.exception.BatchExceptionHandler;
import cn.com.virtualspacex.model.SaleSerialMissed;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.service.SaleSerialMissedService;
import cn.com.virtualspacex.service.SystemRequestService;
import cn.com.virtualspacex.tasks.logger.Logger;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

public class RequestMissedSerialStep extends AbstractStep {

    @AutoLog
    private SaleSerialMissedService saleSerialMissedService;

    @AutoLog
    private SystemRequestService systemRequestService;

    @AutoExceptionHandler
    private BatchExceptionHandler batchExceptionHandler;

    //下发成功个数
    private AtomicInteger sentSuccessNumber = new AtomicInteger();

    //下发失败个数
    private AtomicInteger sentFailureNumber = new AtomicInteger();

    @Override
    public <T> StepResult<T> execute() {

        StepResult<T> stepResult = new StepResult<T>();

        //等待时间
        Long waitTimeMillis = Long.parseLong(PropertiesManager.RETRY_SECONDS) * 1000;
        int maxRetryTimes = Integer.parseInt(PropertiesManager.RETRY_TIME);

        //获取已生成的欠番数据
        ServiceResult<List<SaleSerialMissed>> listServiceResult = saleSerialMissedService.querySaleSerialMissed(null);
        if (!listServiceResult.result()) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SALE_SERIAL_MISSED_GET_FAILED);
            stepResult.setResult(false);
            return stepResult;
        }

        //判断有没有欠番数据
        if (listServiceResult.value().size() == 0) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SALE_SERIAL_MISSED_IS_EMPTY);
            stepResult.setResult(true);
            return stepResult;
        } else {
            Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.QUERY_SALESERIA_LMISSED) + String.format(Constant.QUERY_SUCCESS, listServiceResult.value().size()));
        }

        //登录认证获取token
        String token = systemRequestService.login();
        if (null == token || token.length() == 0) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.TOKEN_GET_FAILED);
            stepResult.setResult(false);
            return stepResult;
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(PropertiesManager.MAX_THREADS);
        ArrayList<Future<Boolean>> resultList = new ArrayList<>();

        //受信个数
        int receptionNumber = 0;
        //永久缺号个数
        int foreverNumber = 0;
        //重试次数
        int retryNumber = 0;

        //判断欠番表中的数据并执行下发流程
        //下发
        for (SaleSerialMissed saleSerialMissed : listServiceResult.value()) {

            //检查该欠番是否已经受信,已受信则更新
            ServiceResult<Integer> integerServiceResult = saleSerialMissedService.updateIfRecepted(null, saleSerialMissed);
            if (!integerServiceResult.result()) {
                Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.RECEPTION_CONFIRMATION_PROCESSING_FAILED);
                break;
            }

            //如果已受信，跳过该欠番
            if (integerServiceResult.value() > 0) {
                receptionNumber++;
                continue;
            }

            //获取当前已重试次数
            int retryTimes = Integer.parseInt(saleSerialMissed.getRetryTimes());
            if (maxRetryTimes <= retryTimes) {

                //超过重试次数,永久欠番
                ServiceResult<Integer> deleteNumber = saleSerialMissedService.updateAsMissedForever(saleSerialMissed, null);
                if (!deleteNumber.result()) {
                    Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MISSED_FOREVER_FAILED);
                    break;
                }
                foreverNumber++;
            } else {
                //没有超过重试次数,重试次数加一
                ServiceResult<Integer> updateRetryTimes = saleSerialMissedService.increaseRetryTimes(saleSerialMissed.getId(), null);
                if (!updateRetryTimes.result()) {
                    Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.RETRY_FREQUENCY_PLUS_FAILED);
                    break;
                }
                retryNumber++;
                resultList.add(threadPool.submit(new MissedSerialProcesser(token, saleSerialMissed)));
            }
        }

        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.RECEPTION_NUMBER_MSG + receptionNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, Integer.valueOf(receptionNumber).toString()));
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.FOREVER_NUMBER_MSG + foreverNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, Integer.valueOf(foreverNumber).toString()));
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.RETRY_NUMBER_MSG + retryNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, Integer.valueOf(retryNumber).toString()));

        for (Future<Boolean> f : resultList) {
            try {
                f.get();
            } catch (Throwable e) {
                Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.ISSUED_FAILED);
                stepResult.setResult(false);
                return stepResult;
            }
        }

        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SENT_SUCCESS_NUMBER_MSG + sentSuccessNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, sentSuccessNumber.toString()));
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SENT_FAILURE_NUMBER_MSG + sentFailureNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, sentFailureNumber.toString()));


        threadPool.shutdown();
        //注销登录
        systemRequestService.logout(token);

        try {
            Thread.sleep(waitTimeMillis);
        } catch (InterruptedException e) {
            batchExceptionHandler.handle(e);
            stepResult.setResult(false);
            return stepResult;
        }

        //获取已生成的欠番数据
        ServiceResult<List<SaleSerialMissed>> missedSerialAfterRequset = saleSerialMissedService.querySaleSerialMissed(null);

        if (!listServiceResult.result()) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SALE_SERIAL_MISSED_GET_FAILED);
            stepResult.setResult(false);
            return stepResult;
        }
        List<SaleSerialMissed> afterRequestDataSerialMisseds = missedSerialAfterRequset.value();
        if (afterRequestDataSerialMisseds.size() == 0) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SALE_SERIAL_MISSED_TABLENAME);
            return stepResult;
        } else {
            Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.QUERY_SALESERIA_LMISSED) + String.format(Constant.QUERY_SUCCESS, afterRequestDataSerialMisseds.size()));
        }

        //再处理受信个数
        int afterRequestReceptionNumber = 0;
        //再处理永久缺号个数
        int afterRequestForeverNumber = 0;

        for (SaleSerialMissed s : afterRequestDataSerialMisseds) {
            //检查该欠番是否已经受信,已受信则更新
            ServiceResult<Integer> integerServiceResult = saleSerialMissedService.updateIfRecepted(null, s);
            if (!integerServiceResult.result()) {
                Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.RECEPTION_CONFIRMATION_PROCESSING_FAILED);
                break;
            }
            //如果已受信，跳过该欠番
            if (integerServiceResult.value() > 0) {
                afterRequestReceptionNumber++;
                continue;
            }

            int retryTimes = Integer.parseInt(s.getRetryTimes());
            if (maxRetryTimes <= retryTimes) {
                //超过重试次数,永久欠番
                ServiceResult<Integer> deleteNumber = saleSerialMissedService.updateAsMissedForever(s, null);
                if (!deleteNumber.result()) {
                    Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MISSED_FOREVER_FAILED);
                    break;
                }
                afterRequestForeverNumber++;
            }
        }
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.AFTER_REQUEST_RECEPTION_NUMBER_MSG + afterRequestReceptionNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, Integer.valueOf(afterRequestReceptionNumber).toString()));
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.AFTER_REQUEST_FOREVER_NUMBER_MSG + afterRequestForeverNumber);
        Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, Integer.valueOf(afterRequestForeverNumber).toString()));

        return stepResult;
    }

    class MissedSerialProcesser implements Callable<Boolean> {

        private String token;
        private SaleSerialMissed saleSerialMissed;

        public MissedSerialProcesser(String token, SaleSerialMissed saleSerialMissed) {
            this.token = token;
            this.saleSerialMissed = saleSerialMissed;
        }

        @Override
        public Boolean call() throws Exception {

            SystemRequestService systemRequestService = new SystemRequestService();

            //取引通番欠番在要求
            ServiceResult<JSONObject> responseResult = systemRequestService.requestCallReq(token, saleSerialMissed);

            //判断请求结果
            if (!responseResult.result()) {
                ServiceResult<NULL> decreaseRetryTimesRet = saleSerialMissedService.decreaseRetryTimes(saleSerialMissed.getId(), null);
                if (!decreaseRetryTimesRet.result()) {
                    return false;
                } else {
                    sentFailureNumber.incrementAndGet();
                }
            } else {
                sentSuccessNumber.incrementAndGet();
            }
			
            return true;
        }
    }
}
