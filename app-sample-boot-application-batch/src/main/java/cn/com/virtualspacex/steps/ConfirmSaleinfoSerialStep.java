package cn.com.virtualspacex.steps;

import java.util.ArrayList;

import org.hibernate.Session;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.model.SaleSerialEnd;
import cn.com.virtualspacex.model.SaleSerialMissed;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.service.OnlinesaleinfosService;
import cn.com.virtualspacex.service.SaleSerialEndService;
import cn.com.virtualspacex.service.SaleSerialMissedService;
import cn.com.virtualspacex.tasks.logger.Logger;

import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.annotation.AutoSession;
import com.virtualspacex.annotation.AutoTransaction;
import com.virtualspacex.batch.step.AbstractStep;
import com.virtualspacex.batch.step.StepResult;
import com.virtualspacex.util.entity.NULL;

public class ConfirmSaleinfoSerialStep  extends AbstractStep{
	
    @AutoLog
    private SaleSerialEndService saleSerialEndService;

    @AutoLog
    private OnlinesaleinfosService onlinesaleinfosService;

    @AutoLog
    private SaleSerialMissedService saleSerialMissedService;
    
	@Override
	public <T> StepResult<T> execute() {
		
		StepResult<T> stepResult = new StepResult<T>();
	     
        //事务：进行欠番检查,生成欠番数据,更新最终取引表
        StepResult<NULL> updateResult = updateSerialEndAndCreateMissedSerialWithTransaction(null);
        if (!updateResult.result()) {
            return stepResult;
        } 
        
        stepResult.setResult(true);
        return stepResult;
	}
	
	@AutoTransaction
	public <T> StepResult<T> updateSerialEndAndCreateMissedSerialWithTransaction(@AutoSession Session session) {
		
		StepResult<T> stepResult = new StepResult<T>();
		
		//获取新收到的销售数据
        ServiceResult<Boolean> getResult = onlinesaleinfosService.getNewSaleinfosFromLastTime(null);
        if (!getResult.result()) {
        	Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SALE_SERIAL_NUMBER_GET_FAILED);
            return stepResult;
        }  
		
        //SaleSerialEnds需更新的数据集合
        ArrayList<SaleSerialEnd> maxSaleSerialEnd = onlinesaleinfosService.getMaxSaleSerials().value();
        //SaleSerialMissed新增数据
        ArrayList<SaleSerialMissed> newSaleSerialMissed = onlinesaleinfosService.getMissedSaleSerials().value();
        
        ServiceResult<NULL> updateSaleSerialEndResult = saleSerialEndService.updateSaleSerialEnd(session, maxSaleSerialEnd);
        if (!updateSaleSerialEndResult.result()) {
        	Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.END_SALE_SERIAL_NUMBER_UPDATE_FAILED);
            return stepResult;
        }
        
        ServiceResult<NULL> createNewMissedSerialResult = saleSerialMissedService.createNewMissedSerial(session, newSaleSerialMissed);
        if (!createNewMissedSerialResult.result()) {
        	Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.SALE_SERIAL_MISSED_ADD_FAILED);
            return stepResult;
        }
        
        stepResult.setResult(true);
        return stepResult;
	}
}


