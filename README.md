#What is WeatherStationPI?
WeatherStationPI is a project which obtains weather data from a sensor and OpenWeatherMap and displays it on a website.
For hosting a website and storing data into a database a Raspberry Pi B+ is in use. Also <a href=http://www.highcharts.com/>Highcharts<a/> was used for displaying
the data in a nice looking graph. A <a href=http://www.adafruit.com/products/385>DHT22<a/> sensor was used to deliver temperature and humidity data.

#How does WeatherStationPI work?
Every 10 minutes the Raspberry Pi performs the java program to get sensordata and saves it into a database table. 
At the end a PHP script is called to save all sensordata in the database into a json file. Then the json data can be loaded
from a javascript file into highcharts as a series and displayed on a website. The website was made by using <a href=http://getbootstrap.com/>Bootstrap<a/>.
The java program but also the PHP-script which saves sensordata into json and all files for displaying the result in a
website are included.


#License
<a href=http://opensource.org/licenses/MIT>MIT License<a/>
