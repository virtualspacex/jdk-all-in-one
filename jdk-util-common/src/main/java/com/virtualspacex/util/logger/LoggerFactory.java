package com.virtualspacex.util.logger;

public class LoggerFactory implements LoggerFactoryInterface {
	
	private static AbstractLogger logger= new JdkLogger();
	
	public static void register(AbstractLogger logger) {
		LoggerFactory.logger = logger;
	}
	
	public static AbstractLogger getLogger() {
		return LoggerFactory.logger;
	}
}
