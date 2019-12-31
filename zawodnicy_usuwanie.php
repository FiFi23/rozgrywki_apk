<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_z = $_POST['id_z'];
    
    
    require_once("connect.php");
    
    $query = "DELETE FROM Zawodnicy WHERE id_z='$id_z' ";
    
  if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Usunięto zawodnika!";
        
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