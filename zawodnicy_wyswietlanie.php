<?php
header("Content-type: application/json; charset=utf-8");

require_once('connect.php');

$query = mysqli_query($conn, "SELECT Zawodnicy.id_z, Zawodnicy.imie, Druzyny.id_d, Druzyny.nazwa_d FROM Druzyny, Zawodnicy WHERE Zawodnicy.id_d = Druzyny.id_d ORDER BY imie ASC");

$response = array();

while( $row = mysqli_fetch_assoc($query) ){
    
    array_push($response,
    array(
        'id_z'       =>$row['id_z'],
        'id_d'       =>$row['id_d'],
        'nazwa_d'    =>$row['nazwa_d'],
        'imie'       =>$row['imie'])
    );
}

echo json_encode($response);

?>