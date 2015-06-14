package de.mikebudimon.weatherstationpi;


import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws Exception {

        ConnectDB connectDB = new ConnectDB();
        SensorData sensorData = new SensorData();
        OpenWeatherMap owm = new OpenWeatherMap("0e57d03bd82f2c8a3776d74c26e9c37f");
        CurrentWeather cwd = owm.currentWeatherByCityName("Oberhausen", "DE");

        try {
            System.out.println("Receiving sensordata...");
            float[] sData = sensorData.getData();

            System.out.println("Connecting to a selected database...");
            connectDB.useDatabase();
            System.out.println("Successfully connected to database!");


            System.out.println("Writing sensordata to database...");
            connectDB.writeDatabase(sData[0], sData[1], sData[2], Math.round(((cwd.getMainInstance().getTemperature() - 32) * 5/9) * 100.0f) / 100.0f, Math.round((cwd.getMainInstance().getHumidity()) * 100.0f) / 100.0f);
            System.out.println("Successfully wrote sensordata to database!");

            System.out.println("Writing sensordata to json...");
            Runtime.getRuntime().exec("sudo php5 /var/www/sensordata_to_json.php");
            System.out.println("Successfully wrote sensordata to json!");

            System.out.println("Finished. Quiting program!");

        } catch (Exception e) {
            MyLogger.activateHandler();
            MyLogger.LOGGER.log(Level.SEVERE, e.getMessage());

        } finally {
            try {
                connectDB.close();

            } catch (Exception e) {
                MyLogger.activateHandler();
                MyLogger.LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }





    }
}
