<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
<style>

.listado {
 	width: 100%;
  	margin-bottom: 1.5rem;
  	background-color: transparent;}
  
  	.listado th,
  	.listado td {
    	padding: 0.75rem;
    	vertical-align: top;
    	border-bottom: 1px solid lightgrey; 
  	}
    
  	.listado thead th {
    	vertical-align: bottom;
   	 	border-bottom: 2px solid black; 
 	}
    
  	.listado tbody + tbody {
    	border-top: 2px solid lightgrey; 
   	}
    
  	.listado .listado {
    	background-color: white; 
   	}
   	
   	
.perfil {
 	width: 100%;
  	margin-bottom: 1.5rem;
  	background-color: transparent; 
    }
  
  	.perfil th,
  	.perfil td {
    	padding: 0.75rem;
    	vertical-align: top; 
  	}
    
  	.perfil thead th {
    	vertical-align: bottom;
   	 	border-bottom: 2px solid black; 
 	}
    
  	.perfil .perfil {
    	background-color: white; 
   	}
   	
.subrayadoGordo {
    	vertical-align: bottom;
   	 	border-bottom: 2px solid black; 
}

.subrayadoFino {
    	vertical-align: bottom;
   	 	border-bottom: 1px solid lightgrey; 
   	 	margin-bottom: 20px;
}
   	
   	
/* width */
::-webkit-scrollbar {
    width: 5px;
    heigth: 5px;
}

/* Track */
::-webkit-scrollbar-track {
    border-radius: 1px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
    background: black; 
    border-radius: 1px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555555; 
}


/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

.open-button-slim {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  bottom: 23px;
  right: 28px;
  width: 140px;
}

/* The popup form - hidden by default */
.form-popup {
  display: none;
  width: 400px;
  position: absolute;
  top: 0px;
  left: 400px;
  border: 3px solid #f1f1f1;
  z-index: 100;
}

/* Add styles to the form container */
.form-container {
  max-width: 500px;
  padding: 7px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
  width: inherit;
  padding: 5px;
  margin: 20px 10px 10px 10px;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit/login button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 10px 10px;
  border: none;
  cursor: pointer;
  width: 187px;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}



.rightAlign {
    text-align: right;
    padding: 22px 0;

}
</style>
</head>

<body>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css" style="">
<jsp:include page="barrasuperior.jsp" flush="true" />

<c:if test="${boolModel.errorUsername}">
	<br>
	<div style="text-align: center; color: red;"><h3>ERROR: El apodo ya está en uso.</h3></div>
</c:if>
<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<table class="perfil">
						<tbody>	
							<tr>
								<td width="25%"></td>
								<c:if test="${!boolModel.esProfesional}">
									<td width="12%"></td>
								</c:if>
								<td>
									<img src="https://i.imgur.com/Yiay52m.png" width = 256 title="source: imgur.com" /><br>
									<c:if test="${boolModel.esProfesional}">
										<a>&emsp;&emsp;&emsp;&emsp;&emsp;
											<c:forEach begin="1" end="${intModel.valoracion}" varStatus="loop">
		   										<img src="https://i.imgur.com/rhSk7m7.png" width = 26 title="source: imgur.com" />
											</c:forEach>
											<c:forEach begin="1" end="${5 - intModel.valoracion}" varStatus="loop">
		   										<img src="https://i.imgur.com/hYfF8io.png" width = 26 title="source: imgur.com" />
											</c:forEach>
										</a>
									</c:if>
									<br>
									<br>
									<h3><b>${model.usuario.nombre} ${model.usuario.apellidos}</b></h3>
									<a><font size="+1">Apodo: ${model.registro.username}</font></a><br>
									<a><font size="+1">Edad: ${model.usuario.edad}</font></a><br>
									<a><font size="+1">DNI: ${model.usuario.dni}</font></a><br>
									<a><font size="+1">E-mail: ${model.usuario.email}</font></a><br>
									<a><font size="+1">Teléfono: ${model.usuario.telefono}</font></a><br>
									<br>
									<button class="open-button" onclick="openForm()">Editar perfil</button>
									<br>
									<button class="open-button" onclick="openForme()">Eliminar Perfil</button>
									<br>
									<button class="open-button" onclick="openFormDesac()">Desactivar Perfil</button>
									
									<div class="form-popup" id="myFormAddS">
										<form action="#" method="post" class="form-container">
											<input type="hidden" id="addSid" name="addSid" value="${model.usuario.id}">
											
											<div style="text-align: center">Seleccione un servicio a añadir</div>
											
											<div class="row">
												<br> 
												<select name='servicio'>
												 
													<c:set var="servId" value="${model.serviciId}" />
													
													<c:if test="${servId == null}">
														<option disabled selected>-- Seleccione un servicio --</option>
													</c:if>
													
													<c:forEach items="${model.allServices}" var="servicio">
														<c:choose>
															<c:when test="${servId == null}">
																<option value="${servicio.id}" label="${servicio.nombre}"></option>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${servId == servicio.id}">
																		<option value="${servicio.id}" label="${servicio.nombre}"
																			selected></option>
																	</c:when>
																	<c:otherwise>
																		<option value="${servicio.id}" label="${servicio.nombre}"></option>
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select> 
											</div>
											<div style="display: inline-block;">
												<button type="submit" class="btn">Aceptar</button> 
												<button type="button" class="btn cancel" onclick="closeFormAddS()">Cancelar</button>
											</div>
										</form>
									</div>
									
									<div class="form-popup" id="myFormQuitarServicio">
										<form action="#" method="post" class="form-container">
											<input type="hidden" id="quitarServicio" name="quitarServicio" value="${model.usuario.id}">
											
											<div style="text-align: center">Seleccione un servicio a eliminar</div>
											
											<div class="row">
												<br> 
												<select name='servicioAQuitar'>
												 
													
													<c:if test="${servicioId == null}">
														<option disabled selected>-- Seleccione un servicio --</option>
													</c:if>
													
													<c:forEach items="${model.servicios}" var="servicioAQuitar">
														<c:choose>
															<c:when test="${servicioId == null}">
																<option value="${servicioAQuitar.id}" label="${servicioAQuitar.nombre}"></option>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${servicioId == servicio.id}">
																		<option value="${servicioAQuitar.id}" label="${servicioAQuitar.nombre}"
																			selected></option>
																	</c:when>
																	<c:otherwise>
																		<option value="${servicioAQuitar.id}" label="${servicioAQuitar.nombre}"></option>
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</select> 
											</div>
											<div style="display: inline-block;">
												<button type="submit" class="btn">Aceptar</button> 
												<button type="button" class="btn cancel" onclick="closeFormQuitarServicio()">Cancelar</button>
											</div>
										</form>
									</div>
									
									<div class="form-popup" id="myForme">
										<form action="#" method="post" class="form-container">
											<input type="hidden" id="usridE" name="usridE" value="${model.usuario.id}">
											<div style="text-align: center">No podrás recuperar tus datos, ¿proceder?</div>
											<div style="display: inline-block;">
												<button type="submit" class="btn">Aceptar</button> 
												<button type="button" class="btn cancel" onclick="closeForme()">Cancelar</button>
											</div>
										</form>
									</div>
									
									<div class="form-popup" id="myFormDesac">
										<form action="#" method="post" class="form-container">
											<input type="hidden" id="desacId" name="desacId" value="${model.usuario.id}">
											<div style="text-align: center">Se procederá a desactivar la cuenta, ¿está seguro de esto?</div>
											<div style="display: inline-block;">
												<button type="submit" class="btn">Aceptar</button> 
												<button type="button" class="btn cancel" onclick="closeFormDesac()">Cancelar</button>
											</div>
										</form>
									</div>
									
									
									
									
									<div class="form-popup" id="myForm">
									  	<form action="#" onsubmit="return validarCampos();" method="post" class="form-container">
										    <div style="text-align: center"><h1>Editar Perfil</h1></div>
											<table class="perfil">
												<tbody>	
													<tr>
														<td><div class="rightAlign"><b>Nombre:</b></div></td>  <td><input type="text" value="${model.usuario.nombre}" name="nombre" id="nombre" required></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>Apellidos:</b></div></td>  <td><input type="text" value="${model.usuario.apellidos}" name="apellidos" id="apellidos" required></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>Apodo:</b></div></td>  <td><input type="text" value="${model.registro.username}" name="apodo" id="apodo" required></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>Edad:</b></div></td>  <td><input type="text" value="${model.usuario.edad}" name="edad" id="edad" required><a id="errorEdad" style="color: red;"></a></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>DNI:</b></div></td>  <td><input type="text" value="${model.usuario.dni}" name="dni" id="dni" required><a id="errorDni" style="color: red;"></a></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>E-mail:</b></div></td>  <td><input type="text" value="${model.usuario.email}" name="email" id="email" required><a id="errorEmail" style="color: red;"></a></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>Teléfono:</b></div></td>  <td><input type="text" value="${model.usuario.telefono}" name="telefono" id="telefono" required><a id="errorTele" style="color: red;"></a></td>
													</tr>
													<tr>
														<td><div class="rightAlign"><b>Contraseña:</b></div></td>  <td><input type="text" value="${model.registro.contraseña}" name="contrasena" id="contrasena" required></td>
													</tr>
												</tbody>
											</table>
											
											<div style="display: inline-block;"><button type="submit" class="btn">Guardar</button> <button type="button" class="btn cancel" onclick="closeForm()">Cancelar</button></div>
									  	</form>
									</div>
									
									<script>
									function openFormAddS() {
									    document.getElementById("myFormAddS").style.display = "block";
									}
									
									function closeFormAddS() {
									    document.getElementById("myFormAddS").style.display = "none";
									}
									
									function openForme() {
									    document.getElementById("myForme").style.display = "block";
									}
									
									function closeForme() {
									    document.getElementById("myForme").style.display = "none";
									}
									
									function openForm() {
									    document.getElementById("myForm").style.display = "block";
									}
									
									function closeForm() {
									    document.getElementById("myForm").style.display = "none";
									}
									
									function openFormDesac(){
										document.getElementById("myFormDesac").style.display = "block";
									}
									

									function closeFormDesac() {
									    document.getElementById("myFormDesac").style.display = "none";
									}
									
									function openFormQuitarServicio(){
										
										document.getElementById("myFormQuitarServicio").style.display = "block";
									}
									
									function closeFormQuitarServicio() {
									    document.getElementById("myFormQuitarServicio").style.display = "none";
									}
									
									function validarCampos(){
										var valido = true;
										

										var campoEdad = document.getElementById("edad");
										var campoDni = document.getElementById("dni");
										var campoEmail = document.getElementById("email");
										var campoTelefono = document.getElementById("telefono");
										var campoContrasena = document.getElementById("contrasena");
										
										var errorEdad = document.getElementById("errorEdad");
										var errorDni = document.getElementById("errorDni");
										var errorEmail = document.getElementById("errorEmail");
										var errorTele = document.getElementById("errorTele");

										var edad = campoEdad.value;
										var dni = campoDni.value;
										var email = campoEmail.value;
										var telefono = campoTelefono.value;
										var contrasena = campoContrasena.value;
										

										if(isNaN(edad)){
											campoEdad.style.border = "2px solid red";
											errorEdad.innerHTML = "Tiene que ser un número.";
											valido = false;
										}else{
											campoEdad.style.border = "none";
											errorEdad.innerHTML = "";
										}

										var pattern = /[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]|[A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]/i;
										if(dni.search(pattern) != 0 || dni.length != 9){
											campoDni.style.border = "2px solid red";
											errorDni.innerHTML = "Patrones válidos: 12345678A o A1234567A.";
											valido = false;
										}else{
											campoDni.style.border = "none";
											errorDni.innerHTML = "";
										}
										

										if(email.indexOf('@') <= -1){
											campoEmail.style.border = "2px solid red";
											errorEmail.innerHTML = "No es una dirección válida.";
											valido = false;
										}else{
											campoEmail.style.border = "none";
											errorEmail.innerHTML = "";
										}
										
										if(telefono.length != 9){
											errorTele.innerHTML = "Tiene que tener 9 dígitos.";
											campoTelefono.style.border = "2px solid red";
											valido = false;
										}else if(isNaN(telefono)){
											errorTele.innerHTML = "Solo pueden haber números.";
											campoTelefono.style.border = "2px solid red";
											valido = false;
										}else{
											campoTelefono.style.border = "none";
											errorTele.innerHTML = "";
										}
										
										return valido;
									}
									</script>
									

								</td>								
								<c:if test="${boolModel.esProfesional}">

									<td width="300px">
										<br>
										<br>
										<br>
										<br>
										<br>
										<br>
										<br>
										<div class="subrayadoGordo">
											<h1><b>Servicios</b></h1>
										</div>
										<br>
										<div style="height:400px; width:100px">
											<ul style="list-style-type: none;">

										        <c:forEach items="${model.servicios}" var="ser">	
													<li><h2>${ser.nombre}</h2></li>
												</c:forEach>

											</ul>
										</div>
										<button class="open-button-slim" onclick="openFormAddS()">Añadir servicio</button>
										<button class="open-button-slim" onclick="openFormQuitarServicio()">Quitar servicio</button>
									</td>
									<td width="20%"></td>
								</c:if>
							</tr>
						</tbody>
					</table>
					<br>
					<br>
					<br>
					<c:if test="${boolModel.esProfesional}">
						<div class="subrayadoGordo">
							<h3><b>Certificados</b></h3>
						</div>
						<br>
						<div style="height:130px; overflow-x: scroll; white-space: nowrap;">
							<a>&nbsp;<c:forEach begin="1" end="5" varStatus="loop">
								&nbsp;<img src="https://i.imgur.com/nHcoCaG.png" height = 100 title="source: imgur.com" />Master en Física &nbsp;
							</c:forEach></a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

  	
  
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>

</html>