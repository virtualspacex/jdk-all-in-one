package com.virtualspacex.batch.step;

public abstract class AbstractStep implements StepInterface {

	public StepStatusEnum status() {return StepStatusEnum.DUMMY;}
	
	public void accept(Object ... objects) {}
	
	public boolean rollbackable() {return false;}
	
	public void retry() {}
	
	public boolean reset() {return false;}
}
