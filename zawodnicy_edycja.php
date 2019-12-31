<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_z = $_POST['id_z'];
    $id_d = $_POST['id_d'];
    $imie = $_POST['imie'];
    
    require_once("connect.php");
    
    $query = "UPDATE Zawodnicy SET id_d = '$id_d', imie = '$imie' WHERE id_z ='$id_z'";
    
  if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Edytowano zawodnika!";
        
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