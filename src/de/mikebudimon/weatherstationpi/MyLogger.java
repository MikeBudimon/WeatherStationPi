package de.mikebudimon.weatherstationpi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * Log program events to file
 */
public class MyLogger {

    public final static Logger LOGGER = Logger.getLogger(MyLogger.class.getName());
    private static FileHandler mFileHandler = null;

    public static void activateHandler() throws Exception {

        if (mFileHandler == null){
            LOGGER.setLevel(Level.SEVERE);

            mFileHandler = new FileHandler("/home/pi/projects/Log"+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xml");
            LOGGER.addHandler(mFileHandler);
        }
    }

    private static void suppressConsole(){

        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        for (Handler handler: handlers) {
            rootLogger.removeHandler(handler);
        }
    }

}
