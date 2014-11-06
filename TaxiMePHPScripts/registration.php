<?php

//http://taxime.comeze.com/TaxiMe/registration.php?LoginStatus=1&userEmail=cwillison94@gmail.ca&userPassword=password&fname=Cole&lname=Willison&sex=m&proffession=student&age=20&rating=5
$userEmail = $_GET['userEmail'];
$userPassword = $_GET['userPassword'];
$sex = $_GET['sex'];
$proffession = $_GET['proffession'];
$age = $_GET['age'];
$rating = $_GET['rating'];
$loginStatus = $_GET['LoginStatus'];
$fname = $_GET['fname'];
$lname = $_GET['lname'];

$sql = "INSERT INTO `a6286641_usersdb`.`userprofile` (`ID`,`LoginStatus`, `userEmail`, `userPassword`,`fname`,`lname`, `sex`, `proffession`, `age`, `rating`) VALUES (NULL, '$LoginStatus','$userEmail', '$userPassword', '$fname', '$lname','$sex', '$proffession', '$age', '$rating');";

$dbHost = "mysql17.000webhost.com";
$dbName = "a6286641_usersdb";
$dbTable = "userprofile";
$dbUserName = "a6286641_user";
$dbPassword = "password2014";

$connection = mysql_connect($dbHost, $dbUserName, $dbPassword) or die(mysql_error());
mysql_select_db($dbName) or die(mysql_error());

if (!userEmailExists($userEmail)) {
	$prevId = mysql_insert_id();
	$result = mysql_query($sql);
	$nextId = mysql_insert_id();

	if ($prevId != $nextId) {
		echo "Success";
	} else {
		echo "failed";
	}
} else {
	echo "userExists";
}

function userEmailExists($email) {
	$query = mysql_query("SELECT userEmail FROM userprofile WHERE userEmail='$email'");

	if (mysql_num_rows($query) != 0){
		return true;	  
	}
	else{
		return false;
	}
}


?>