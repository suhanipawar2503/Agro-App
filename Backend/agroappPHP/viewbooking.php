<?php
    include("Connection.php");

    $sql = "SELECT * FROM booking ";
   
    $result = mysqli_query($conn, $sql);
    $dataarray = array();
    while($row = mysqli_fetch_assoc($result))
    {
        $tarray = array();
		$tarray["status"] = 'success';
        $tarray["id"] = $row['id'];
        $tarray["name"] = $row['name'];
        $tarray["email"] = $row['email'];
        $tarray["mobileno"] = $row['mobileno'];
        $tarray["location"] = $row['location'];
        $tarray["payment"] = $row['payment'];
       
		array_push($dataarray, $tarray);
    }
    header('Content-Type: application/json');
	echo json_encode(array("data"=>$dataarray));
    
?>