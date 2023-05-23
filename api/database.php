<?php
$server = $_ENV["DB_SERVER"];
$username = $_ENV["DB_USER"];
$password = $_ENV["DB_PASSWORD"];
$database = $_ENV["DB_NAME"];

try{
	$conn = new PDO("mysql:host=$server;dbname=$database;", $username, $password);
} catch(PDOException $e){
	die( "Connection failed: " . $e->getMessage());
}
