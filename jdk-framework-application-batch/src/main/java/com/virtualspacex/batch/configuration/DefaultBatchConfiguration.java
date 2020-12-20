package com.virtualspacex.batch.configuration;

import java.lang.Thread.UncaughtExceptionHandler;

import com.virtualspacex.batch.checker.Checker;
import com.virtualspacex.batch.filter.AfterFilter;
import com.virtualspacex.batch.filter.BeforeFilter;
import com.virtualspacex.exception.handler.DefaultUncaughtExceptionHandler;
import com.virtualspacex.middleware.handler.EventHandlerInterface;
import com.virtualspacex.util.logger.AbstractLogger;
import com.virtualspacex.util.logger.JdkLogger;

public class DefaultBatchConfiguration implements BatchConfigurationInterface{

	@Override 
	public Metadata getMeta() {
		return new Metadata("default batch version", "default batch name", "default batch id");
	}
	
	@Override
	public AbstractLogger getLogger() {
		return new JdkLogger();
	}

	@Override
	public UncaughtExceptionHandler getUncaughtExceptionHandler() {
		return new DefaultUncaughtExceptionHandler();
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public DatabaseUtilInterface<?, ?> getDatabaseUtil() throws Exception {
		
   	// String jdbcConfigFile = FileUtil.getClassPath()
    //    		+ File.separator 
    //    		+ Constants.CONFIG_FILE_PATH
    //    		+ File.separator 
    //    		+ Constants.HIBERNATE_CONFIG_FILE;
   	
	// 	return new JdbcUtils(jdbcConfigFile);
	// 	return null;
	// }
	
	@Override
	public Checker getChecker() {
		return () -> {return true;};
	}
	
	@Override
	public EventHandlerInterface getStartupListener() {
		return ()-> {
			return;
		};
	}
	
	@Override
	public EventHandlerInterface getShutdownListener() {
		return ()-> {
			return;
		};
	}
	
	@Override
	public BeforeFilter getBeforeFilter() {
		return ()-> {return true;};
	}
	
	@Override
	public AfterFilter getAfterFilter() {
		return ()-> {return true;};
	}
}
