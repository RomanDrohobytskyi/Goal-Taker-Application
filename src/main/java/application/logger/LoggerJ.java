package application.logger;

import application.constants.CharConstants;
import org.apache.log4j.Logger;

/**
 * Log4j logger. <br>
 * Log Your INFO`s, ERROR`s here using created methods.
 */
public class LoggerJ {

    /**
     * Printing Log4J Info message with stars
     * @param logClass - invoke class
     * @param message - message to log
     */
    public static void logInfo(Class logClass, String message) {
        try {
            Logger logger = Logger.getLogger(logClass);
            logger.info(CharConstants.HUNDRED_STARS);
            logger.info(message);
            logger.info(CharConstants.HUNDRED_STARS);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Print Log4J ERROR message
     * @param logClass - invoke class
     * @param message - message to log
     */
    public static void logError(Class logClass, String message){
        try{
            Logger logger = Logger.getLogger(logClass);
            logger.info(CharConstants.HUNDRED_STARS);
            logger.error(message);
            logger.info(CharConstants.HUNDRED_STARS);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
