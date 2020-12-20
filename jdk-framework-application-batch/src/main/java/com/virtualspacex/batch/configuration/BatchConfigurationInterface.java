package com.virtualspacex.batch.configuration;

import java.lang.Thread.UncaughtExceptionHandler;

import com.virtualspacex.batch.checker.Checker;
import com.virtualspacex.batch.filter.AfterFilter;
import com.virtualspacex.batch.filter.BeforeFilter;
import com.virtualspacex.middleware.handler.EventHandlerInterface;
// import com.virtualspacex.util.database.DatabaseUtilInterface;
import com.virtualspacex.util.logger.AbstractLogger;

public interface BatchConfigurationInterface {
	
	Metadata getMeta();

	AbstractLogger getLogger();
	
	UncaughtExceptionHandler getUncaughtExceptionHandler();
	
	// <C, T> DatabaseUtilInterface<C, T> getDatabaseUtil() throws Exception;
	
	Checker getChecker();
	
	EventHandlerInterface getStartupListener();
	
	EventHandlerInterface getShutdownListener();
	
	BeforeFilter getBeforeFilter();
	
	AfterFilter getAfterFilter();
}
