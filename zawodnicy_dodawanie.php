<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_d = $_POST['id_d'];
    $imie = $_POST['imie'];
    
    require_once("connect.php");
    
    $query = "INSERT INTO Zawodnicy (id_d, imie) VALUES ('$id_d', '$imie')";
    
    if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Dodano zawodnika!";
        
    } else {
        
        $response['success'] = false;
        $response['message'] = "Wybierz drużynę zawodnika!";
        
    }
    
} else {
    
    $response['success'] = false;
        $response['message'] = "Błąd!";
    
}

echo json_encode($response);
?>