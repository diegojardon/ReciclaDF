<?php
	//validamos que la variable no sea null
	//GET para pruebas
	$accion = isset($_GET["idOp"]) ? $_GET["idOp"] : "";
	//$accion = isset($_POST["idOp"]) ? $_POST["idOp"] : "";
		
	//conexion a Base de Datos
	$link = mysql_connect("localhost","u251968_recicla","r3c1cla");
	mysql_select_db("u251968_recicladf",$link);
	
	switch($accion){
		case "100":
			//login
		break;
		case "101":
			//regresar todos los id's de los centros
			consultarCentros();
		break;
		case "102":
			//regresar todos los datos de un centro por id
			 consultarDatosCentro();
		break;
		case "103":
			//insertar centro de reciclaje
			agregarCentro();
		break;
		case "104":
			//obtener de 5 en 5 comentarios del centro de reciclaje
			consultarComentarios();
		break;
		case "105":
			//Obtener promedio rating por id de centro de reciclaje
			obtenerRanking();
		break;
		case "106":
			//Insertar comentario
			agregarComentario();
		break;
		case "107":
			obtenerCentroMasCercano();
		break;
		default:
			echo "default";
		break;
	}
	
	/*function iniciarSesion(){
		$correo = isset($_POST["correo"]) ? $_POST["correo"] : "";
		$query = "INSERT INTO Usuario(`idUsuario`,`correoUsuario`) VALUES (NULL, '$correo')";
		$result = mysql_query($query);
		$resultado["login"] = "ok";
		echo json_encode($resultado);
	}*/
	
	function consultarCentros(){
		$result = mysql_query("SELECT idCentro FROM CentroReciclaje",$link);
		while ($filaCentro = mysql_fetch_assoc($result)) {
			$resultado["id"] = $filaCentro["idCentro"];
		}
		echo json_encode($resultado);
	}
	
	
	function consultarDatosCentro(){
		$idCentro = $_POST['idCentro'];
		$result = mysql_query("SELECT nombreCentro, delegacionCentro, horarioCentro, telefonoCentro, correoCentro, papelCentro, plasticoCentro, bateriaCentro, metalCentro, vidrioCentro, latitudCentro, longitudCentro FROM CentroReciclaje WHERE idCentro = '".$idCentro."'",$link);
		while ($filaCentro = mysql_fetch_assoc($result)) {
			$resultado["nombre"] = $filaCentro["nombreCentro"];
			$resultado["delegacion"] = $filaCentro["delegacionCentro"];
			$resultado["horario"] = $filaCentro["horarioCentro"];
			$resultado["telefono"] = $filaCentro["telefonoCentro"];
			$resultado["correo"] = $filaCentro["correoCentro"];
			$resultado["material"]["papel"] = $filaCentro["papelCentro"];
			$resultado["material"]["plastico"] = $filaCentro["plasticoCentro"];
			$resultado["material"]["bateria"] = $filaCentro["bateriaCentro"];
			$resultado["material"]["metal"] = $filaCentro["metalCentro"];
			$resultado["material"]["vidrio"] = $filaCentro["vidrioCentro"];
			$resultado["lat"] = $filaCentro["latitudCentro"];
			$resultado["lon"] = $filaCentro["longitudCentro"];
		}
		echo json_encode($resultado);
	}
	
	function agregarCentro(){
	
		$nombreCentro = $_POST['nombreCentro'];
		$delegacionCentro = $_POST['delegacionCentro'];
		$horarioCentro = $_POST['horarioCentro'];
		$telefonoCentro = $_POST['telefonoCentro'];
		$correoCentro = $_POST['correoCentro'];
		$papelCentro = $_POST['papelCentro'];
		$plasticoCentro = $_POST['plasticoCentro'];
		$bateriaCentro = $_POST['bateriaCentro'];
		$metalCentro = $_POST['metalCentro'];
		$vidrioCentro = $_POST['vidrioCentro'];
		$latitudCentro = $_POST['latitudCentro'];
		$longitudCentro = $_POST['longitudCentro'];
		
		$query = "INSERT INTO CentroReciclaje (`idCentro`,`nombreCentro`,`delegacionCentro`,`horarioCentro`,`telefonoCentro`,`correoCentro`,`papelCentro`,`plasticoCentro`,`bateriaCentro`,`metalCentro`,`vidrioCentro`,`latitudCentro`,`longitudCentro`) 
				  VALUES (NULL, '$nombCentro', '$delegacionCentro', '$horarioCentro', '$telefonoCentro', '$correoCentro', '$papelCentro', '$plasticoCentro' , '$bateriaCentro', '$metalCentro', '$vidrioCentro', '$latitudCentro', '$longitudCentro')";
		$result = mysql_query($query);	
		echo json_encode($resultado);		
	}
	
	function consultarComentarios(){
		//PENDIENTE
	}
	
	function obtenerRanking(){
		//PENDIENTE
	}
	
	function agregarComentario(){
		//PENDIENTE
	}
	
	function obtenerCentroMasCercano(){
		$latitud = isset($_POST['miLat']) ? $_POST['miLat'] : "";
		$longitud = isset($_POST['miLon']) ? $_POST['miLon'] : "";
		
		//Calculamos la distancia hacia todos los centros
		if(strcmp($latitud,"") != 0){
			//PENDIENTE
		}
	
	}
		
	
?>