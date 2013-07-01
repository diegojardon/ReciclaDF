<?php
	//validamos que la variable no sea null
	//$accion = $_POST["id"]) ? $_POST["id"] : "";
	
	
	//conexion a Base de Datos
	$link = mysql_connect("localhost","u251968_recicla","r3c1cla");
	mysql_select_db("u251968_recicladf",$link);
	
	$result = mysql_query("SELECT idCentro, nombreCentro, papelCentro, plasticoCentro, bateriaCentro, metalCentro, vidrioCentro, latitudCentro, longitudCentro FROM CentroReciclaje",$link);
		while ($filaCentro = mysql_fetch_assoc($result)) {
			$resultado["centro"]["id"] = $filaCentro["idCentro"];
			$resultado["centro"]["nombre"] = $filaCentro["nombreCentro"];
			$resultado["centro"]["material"]["papel"] = $filaCentro["papelCentro"];
			$resultado["centro"]["material"]["plastico"] = $filaCentro["plasticoCentro"];
			$resultado["centro"]["material"]["bateria"] = $filaCentro["bateriaCentro"];
			$resultado["centro"]["material"]["metal"] = $filaCentro["metalCentro"];
			$resultado["centro"]["material"]["vidrio"] = $filaCentro["vidrioCentro"];
			$resultado["centro"]["lat"] = $filaCentro["latitudCentro"];
			$resultado["centro"]["lon"] = $filaCentro["longitudCentro"];
		}
		echo json_encode($resultado)
	
	
	/*function iniciarSesion(){
		$correo = isset($_POST["correo"]) ? $_POST["correo"] : "";
		$query = "INSERT INTO Usuario(`idUsuario`,`correoUsuario`) VALUES (NULL, '$correo')";
		$result = mysql_query($query);
		$resultado["login"] = "ok";
		echo json_encode($resultado);
	}
	
	function consultarCentros(){
	echo "2";
		$result = mysql_query("SELECT idCentro, nombreCentro, papelCentro, plasticoCentro, bateriaCentro, metalCentro, vidrioCentro, latitudCentro, longitudCentro FROM CentroReciclaje",$link);
		while ($filaCentro = mysql_fetch_assoc($result)) {
			$resultado["centro"]["id"] = $filaCentro["idCentro"];
			$resultado["centro"]["nombre"] = $filaCentro["nombreCentro"];
			$resultado["centro"]["material"]["papel"] = $filaCentro["papelCentro"];
			$resultado["centro"]["material"]["plastico"] = $filaCentro["plasticoCentro"];
			$resultado["centro"]["material"]["bateria"] = $filaCentro["bateriaCentro"];
			$resultado["centro"]["material"]["metal"] = $filaCentro["metalCentro"];
			$resultado["centro"]["material"]["vidrio"] = $filaCentro["vidrioCentro"];
			$resultado["centro"]["lat"] = $filaCentro["latitudCentro"];
			$resultado["centro"]["lon"] = $filaCentro["longitudCentro"];
		}
		echo json_encode($resultado)
	}
	
	function detalleCentro(){
		$correo = isset($_POST["centro"]) ? $_POST["centro"] : "";
		result = mysql_query("SELECT calleCentro, coloniaCentro, delegacionCentro, horarioCentro, telefonoCentro, correoCentro FROM CentroReciclaje WHERE idCentro = '".$centro."'",$link) or die(mysql_error());
		while ($filaCentro = mysql_fetch_assoc($result)) {
			$resultado["centro"]["calle"] = $filaCentro["calleCentro"];
			$resultado["centro"]["colonia"] = $filaCentro["coloniaCentro"];
			$resultado["centro"]["delegacion"] = $filaCentro["delegacionCentro"];
			$resultado["centro"]["horario"] = $filaCentro["horarioCentro"];
			$resultado["centro"]["telefono"] = $filaCentro["telefonoCentro"];
			$resultado["centro"]["correo"] = $filaCentro["correoCentro"];
		}	
		echo json_encode($resultado);
	}
	
	function buscarCentrosMaterial(){
		//Se puede hacer en el app sin lanzar la consulta
		result = mysql_query("SELECT idCentro, nombreCentro, papelCentro, plasticoCentro, bateriaCentro, metalCentro, vidrioCentro, latitudCentro, longitudCentro FROM CentroReciclaje WHERE correoUsuario = '".$correo."'",$link) or die(mysql_error());
	}
	
	function agregarCentro(){
	
	
	}
	
	function consultarComentarios(){
		
	}
	
	function agregarComentario(){
	
	}*/
		
	
?>