package de.mikebudimon.weatherstationpi;

import com.pi4j.system.SystemInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * get data from DHT22 sensor through python script
 */
public class SensorData {

    /**
     * get sensordata
     *
     * @return data
     */
    public float[] getData() throws Exception {

        String dataS;
        String error;
        float[] data = new float[3];

        Process process = Runtime.getRuntime().exec("sudo python /home/pi/WeatherStationPi/getSensordata.py");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader brE = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        while ((error = brE.readLine()) != null) {
            MyLogger.activateFileHandler();
            MyLogger.LOGGER.log(Level.SEVERE, error);
        }

        for (int i = 0; (dataS = br.readLine()) != null; i++) {
            data[i] = Float.valueOf(dataS);
        }
        data[2] = SystemInfo.getCpuTemperature();

        return data;
    }
}
