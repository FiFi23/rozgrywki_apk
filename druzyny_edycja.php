<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_d = $_POST['id_d'];
    $nazwa_d = $_POST['nazwa_d'];
    $wygrane = $_POST['wygrane'];
    $przegrane = $_POST['przegrane'];
    
    require_once("connect.php");
    
    $query = "UPDATE Druzyny SET nazwa_d = '$nazwa_d', wygrane = '$wygrane', przegrane = '$przegrane' WHERE id_d ='$id_d'";
    
  if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Edytowano drużynę!";
        
    } else {
        
        $response['success'] = false;
        $response['message'] = "Nie edytowano!";
        
    }
    
} else {
    
    $response['success'] = false;
        $response['message'] = "Błąd!";
    
}

echo json_encode($response);
    ?>