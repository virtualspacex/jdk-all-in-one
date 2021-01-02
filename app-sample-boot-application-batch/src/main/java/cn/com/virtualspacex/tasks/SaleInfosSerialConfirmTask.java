/*
 * @Author: keiki
 * @Date: 2020-12-28 19:35:15
 * @LastEditTime: 2021-01-01 15:30:47
 * @LastEditors: keiki
 * @Description: 
 */
package cn.com.virtualspacex.tasks;

import com.virtualspacex.annotation.AutoLog;
import com.virtualspacex.batch.job.AbstractJob;
import com.virtualspacex.batch.step.StepResult;
import com.virtualspacex.util.entity.NULL;

import cn.com.virtualspacex.steps.ConfirmSaleinfoSerialStep;
import cn.com.virtualspacex.steps.MasterTroubleConfirmStep;
import cn.com.virtualspacex.steps.RequestMissedSerialStep;

/**
 * 缺号管理
 */
@AutoLog
//@ConfigBatch(BatchConfiguration.class)
public class SaleInfosSerialConfirmTask extends AbstractJob {
    
    @AutoLog
    private MasterTroubleConfirmStep masterTroubleConfirmStep;
    
    @AutoLog
    private ConfirmSaleinfoSerialStep confirmSaleinfoSerialStep;
    
    @AutoLog
    private RequestMissedSerialStep requestMissedSerialStep;
    
    // @MBean
    // private BatchDynamicConfigutation config;

    @Override
    public void execute() {

       //故障判断
   	StepResult<NULL> step1Result = masterTroubleConfirmStep.execute();
   	
   	if(step1Result.result()) {
   		//欠番检出及再要求处理
   		StepResult<NULL> step2Result = confirmSaleinfoSerialStep.execute();
   		
       	if(step2Result.result()) {
       		//再要求结果确认(欠番结果确认)
       		requestMissedSerialStep.execute();
       	}
   	}
    }
}