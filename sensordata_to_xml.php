<?php 

// Show errors
error_reporting(E_ALL);
ini_set('display_errors', 1);

require_once('mysqli_connect.php');

$query = "SELECT ID, Temperature, Humidity, CPUTemp, Date, Time FROM data";

$response = @mysqli_query($dbc, $query);


if (extension_loaded('simplexml')) {

    echo "all good, extension is installed<br>";

} else{ echo "snip snap! no cigar<br>";}   

if($response){

	$xml = "<?xml version='1.0' encoding='utf-8'?>\n<weatherstation>\n";

	while ($row = mysqli_fetch_assoc($response)) {
		$xml .="\n\t<sensordata>\n";

		$xml .="\t\t<ID>".$row['ID']."</ID>\n";
		$xml .="\t\t<Temperature>".$row['Temperature']."</Temperature>\n";
		$xml .="\t\t<Humidity>".$row['Humidity']."</Humidity>\n";
		$xml .="\t\t<CPUTemp>".$row['CPUTemp']."</CPUTemp>\n";
		$xml .="\t\t<Date>".$row['Date']."</Date>\n";
		$xml .="\t\t<Time>".$row['Time']."</Time>\n";	

  		$xml .="\t</sensordata>\n";		
	}

	$xml .= "\n</weatherstation>";
	$sxe = new SimpleXMLElement($xml);
	//echo $sxe->asXML();
	$sxe->asXML("weatherstation_data.xml");
	
} else {

echo "Couldn't issue database query<br />";

echo mysqli_error($dbc);

}

// Close connection to the database
mysqli_close($dbc);

 ?>