<?php
header("Content-type: application/json; charset=utf-8");

require_once('connect.php');

$query = mysqli_query($conn, "SELECT id_m, id_gosp, wynik_gosp, id_gosc, wynik_gosc, data, Gospo.nazwa_d AS Gospo, Gosc.nazwa_d AS Gosc FROM Mecze 
                      JOIN Druzyny AS Gospo ON (Gospo.id_d = Mecze.id_gosp) JOIN Druzyny AS Gosc ON (Gosc.id_d = Mecze.id_gosc)
                      ORDER BY data DESC");

$response = array();

while( $row = mysqli_fetch_assoc($query) ){
    
    array_push($response,
    array(
        'id_m'         =>$row['id_m'],
        'id_gosp'      =>$row['id_gosp'],
        'nazwa_gosp'   =>$row['Gospo'],
        'id_gosc'      =>$row['id_gosc'],
        'nazwa_gosc'   =>$row['Gosc'],
        'wynik_gosp'   =>$row['wynik_gosp'],
        'wynik_gosc'   =>$row['wynik_gosc'],
        'data'         =>$row['data'])
    );
}

echo json_encode($response);

?>