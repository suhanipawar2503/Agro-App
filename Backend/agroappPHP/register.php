<?php

include('connection.php');

$name = $_POST['name'];
$email = $_POST['email'];
$city = $_POST['city'];
$mobileno = $_POST['mobileno'];
$password = $_POST['password'];

$query = "INSERT INTO users (name, email, city, mobileno, password) VALUES
        ('$name', '$email', '$city','$mobileno', '$password')";

        // echo $query;
        // exit;

        $result = mysqli_query($conn, $query);

        $tarray = array();

        if($result)
        {
            $tarray['status'] = "success";
        }
        else
        {
            $tarray['status'] = "failed";
        }
        header('Content-Type:application/json');
        echo json_encode($tarray);
?>