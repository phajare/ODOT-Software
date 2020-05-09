package edu.okstate.entities;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
	
	public static Logger printLog() {
		LogManager lgmngr = LogManager.getLogManager();
    	Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
		return log;
	}
}
