package com.virtualspacex.batch.step;

public interface StepInterface {
	
	public enum StepStatusEnum{
		SUCCESS,
		FAIL,
		PROCESSING,
		FREE,
		DUMMY
	}
	
	StepStatusEnum status();
	
	void accept(Object ... objects);
	
	boolean rollbackable();
	
	<T> StepResult<T> execute();
	
	void retry();
	
	boolean reset();
}
