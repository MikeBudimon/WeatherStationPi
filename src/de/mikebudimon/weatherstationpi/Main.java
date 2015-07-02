package de.mikebudimon.weatherstationpi;


import java.util.logging.Level;

public class Main {

    public static void main(String[] args) throws Exception {

        ConnectDB connectDB = new ConnectDB();
        SensorData sensorData = new SensorData();
        OWMData owmData = new OWMData();

        try {
            //System.out.println("Receiving sensordata...");
            float[] sData = sensorData.getData();


            //System.out.println("Connecting to a selected database...");
            connectDB.useDatabase();
            //System.out.println("Successfully connected to database!");

            //System.out.println("Writing sensordata to database...");

            // set:temperature, humidity, cpuTemp, owmTemp, owmHum
            connectDB.writeDatabase(sData[0], sData[1], sData[2], Math.round(owmData.getOWMData()[0] * 100.0f) / 100.0f, Math.round(owmData.getOWMData()[1] * 100.0f) / 100.0f)
            ;
            //System.out.println("Successfully wrote sensordata to database!");

            //System.out.println("Writing sensordata to json...");
            Runtime.getRuntime().exec("sudo php5 /home/pi/WeatherStationPi/sensordata_to_json.php");
            //System.out.println("Successfully wrote sensordata to json!");

            System.out.println("Finished. Quiting program!");

        } catch (Exception e) {
            MyLogger.activateFileHandler();
            MyLogger.LOGGER.log(Level.SEVERE, e.getMessage());

        } finally {
            try {
                connectDB.close();

            } catch (Exception e) {
                MyLogger.activateFileHandler();
                MyLogger.LOGGER.log(Level.SEVERE, e.getMessage());
            }
        }


    }
}
