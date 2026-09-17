package util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;

/**
 * Utility class for application logging.
 */
public class AppLogger {
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    static {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            
            // Remove default handlers
            Logger rootLogger = Logger.getLogger("");
            java.util.logging.Handler[] handlers = rootLogger.getHandlers();
            for (java.util.logging.Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }
            
            FileHandler fileHandler = new FileHandler("logs/app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(consoleHandler);
            
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    /**
     * Logs an info message.
     *
     * @param msg the message to log
     */
    public static void info(String msg) {
        logger.info(msg);
    }

    /**
     * Logs a warning message.
     *
     * @param msg the message to log
     */
    public static void warning(String msg) {
        logger.warning(msg);
    }

    /**
     * Logs an error message.
     *
     * @param msg the message to log
     */
    public static void error(String msg) {
        logger.severe(msg);
    }
}
