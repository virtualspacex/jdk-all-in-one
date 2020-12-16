package com.virtualspacex.util.logger;

public abstract class AbstractLogger {

	public abstract void trace(String msg);
	
	public abstract void debug(String msg);
	
	public abstract void info(String msg);
	
	public abstract void warn(String msg);
	
	public abstract void error(String msg);
}
