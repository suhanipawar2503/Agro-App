<?php

include('connection.php');

$name = $_POST['name'];
$email = $_POST['email'];
$location = $_POST['location'];
$mobileno = $_POST['mobileno'];
$payment = $_POST['payment'];

$query = "INSERT INTO booking (name, email, location, mobileno, payment) VALUES
        ('$name', '$email', '$location','$mobileno', '$payment')";

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