<?php
    include('connection.php');

    $sql = "SELECT * FROM fungicides ";
   
    $result = mysqli_query($conn, $sql);
    $dataarray = array();
    while($row = mysqli_fetch_assoc($result))
    {
        $tarray = array();
		$tarray["status"] = 'success';
        $tarray["id"] = $row['id'];
        $tarray["name"] = $row['name'];
        $tarray["price"] = $row['price'];
        $tarray["mrp"] = $row['mrp'];
        $tarray["pic"] = $row['pic'];
        $tarray["description"] = $row['description'];
        $tarray["stock"] = $row['stock'];
		array_push($dataarray, $tarray);
    }
    header('Content-Type: application/json');
	echo json_encode(array("data"=>$dataarray));
    
?>