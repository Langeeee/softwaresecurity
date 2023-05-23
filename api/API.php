<?php
require 'database.php';

if($_GET["API_KEY"]=="Flight booker 1.2 plus edition"){
    switch($_GET["action"]){
        case "get":Get();break;
        case "login":Login();break;
        case "book":Book();break;
        default:break;
    }
}
else{
    echo "wrong Key";

}


function Get(){
    global $conn;
//    echo "1";
    switch($_GET["items"]){
        case "airport":
            $records = $conn->prepare('SELECT * FROM airports WHERE airport like "%'.$_GET['search'].'%" or code like "%'.$_GET['search'].'%"');
            $records->execute();
            $results = $records->fetchAll(PDO::FETCH_ASSOC);
            if(count($results) > 0) {
                $obj=["status"=>"200","statusDescription"=>"Ok"];
                array_unshift($results,$obj);
                $jsonObj= json_encode($results);
            }
            else {
                $obj=["status"=>"440","statusDescription"=>"No matching airports found"];
                $jsonObj="[".json_encode($obj)."]";
            }
            echo $jsonObj;
            break;
        case "flights":
            $records = $conn->prepare('SELECT * FROM flights WHERE substring(date_time_depart,1,10)="'.$_GET['date'].'" or substring(date_time_arriv,1,10)="'.$_GET['date'].'" and airport_from='.$_GET['from'].' and airport_to='.$_GET['to']);
            $records->execute();
            $results = $records->fetchAll(PDO::FETCH_ASSOC);
            if(count($results) > 0) {
                $obj=["status"=>"200","statusDescription"=>"Ok"];
                array_unshift($results,$obj);
                $jsonObj= json_encode($results);
            }
            else {
                $obj=["status"=>"460","statusDescription"=>"No matching flights found"];
                $jsonObj="[".json_encode($obj)."]";
            }
            echo $jsonObj;
    }
}

function Login(){
    global $conn;
//    echo "1";
    $records = $conn->prepare('SELECT * FROM passengers WHERE email="'.$_GET['email'].'" and password= "'.md5($_GET['pass']).'"');
    $records->execute();
    $results = $records->fetchAll(PDO::FETCH_ASSOC);
    // var_dump( $results);
    if(count($results) > 0) {
        $token=md5(rand());
        $sql = "UPDATE passengers SET token='".$token."' where email='".$_GET['email']."'";
        $stmt = $conn->prepare($sql);
//        echo $sql;
        if( $stmt->execute() ) {
            $results[0]["token"]=$token;
        }
        $obj=["status"=>"200","statusDescription"=>"Ok"];
        array_unshift($results,$obj);
        $jsonObj= json_encode($results);
    }
    else {
        $obj=["status"=>"450","statusDescription"=>"No user found"];
        $jsonObj="[".json_encode($obj)."]";
    }
    echo $jsonObj;
}

function Book(){
    global $conn;

    $records = $conn->prepare('SELECT * FROM passengers WHERE id="'.($_GET['passenger']).'" and token="'.($_GET['token']).'"');
    $records->execute();
    $results = $records->fetchAll(PDO::FETCH_ASSOC);
    // var_dump( $results);
    if(count($results) > 0) {
        $sql = "INSERT INTO bookings (passenger, flight,status) VALUES ('" . $_GET['passenger'] . "','" . $_GET['flight'] . "', 'OK')";
        $stmt = $conn->prepare($sql);

        if ($stmt->execute()){
            $obj=["status"=>"200","statusDescription"=>"OK"];
            $jsonObj="[".json_encode($obj)."]";
        }
        else{
            $obj=["status"=>"460","statusDescription"=>"problem with the booking"];
            $jsonObj="[".json_encode($obj)."]";

        }
    }
    else {
        $obj=["status"=>"470","statusDescription"=>"User token problem"];
        $jsonObj="[".json_encode($obj)."]";
    }
    echo $jsonObj;
}


?>


