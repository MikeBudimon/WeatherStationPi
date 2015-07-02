package de.mikebudimon.weatherstationpi;

import com.pi4j.system.SystemInfo;
import se.hirt.w1.Sensor;
import se.hirt.w1.Sensors;

import java.util.Set;

/**
 * get or print data from DHT22 sensor
 */
public class SensorData{

    private Set<Sensor> sensors;


    /**
     * get sensordata
     * @return temps
     * @throws Exception
     */
    public float[] getData() throws Exception{

        sensors = Sensors.getSensors();

        float temps[] = new float[3];
        int i = 0;

        // first humidity, second temperature => check with printData() if not sure
        for (Sensor sensor : sensors) {

            float temp = (Math.round((float) sensor.getValue() * 100.0f) / 100.0f);
            long time = System.currentTimeMillis();

            // Sometimes the sensors sends false data
            while (temp <= 0.00) {
                temp = (Math.round((float) sensor.getValue() * 100.0f) / 100.0f);
                long curTime = System.currentTimeMillis();

                // if sensor sends false data for 8 minutes => end program
                if (curTime - time > 480000){
                    System.exit(0);
                }
            }
            temps[i] = temp;
            i++;
        }

        // checks if humidity or temperature data is wrong => restart
        if (temps[0] >= 100.0f || temps[1] >= 35.0f){
            getData();
        }

        temps[i] = SystemInfo.getCpuTemperature();

        return temps;
    }

    /**
     * print sensordata
     * @throws Exception
     */
    public void printData() throws Exception{

        System.out.printf("Found %d sensors! \n", sensors.size());

        for (Sensor sensor : sensors) {

            float temp = (Math.round((float) sensor.getValue() * 100.0f) / 100.0f);

            System.out.println("Please wait for data...");

            // Sometimes the sensors sends false temp data as 0.00
            while (temp == 0.00) {
                temp = (Math.round((float) sensor.getValue() * 100.0f) / 100.0f);
            }

            System.out.printf("%s(%s):%.2f %s \n", sensor.getPhysicalQuantity(),
                    sensor.getID(), sensor.getValue(), sensor.getUnitString());

        }
        System.out.println("CPU Temperature: " + SystemInfo.getCpuTemperature());
    }

}
