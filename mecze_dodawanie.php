<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_gosp = $_POST['id_gosp'];
    $id_gosc = $_POST['id_gosc'];
    $wynik_gosp = $_POST['wynik_gosp'];
    $wynik_gosc = $_POST['wynik_gosc'];
    $data = $_POST['data'];
    
    require_once("connect.php");
    
    $query = "INSERT INTO Mecze (id_gosp, id_gosc, wynik_gosp, wynik_gosc, data) VALUES ('$id_gosp', '$id_gosc', '$wynik_gosp', '$wynik_gosc', '$data')";
    
    if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Dodano mecz!";
        
    } else {
        
        $response['success'] = false;
        $response['message'] = "Proszę wybrać drużyny!";
        
    }
    
} else {
    
    $response['success'] = false;
        $response['message'] = "Błąd!";
    
}

echo json_encode($response);
?>