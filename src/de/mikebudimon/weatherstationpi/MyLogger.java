package de.mikebudimon.weatherstationpi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Log program events to file
 */
public class MyLogger {

    public final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    private static FileHandler mFileHandler = null;

    /**
     * activate Handler to write logs to file
     * @throws Exception
     */
    public static void activateFileHandler() throws Exception {

        if (mFileHandler == null){
            LOGGER.setLevel(Level.SEVERE);

            mFileHandler = new FileHandler("/home/pi/WeatherStationPi/logs/" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xml");
            LOGGER.addHandler(mFileHandler);
        }
    }

    /**
     * suppress the logging output to the console
     */
    private static void suppressConsole(){

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        for (Handler handler: handlers) {
            rootLogger.removeHandler(handler);
        }
    }

}
