package cn.com.virtualspacex.service;


import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.dao.SaleSerialEndDao;
import cn.com.virtualspacex.dao.SaleSerialMissedDao;
import cn.com.virtualspacex.exception.BatchExceptionHandler;
import cn.com.virtualspacex.model.SaleSerialEnd;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.tasks.logger.Logger;
import cn.com.virtualspacex.tasks.properties.PropertiesManager;

import com.virtualspacex.annotation.AutoExceptionHandler;
import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.annotation.AutoSession;
import com.virtualspacex.annotation.AutoTransaction;

import java.util.*;

/**
 * 处理最终交易表
 *
 * @author zeng-zhuanghu
 * @date 2020/10/22
 */
public class SaleSerialEndService {

    @AutoLog
    private SaleSerialEndDao saleSerialEndDao;

    @AutoLog
    private SaleSerialMissedDao saleSerialMissedDao;

    @AutoExceptionHandler
    private BatchExceptionHandler batchExceptionHandler;

    /**
     * 故障数据更新
     *
     * @param troubleshooting
     */
    @AutoTransaction
    public <T> ServiceResult<T> upsertSaleSerialEnd(@AutoSession Session session, List<SaleSerialEnd> newSaleSerialEndPO) {
        ServiceResult<T> serviceResult = new ServiceResult<T>();
        try {
            //更新、追加故障数据
            saleSerialEndDao.upsertObjects(session, newSaleSerialEndPO);
            serviceResult.setResult(true);
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_END_TABLENAME, String.valueOf(newSaleSerialEndPO.size())));
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_END_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        return serviceResult;
    }
    
    /**
     * 故障数据更新
     *
     * @param troubleshooting
     */
    public <T> ServiceResult<T> updateSaleSerialEnd(Session session, List<SaleSerialEnd> newSaleSerialEndPO) {
        ServiceResult<T> serviceResult = new ServiceResult<T>();
        try {
            //更新、追加故障数据
            saleSerialEndDao.updateSaleSerialEnd(session, newSaleSerialEndPO);
            serviceResult.setResult(true);
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_END_TABLENAME, String.valueOf(newSaleSerialEndPO.size())));
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_END_TABLENAME, e.getMessage()));
            batchExceptionHandler.handle(e);
        }
        return serviceResult;
    }

    /**
     * 更新 sale_serial_end
     * @param dataProcessingBO
     */
    @AutoTransaction
    public synchronized <T> ServiceResult<T> updateSaleSerialEndByVmcode(@AutoSession Session session, String saleSerialEnd, String vmCode) {
        ServiceResult<T> objectServiceResult = new ServiceResult<T>();
        try {
            Integer update = saleSerialEndDao.updateSaleSerialEndByVmcode(session, saleSerialEnd, vmCode);
            objectServiceResult.setResult(true);
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_I_00004, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_END_TABLENAME,  update.toString()));
        } catch (Throwable e) {
            Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.MESSAGE_E_00013, Arrays.asList(Constant.TASK_NAME, PropertiesManager.SALE_SERIAL_END_TABLENAME, e.getMessage()));
              batchExceptionHandler.handle(e);
        }
        return objectServiceResult;
    }
}
