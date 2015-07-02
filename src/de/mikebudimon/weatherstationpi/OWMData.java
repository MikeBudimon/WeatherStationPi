package de.mikebudimon.weatherstationpi;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.IOException;

/**
 * Get newest weatherdata from OpenWeatherMap
 */
public class OWMData {

    private CurrentWeather cwd;

    // set your OpenWeatherMap key
    public OWMData() {
        OpenWeatherMap owm = new OpenWeatherMap(""); // enter your owm key
        try {
            cwd = owm.currentWeatherByCityName("", "DE"); // enter your city
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get temperature and humidity data
     */
    public float[] getOWMData() {
        // converting Temp in Fahrenheit to Celsius
        return new float[]{((cwd.getMainInstance().getTemperature() - 32) * 5 / 9), cwd.getMainInstance().getHumidity()};
    }
}
