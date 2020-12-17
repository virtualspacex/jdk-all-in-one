package cn.com.virtualspacex.service;

import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.dao.DataProcessingBODao;
import cn.com.virtualspacex.dao.HostFailureBODao;
import cn.com.virtualspacex.exception.BatchExceptionHandler;
import cn.com.virtualspacex.model.SaleSerialEnd;
import cn.com.virtualspacex.model.SaleSerialMissed;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.model.bo.SaleinfosFromLastTimeBO;
import cn.com.virtualspacex.model.bo.VMFailureNumBO;
import cn.com.virtualspacex.tasks.logger.Logger;
import cn.com.virtualspacex.utils.DateUtils;

import com.virtualspacex.annotation.AutoExceptionHandler;
import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.annotation.AutoSession;
import com.virtualspacex.annotation.AutoTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 处理明细表
 * @author zeng-zhuanghu
 * @version 1.0
 * @date 2020/10/22
 * @since JDK8
 */
public class OnlinesaleinfosService {

    @AutoLog
    private HostFailureBODao hostFailureBODao;

    @AutoLog
    private DataProcessingBODao dataProcessingBODao;

    @AutoExceptionHandler
    private BatchExceptionHandler batchExceptionHandler;
    
    private List<SaleinfosFromLastTimeBO> saleinfosFromLastTimeBOs;
    
    private List<VMFailureNumBO> vmFailureNumBOs;

    /**
     * マスタボックス故障判定
     */
    @AutoTransaction
    public ServiceResult<Boolean> getSaleinfosFailureNum(@AutoSession Session session){
        ServiceResult<Boolean> serviceResult = new ServiceResult<>();
        serviceResult.setValue(false);
        try {
        	vmFailureNumBOs = hostFailureBODao.getVMFailureNumBO(session);
            serviceResult.setResult(true);
            if(vmFailureNumBOs.size() > 0) {
            	serviceResult.setValue(true);
            }
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.FAULT_JUDGMENT) + String.format(Constant.QUERY_FAILED, e.getMessage()));
            batchExceptionHandler.handle(e);
            return serviceResult;
        }
        Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.FAULT_JUDGMENT) + String.format(Constant.QUERY_SUCCESS, vmFailureNumBOs.size()));
        return serviceResult;
    }

    /**
     * 缺号数据获取
     * @param session
     * @return
     */
    @AutoTransaction
    public  ServiceResult<Boolean> getNewSaleinfosFromLastTime(@AutoSession Session session){
        ServiceResult<Boolean> listServiceResult = new ServiceResult<>();
        listServiceResult.setValue(false);
        try {
        	saleinfosFromLastTimeBOs = dataProcessingBODao.getNewSaleinfosFromLastTime(session);
        	listServiceResult.setResult(true);
            if(saleinfosFromLastTimeBOs.size() > 0) {
                listServiceResult.setValue(true);
            }
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.MISSING_NUMBER_CONFIRMATION) + String.format(Constant.QUERY_FAILED, e.getMessage()));
            batchExceptionHandler.handle(e);
            return listServiceResult;
        }
        Logger.getLogger(Constant.LOGGER_TASK).msg(String.format(Constant.FORMATTER, Constant.MISSING_NUMBER_CONFIRMATION) + String.format(Constant.QUERY_SUCCESS, saleinfosFromLastTimeBOs.size()));
        return listServiceResult;
    }

    public ServiceResult<ArrayList<SaleSerialMissed>> getMissedSaleSerials(){
        ServiceResult<ArrayList<SaleSerialMissed>> listServiceResult = new ServiceResult<>();

        ArrayList<SaleSerialMissed> missedSaleSerials = new ArrayList<SaleSerialMissed>();
        
        for (SaleinfosFromLastTimeBO s : saleinfosFromLastTimeBOs) {
	  
        	List<String> serialArray = Arrays.asList(s.getSaleSerialList().split(","));
        	int minSerial = Integer.valueOf(s.getLastSaleSerialEnd());
        	int maxSerial = Integer.valueOf(s.getMaxSaleSerial());
	  
        	for(int serial = minSerial + 1; serial <= maxSerial; serial++ ) {
        		if(!serialArray.contains(String.valueOf(serial))) {
        			SaleSerialMissed saleSerialMissed = new SaleSerialMissed();
        			saleSerialMissed.setLocationCode(s.getLocationCode());
        			saleSerialMissed.setPosCode(s.getPosCode());
        			saleSerialMissed.setVmCode(s.getVmCode());
        			saleSerialMissed.setCreatedAt(DateUtils.formatDateTime());
        			saleSerialMissed.setSaleSerial(String.valueOf(serial));
        			missedSaleSerials.add(saleSerialMissed);
        		}
        	}
        }
        
        listServiceResult.setValue(missedSaleSerials);
        
        return listServiceResult;
    }
    
    public  ServiceResult<ArrayList<SaleSerialEnd>> getMaxSaleSerials(){
        ServiceResult<ArrayList<SaleSerialEnd>> listServiceResult = new ServiceResult<>();
        
        ArrayList<SaleSerialEnd> maxSaleSerialEnds = new ArrayList<SaleSerialEnd>();

        for(SaleinfosFromLastTimeBO s : saleinfosFromLastTimeBOs) {
            SaleSerialEnd saleSerialEnd = new SaleSerialEnd();
            saleSerialEnd.setId(String.valueOf(s.getSaleSerialEndId()));
            saleSerialEnd.setLocationCode(s.getLocationCode());
            saleSerialEnd.setVmCode(s.getVmCode());
            saleSerialEnd.setPosCode(s.getPosCode());
            saleSerialEnd.setSaleSerialEnd(String.valueOf(s.getMaxSaleSerial()));
            saleSerialEnd.setUpdatedAt(DateUtils.formatDateTime());
            maxSaleSerialEnds.add(saleSerialEnd);
        }
        
        listServiceResult.setValue(maxSaleSerialEnds);

        return listServiceResult;
    }
    
    /**
     * 故障处理
     *
     * @param hostFailureBOS 查询出的故障数据
     * @return 返回 需要处理的数据 key：add（需要添加），update（需要更新）
     */
    public List<SaleSerialEnd> getSaleSerialEndNeedReset() {
        List<SaleSerialEnd> newSaleSerialEndPO = new ArrayList<>();

        //封装数据
        for (VMFailureNumBO hostFailureBO : vmFailureNumBOs) {
            SaleSerialEnd saleSerialEnd = new SaleSerialEnd();
            saleSerialEnd.setCheckedAt(hostFailureBO.getCheckAt());
            saleSerialEnd.setSaleSerialEnd(Constant.ONE);
            saleSerialEnd.setFailureNum(String.valueOf(hostFailureBO.getFailureNum()));
            saleSerialEnd.setPosCode(hostFailureBO.getPosCode());
            saleSerialEnd.setLocationCode(hostFailureBO.getLocationCode());
            saleSerialEnd.setVmCode(hostFailureBO.getVmCode());
           
            String time = DateUtils.formatDateTime();
            //判断右表数据是否存在
            if (null == hostFailureBO.getSaleSerialEndId()) {
                saleSerialEnd.setCreatedAt(time);
            } else {
                saleSerialEnd.setUpdatedAt(time);
            	saleSerialEnd.setCreatedAt(hostFailureBO.getCreateAt());
                saleSerialEnd.setId(hostFailureBO.getSaleSerialEndId());
            }
            
            newSaleSerialEndPO.add(saleSerialEnd);
        }

        return newSaleSerialEndPO;
    }
    
    /**
     * 通信失败，通过缺号数据，查询最终番号表缺号确认前的值
     *
     * @param missingNumber 缺号确认前 获取的缺号数据
     * @param m             下发失败的数据
     * @return 最终番号表缺号确认前的值
     */
    public String queryLastSaleSerialEnd(String vmCode) {
        //遍历获取
        for (SaleinfosFromLastTimeBO s : saleinfosFromLastTimeBOs) {
            if (s.getVmCode().equals(vmCode)) {
                return s.getLastSaleSerialEnd();
            }
        }
        
        return null;
    }
}
