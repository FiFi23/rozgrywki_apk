<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_d = $_POST['id_d'];
    
    
    require_once("connect.php");
    
    $query = "DELETE FROM Druzyny WHERE id_d='$id_d' ";
    
  if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Usunięto drużynę!";
        
    } else {
        
        $response['success'] = false;
        $response['message'] = "Nie usunięto!";
        
    }
    
} else {
    
    $response['success'] = false;
        $response['message'] = "Błąd!";
    
}

echo json_encode($response);
    ?>