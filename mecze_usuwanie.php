<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_m = $_POST['id_m'];
    
    
    require_once("connect.php");
    
    $query = "DELETE FROM Mecze WHERE id_m='$id_m' ";
    
  if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Usunięto mecz!";
        
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