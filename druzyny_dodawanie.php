<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $nazwa_d = $_POST['nazwa_d'];
    $wygrane = $_POST['wygrane'];
    $przegrane = $_POST['przegrane'];
    
    require_once("connect.php");
    
    $query = "INSERT INTO Druzyny (nazwa_d, wygrane, przegrane) VALUES ('$nazwa_d', '$wygrane', '$przegrane')";
    
    if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Dodano drużynę!";
        
    } else {
        
        $response['success'] = false;
        $response['message'] = "Nie dodano!";
        
    }
    
} else {
    
    $response['success'] = false;
        $response['message'] = "Błąd!";
    
}

echo json_encode($response);
?>