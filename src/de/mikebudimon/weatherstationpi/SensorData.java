package de.mikebudimon.weatherstationpi;

import com.pi4j.system.SystemInfo;
import se.hirt.w1.Sensor;
import se.hirt.w1.Sensors;

import java.util.Set;

/**
 * Get Data from DHT22 sensor
 */
public class SensorData{

    private Set<Sensor> sensors;


    public float[] getData() throws Exception{

        sensors = Sensors.getSensors();

        float temps[] = new float[3];
        int i = 0;

        for (Sensor sensor : sensors) {

            float temp = (Math.round((float) sensor.getValue() * 100.0f) / 100.0f);

            // Sometimes the sensors sends false temp data as 0.00
            while (temp == 0.00) {
                temp = (Math.round((float) sensor.getValue() * 100.0f) / 100.0f);
            }
            temps[i] = temp;
            i++;
        }

        temps[i] = SystemInfo.getCpuTemperature();

        return temps;
    }

    public void showData() throws Exception{

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
