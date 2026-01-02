<?php
include('Connection.php');
{
    $email = $_POST['email'];
    $password = $_POST['password'];

    if($email == "admin@gmail.com")
    {
        if($email == "admin@gmail.com" && $password=="123")
        {
        $tarray['status'] = "success";
        $tarray['id']= 0;
        $tarray['name'] = "Admin";
        $tarray['usertype'] = "admin";
        }
    }
    else
    {
    
        $query = "SELECT * FROM users WHERE email = '$email' AND password = '$password'";
        $result = mysqli_query($conn, $query);
        $tarray =array();
        if(mysqli_num_rows($result) > 0 )
        {
            $row = mysqli_fetch_assoc($result);
            $tarray['status'] = "success";
            $tarray['id']=$row['id'];
            $tarray['name'] = $row['name'];
            $tarray['usertype'] = "user";
        }
        else
        {
            $tarray['status'] = "failed";
        }
    }
    header('Content-Type: application/json');
    echo json_encode($tarray);
}
?>
