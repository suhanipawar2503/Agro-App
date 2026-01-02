<?php
    include('connection.php');   


    if(isset($_POST['name']))
    {
    $name = $_POST["name"];
    $mrp = $_POST["mrp"];
    $price = $_POST["price"];
    $stock = $_POST["stock"];
    $description = $_POST["description"];
    $filename ="";

    date_default_timezone_set('Asia/Kolkata');

    $now = date("Y-m-d h:i:sa");

    
    if(isset($_POST['image']))
        {
            $base64Image = $_POST['image'];

            // Decode the base64-encoded image data
            $imageData = base64_decode($base64Image);

            // Set the path where you want to save the image
            $uploadPath = 'farmmachinespics/';

            // Create the 'uploads' directory if it doesn't exist
            if (!file_exists($uploadPath)) {
                mkdir($uploadPath, 0777, true);
            }

            // Generate a unique filename for the image
            $filename = uniqid() . '.jpg';

            // Set the complete path to save the image
            $filePath = $uploadPath . $filename;

            // Save the image to the specified path
            $success = file_put_contents($filePath, $imageData);
        }
    
        $sql = "INSERT INTO farmmachines(name, stock, mrp, price, pic, description)";
        $sql .=" VALUES ('$name' ,'$stock' , '$mrp', '$price','$filename', '$description')";       
        
        $result  = mysqli_query($conn, $sql); 
        $tarray = array();
        if(!$result)
        {
            $tarray["status"] = 'failed';
            array_push($tarray);
        }
        else
        {
            $tarray["status"] = 'success';
            array_push($tarray);
        }
   
    }
    else{
        $tarray["status"] = 'failed';
            array_push($tarray);
    }
    
    
    header('Content-Type: application/json');
	echo json_encode($tarray);
    
?>