package com.virtualspacex.util.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JdkLogger extends AbstractLogger{

	@Override
	public void trace(String msg) {
		Logger.getLogger("").log(Level.FINEST, msg);
	}

	@Override
	public void debug(String msg) {
		Logger.getLogger("").log(Level.FINER, msg);
	}

	@Override
	public void info(String msg) {
		Logger.getLogger("").log(Level.INFO, msg);
	}

	@Override
	public void warn(String msg) {
		Logger.getLogger("").log(Level.WARNING, msg);
	}

	@Override
	public void error(String msg) {
		Logger.getLogger("").log(Level.SEVERE, msg);
	}

}
