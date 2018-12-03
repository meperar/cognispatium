<!DOCTYPE html>
<html>

<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
<style>

.tablaFiltros {
  	border-spacing: 20px 5px;
  	border-collapse: separate;
}

.scnd {
  	color: black;
}

.scnd:hover {
  	color: black;
}

.titulosFiltros{
	color: black;
	font-weight: 500;
}

.titulosFiltros:hover{
	color: black;
}


.desplegableMenu {
  	background-color: rgba(0,0,0,0);
  	border-radius: 4px;
  	padding: 5px 10px;
  	border: none;
  	color: white;
  	text-align: center;
  	text-decoration: none;
  	display: inline-block;
  	font-size: 16px;
  	cursor: pointer;
}
.desplegableMenu:focus{
  	outline: none;
  	background-color: rgba(100,100,100,1);
}
.desplegableMenu::-moz-focus-inner {
  
  	border: 0;
}
</style>
</head>

<body>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css" style="">
<jsp:include page="barrasuperior.jsp" flush="true" />
   <div class="">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <p class="text-monospace text-right">${usR.nombre}</p>
          <p class="text-monospace text-right">${usRe.nombre}</p>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5 text-center" style="">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <h1 class="display-1">Bienvenido</h1>
        </div>
      </div>
    </div>
  </div>
  <div class="py-5" style="">
    <div class="container">

      <div class="row">
        <div class="col-md-12">
          	<p class="lead">CogniSpatium es la web donde solucionamos tus problemas. Déjanos tus dudas y las resolveremos enseguida.&nbsp;<br>También disponemos de personal bien formado a tu servicio.<br><br></p>
       	  	<a><img src="https://i.imgur.com/tB4heUa.jpg" title="source: imgur.com" /></a>
        </div>
      </div>
     </div>
  </div>
  
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <div style="background-color: black;">
	  <div  style="margin-left: 100px; margin-right: 100px;">
	  	<br>
	  	<br>
	  	<h2 style="color: white;">Utilidades</h2>
	  	<hr style="border-top: 1px solid #aaaaaa;">
	  	<a style="font-size: 13px; color: white;" href="ayuda.htm">Atención al cliente</a>
	  	&emsp; &emsp; 
		<a style="font-size: 13px; color: white;" href="conoceEquipo.htm">Conoce al equipo</a>
		&emsp; &emsp; 
		<a style="font-size: 13px; color: white;" href="acercaDe.htm">Acerca de</a>
		&emsp; &emsp; 
		<a style="font-size: 13px; color: white;" href="politicaDePrivacidad.htm">Políticas de Privacidad</a>
		&emsp; &emsp; 
		<a style="font-size: 13px; color: white;" href="guiaDeUso.htm">Guía uso</a>
	  	<br>
	  	<br>
	  	<br>
	  </div>
  </div>
  
  
</body>

</html>