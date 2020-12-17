package cn.com.virtualspacex.steps;

import java.util.List;

import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.batch.step.AbstractStep;
import com.virtualspacex.batch.step.StepResult;
import com.virtualspacex.util.entity.NULL;

import cn.com.virtualspacex.constants.Constant;
import cn.com.virtualspacex.model.SaleSerialEnd;
import cn.com.virtualspacex.model.ServiceResult;
import cn.com.virtualspacex.service.OnlinesaleinfosService;
import cn.com.virtualspacex.service.SaleSerialEndService;
import cn.com.virtualspacex.tasks.logger.Logger;

public class MasterTroubleConfirmStep extends AbstractStep{

    @AutoLog
    private OnlinesaleinfosService onlinesaleinfosService;
    
    @AutoLog
    private SaleSerialEndService saleSerialEndService;

	@Override
	public <T> StepResult<T> execute() {
		
		StepResult<T> stepResult = new StepResult<T>();
		
        //故障判断
        ServiceResult<Boolean> ret = onlinesaleinfosService.getSaleinfosFailureNum(null);
        if (!ret.result()) {
        	Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.TROUBLE_GET_FAILED);
            return stepResult;
        }

        //故障处理
        List<SaleSerialEnd> newSaleSerialEndPO = onlinesaleinfosService.getSaleSerialEndNeedReset();

        //更新故障数据
        ServiceResult<NULL> faultUpdate = saleSerialEndService.upsertSaleSerialEnd(null, newSaleSerialEndPO);
        if (!faultUpdate.result()) {
        	Logger.getLogger(Constant.LOGGER_TASK).msg(Constant.TROUBLE_UPDATE_FAILED);
            return stepResult;
        }
        
        stepResult.setResult(true);
        return stepResult;
	}
}
