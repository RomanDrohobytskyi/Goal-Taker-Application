package application.logger;

import org.apache.log4j.Logger;

/**
 * Log4j logger
 */
public class LoggerJ {

    public static Logger getLoggerForClass(Class<?> loggerClass){
        return org.apache.log4j.Logger.getLogger(loggerClass);
    }
}
