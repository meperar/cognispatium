<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="theme.css" type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css"
	integrity="">
<style>

.dropbtn {
    background-color: rgba(0,0,0,0);
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
    border-radius: 10px;
}

.dropbtn:hover, .dropbtn:focus {
    background-color: rgba(255,255,255,0.5);
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    overflow: auto;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown a:hover {background-color: #ddd;}

.show {display: block;}








.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #ccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #fff;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}
</style>
</head>

<body>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
		type="text/css">
	<link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css" style="">
	<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="">
		<div class="container">
			<button class="navbar-toggler navbar-toggler-right border-0"
				type="button" data-toggle="collapse" data-target="#navbar13">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			
			
			
			
			<div class="collapse navbar-collapse" id="navbar13">
				<a class="navbar-brand d-none d-md-block" href="hello.htm"> <a
					href="hello.htm"><img src="https://i.imgur.com/xmZULKf.png" width=200 title="source: imgur.com" /></a>
					
					
					
					<div class="dropdown">
					  <button id="btnConsultas" onclick="myFunction()" class="dropbtn">Consultas</button>
					  <ul id="myDropdown" class="dropdown-menu multi-level">
					  	<c:forEach items="${serviciosXAmbito}" var="ambito">
							<li class="dropdown-submenu"><a tabindex="-1">${ambito.key}</a>
								<ul class="dropdown-menu">
									<c:forEach items="${ambito.value}" var="servicio">
										<a class="nav-link" style="color:black;" href="listProf.htm?serviceIdC=${servicio.id}">${servicio.nombre}</a>
									</c:forEach>

								</ul></li>
						</c:forEach>
					  </ul>
					</div>
					
					
					
					<div class="dropdown">
					  <button id="btnProfesionales" onclick="myFunction2()" class="dropbtn">Profesionales</button>
					  <ul id="myDropdown2" class="dropdown-menu multi-level">
					  	<c:forEach items="${serviciosXAmbito}" var="ambito">
							<li class="dropdown-submenu"><a tabindex="-1">${ambito.key}</a>
								<ul class="dropdown-menu">
									<c:forEach items="${ambito.value}" var="servicio">
										<a class="nav-link" style="color:black;" href="ranking.htm?serviceId=${servicio.id}">${servicio.nombre}</a>
									</c:forEach>

								</ul></li>
						</c:forEach>
					  </ul>
					</div>
					
					<script>
					/* When the user clicks on the button, 
					toggle between hiding and showing the dropdown content */
					function myFunction() {
					    //document.getElementById("myDropdown").classList.toggle("show");
					    
					    var x = document.getElementById("myDropdown");
					    if (x.style.display === "none") {
					        x.style.display = "block";
					    } else {
					        x.style.display = "none";
					    }

					    document.getElementById("myDropdown2").style.display = "none";
					     
					}
					
					function myFunction2() {
					    //document.getElementById("myDropdown2").classList.toggle("show");
					    var x = document.getElementById("myDropdown2");
					    if (x.style.display === "none") {
					        x.style.display = "block";
					    } else {
					        x.style.display = "none";
					    }
					    
					    document.getElementById("myDropdown").style.display = "none";
					     
					}
					
					// Close the dropdown if the user clicks outside of it
					window.onclick = function(event) {

					  if (event.target.id.localeCompare("btnProfesionales") != 0
						  && event.target.id.localeCompare("btnConsultas") != 0) {
						  
						  
						  document.getElementById("myDropdown").style.display = "none";
						  document.getElementById("myDropdown2").style.display = "none";
					  }
					}
					</script>
					
					<ul class="navbar-nav">
						<li class="nav-item" style=""><a class="nav-link" href="#">&nbsp;
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></li>

						<c:if test="${usR.apellidos == null}">
							<li class="nav-item"><a class="nav-link"
								href="usersignup.htm">Sign up </a></li>
							<li class="nav-item"><a class="nav-link" href="login.htm">Log In </a></li>
						</c:if>
						<c:if test="${usR.apellidos != null}">
							<c:if test="${(usR.DTYPE).toString().length()==7}">
								<li class="nav-item"><a class="nav-link" href="crearconsultaurgente.htm">Publicar CU</a></li>
								<li class="nav-item"><a class="nav-link" href="crearsolicitudpresupuesto.htm">Pedir Presupuesto</a></li>
								<li class="nav-item"><a class="nav-link" href="misSolicitudes.htm">Mis Solicitudes</a></li>
							</c:if>
							<c:if test="${(usR.DTYPE).toString().length()==11}">
								<li class="nav-item"><a class="nav-link" href="listadosolicitudes.htm">Solicitudes</a></li>
								<li class="nav-item"><a class="nav-link" href="listadoconsultas.htm">Consultas</a></li>
								<li class="nav-item"><a class="nav-link" href="listadoPresupuestos.htm">Presupuestos</a></li>
							</c:if>
							<li class="nav-item"><a class="nav-link" href="historial.htm">Historial</a></li>
							<li class="nav-item"><a class="nav-link" href="bandejamensajes.htm">Bandeja de Mensajes</a></li>
							<li class="nav-item"><a class="nav-link" href="perfil.htm">Perfil</a></li>						
							<li class="nav-item"><a class="nav-link" href="logout.htm">Log Out</a></li>
						</c:if>
						<li class="nav-item">
							<a class="nav-link" href="guiaDeUso.htm">Manual de uso</a>
						</li>
						
					</ul>
			</div>
		</div>
	</nav>
</body>

</html>