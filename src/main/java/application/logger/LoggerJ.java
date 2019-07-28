package application.logger;

import application.constants.CharConstants;
import org.apache.log4j.Logger;

/**
 * Log4j logger
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
            logger.info(CharConstants.sixtyStars);
            logger.info(message);
            logger.info(CharConstants.sixtyStars);
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
            logger.info(CharConstants.sixtyStars);
            logger.error(message);
            logger.info(CharConstants.sixtyStars);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
