package com.virtualspacex.batch.job;

public abstract class AbstractJob implements JobInterface{
	public void execute() {
		System.out.println("= = = DEFAULT JOB START SUCCESSFUL! = = =");
	};
}
