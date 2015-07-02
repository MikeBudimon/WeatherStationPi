<?php 

// Show errors
error_reporting(E_ALL);
ini_set('display_errors', 1);

require_once('mysqli_connect.php');

$query = "SELECT * FROM XX"; // exchange XX for your db table

$response = @mysqli_query($dbc, $query);
   

if($response){

	$sensordata[] = array();

	while ($row = mysqli_fetch_assoc($response)) {

			$sensordata[] = $row;
	}

	// delete first empthy data
	array_shift($sensordata);

//echo json_encode($sensordata);

// Write to json file
$fp = fopen("XX", "w"); // // exchange XX for your path
fwrite($fp, json_encode($sensordata));
fclose($fp);

} else {

echo "Couldn't issue database query<br />";

echo mysqli_error($dbc);

}

// Close connection to the database
mysqli_close($dbc);

 ?>