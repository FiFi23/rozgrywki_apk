<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $id_m        = $_POST['id_m'];
    $id_gosp     = $_POST['id_gosp'];
    $id_gosc     = $_POST['id_gosc'];
    $wynik_gosp  = $_POST['wynik_gosp'];
    $wynik_gosc  = $_POST['wynik_gosc'];
    $data        = $_POST['data'];
    
    require_once("connect.php");
    
    $query = "UPDATE Mecze SET id_gosp = '$id_gosp', id_gosc = '$id_gosc', wynik_gosp = '$wynik_gosp', wynik_gosc = '$wynik_gosc', data = '$data' WHERE id_m ='$id_m'";
    
  if ( mysqli_query($conn, $query) ) {
        
        $response['success'] = true;
        $response['message'] = "Edytowano mecz!";
        
    } else {
        
        $response['success'] = false;
        $response['message'] = "Nie edytowano meczu. Proszę wybrać drużyny!";
        
    }
    
} else {
    
    $response['success'] = false;
        $response['message'] = "Błąd!";
    
}

echo json_encode($response);
    ?>