<?php
header("Content-type: application/json; charset=utf-8");

require_once('connect.php');

$query = mysqli_query($conn, "SELECT * FROM Druzyny ORDER BY wygrane DESC");

$response = array();

while( $row = mysqli_fetch_assoc($query) ){
    
    array_push($response,
    array(
        'id_d'       =>$row['id_d'],
        'nazwa_d'    =>$row['nazwa_d'],
        'wygrane'    =>$row['wygrane'],
        'przegrane'  =>$row['przegrane'])
    );
}

echo json_encode($response);

?>