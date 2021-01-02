package cn.com.virtualspacex.service;

import com.virtualspacex.annotation.AutoExceptionHandler;
import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.annotation.AutoSession;
import com.virtualspacex.annotation.AutoTransaction;
import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.dao.SaleSerialMissedDao;
import cn.com.virtualspacex.exception.BatchExceptionHandler;
import cn.com.virtualspacex.model.SaleSerialMissed;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.tasks.logger.Logger;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import java.util.Arrays;
import java.util.List;

public class SaleSerialMissedService {

    @AutoLog
    private SaleSerialMissedDao saleSerialMissedDao;

    @AutoExceptionHandler
    private BatchExceptionHandler batchExceptionHandler;
    
    public <T> ServiceResult<T> createNewMissedSerial(Session session,List<SaleSerialMissed> missedSaleSerials)  {
        ServiceResult<T> integerServiceResult = new ServiceResult<>();
        try {
            saleSerialMissedDao.insertByList(session, missedSaleSerials);
            integerServiceResult.setResult(true);
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, String.valueOf(missedSaleSerials.size())));
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        
        return integerServiceResult;
    }

    /**
     * 明细表中存在，更新缺号表状态，
     * @throws Exception
     */
    @AutoTransaction
    public  ServiceResult<Integer> updateIfRecepted(@AutoSession Session session,SaleSerialMissed saleSerialMissed)  {
        ServiceResult<Integer> integerServiceResult = new ServiceResult<>();
        try {
            Integer i = saleSerialMissedDao.updateIfRecepted(session,saleSerialMissed);
            integerServiceResult.setResult(true);
            integerServiceResult.setValue(i);
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        return  integerServiceResult;
    }

    /**
     * 当retry_time等于系统设定时，更新is_deleted=1
     * @return 影响条数
     * @throws Exception
     */
    @AutoTransaction
    public  ServiceResult<Integer> updateAsMissedForever (SaleSerialMissed saleSerialMissed,@AutoSession Session session)  {
        ServiceResult<Integer> objectServiceResult = new ServiceResult<>();
        try {
            Integer i = saleSerialMissedDao.deleteNumber(session, saleSerialMissed);
            objectServiceResult.setResult(true);
            objectServiceResult.setValue(i);
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        return objectServiceResult;
    }


    /**
     * 查询缺号表中符合重发的数据
     * @return
     */
    @AutoTransaction
    public  ServiceResult<List<SaleSerialMissed>> querySaleSerialMissed( @AutoSession Session session )  {
        ServiceResult<List<SaleSerialMissed>> objectServiceResult = new ServiceResult<>();
        List<SaleSerialMissed> saleSerialMisseds=null;
        try {
            saleSerialMisseds = saleSerialMissedDao.querySaleSerialMissed(session);
            objectServiceResult.setResult(true);
            objectServiceResult.setValue(saleSerialMisseds);
            return objectServiceResult;
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.QUERY_SALESERIA_LMISSED) + String.format(Constant.QUERY_FAILED, e.getMessage()));
            batchExceptionHandler.handle(e);
            return objectServiceResult;
        }
    }

    /**
     * retry_times加一
     * @param id
     * @return  是否执行成功
     * @throws Exception
     */
    @AutoTransaction
    public ServiceResult<Integer> increaseRetryTimes(int id, @AutoSession Session session) {
        ServiceResult<Integer> serviceResult = new ServiceResult<>();
        try {
            Integer i = saleSerialMissedDao.increaseRetryTimes(session, String.valueOf(id));
            serviceResult.setResult(true);
            serviceResult.setValue(i);
        } catch (Exception e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        return serviceResult;
    }
    
    @AutoTransaction
    public <T> ServiceResult<T> decreaseRetryTimes(int id, @AutoSession Session session) {
        ServiceResult<T> serviceResult = new ServiceResult<>();
        try {
            saleSerialMissedDao.decreaseRetryTimes(session, String.valueOf(id));
            serviceResult.setResult(true);
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_MISSED_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        return serviceResult;
    }

    /**
     * 删除缺号数据
     * @param id
     */
    @AutoTransaction
    public synchronized <T> ServiceResult<T> deleteById(@AutoSession Session session, int id) {
        ServiceResult<T> objectServiceResult = new ServiceResult<T>();
        try {
            saleSerialMissedDao.deleteObjectById(session, SaleSerialMissed.class, id);
            objectServiceResult.setResult(true);
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.QUERY_SALESERIA_LMISSED) + String.format(Constant.TRUNCATE_FAILED, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.QUERY_SALESERIA_LMISSED) +Constant.TRUNCATE_SUCCESS);
        return objectServiceResult;
    }
}
