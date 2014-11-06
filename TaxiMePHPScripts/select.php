<?php

$userEmail = $_GET['userEmail'];
$userPassword = $_GET['userPassword'];

$dbHost = "mysql17.000webhost.com";
$dbName = "a6286641_usersdb";
$dbTable = "userprofile";
$dbUserName = "a6286641_user";
$dbPassword = "password2014";
$connection;

//$userEmail = "cwillison94@gmail.com";
//$userPassword = "password";
establishConnection();

if (isUserLoggedIn()){
	echo "LoggedIn";
} else if (selectFromDB() == $userPassword) {
	echo "Accept";
	writeLoginStatus();
} else {
	echo "Denied";
}

function establishConnection() {
	global $userEmail;
	global $userPasswor;
	global $dbHost;
	global $dbName;
	global $dbTable;
	global $dbUserName;
	global $dbPassword;
	global $connection;
	$connection = mysql_connect($dbHost, $dbUserName, $dbPassword) or die(mysql_error());
	mysql_select_db($dbName) or die(mysql_error());
}

function isUserLoggedIn() {
	global $userEmail;
	global $userPasswor;
	global $dbHost;
	global $dbName;
	global $dbTable;
	global $dbUserName;
	global $dbPassword;
	global $connection;
	
	$loginQuery = "SELECT LoginStatus FROM $dbTable WHERE userEmail = '$userEmail'";

	$result = mysql_query($loginQuery);

	$row = mysql_fetch_object($result);

	$LoginStatus = $row->LoginStatus;
	
	if ($LoginStatus == 1) {
		return true;
	} else {
		return false;
	}
	
	//mysql_close($connection);
}

function selectFromDB() {
	global $userEmail;
	global $userPasswor;
	global $dbHost;
	global $dbName;
	global $dbTable;
	global $dbUserName;
	global $dbPassword;
	global $connection;

	$query = "SELECT userPassword FROM $dbTable WHERE userEmail = '$userEmail'";
	
	$result = mysql_query($query);
	$count = mysql_num_rows($result);
	
	if ($count) {
		$output = mysql_result($result, 0);
		return $output;
	} else {
		return "0";
	}

	
	mysql_free_result($result);
}

function writeLoginStatus() {
	global $userEmail;
	global $userPasswor;
	global $dbHost;
	global $dbName;
	global $dbTable;
	global $dbUserName;
	global $dbPassword;
	global $connection;
	$query = "UPDATE $dbTable SET LoginStatus='1' WHERE userEmail = '$userEmail'";
	
	$result = mysql_query($query);
	
}

?>