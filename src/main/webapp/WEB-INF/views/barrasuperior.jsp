<!DOCTYPE html>
<html>

<head>
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
  <nav class="navbar navbar-expand-md navbar-dark bg-dark" style="">
    <div class="container"> <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar13">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar13"> <a class="navbar-brand d-none d-md-block" href="hello.htm">
        <a href="hello.htm"><img src="https://i.imgur.com/xmZULKf.png" width = 200 title="source: imgur.com" /></a>
        <div class="btn-group"> <button class="desplegableMenu" id="profesional" data-toggle="dropdown" > Profesionales</button>
          <div class="dropdown-menu">
      <table class="tablaFiltros" id="gh-sbc">
        <tbody>
          <tr>
            <td>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Medicina<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href="listaprofesionales.htm">Odontología</a></li>
                <li><a class="scnd" href=#>Pediatría</a></li>
                <li><a class="scnd" href=#>Medicina Familiar</a></li>
                <li><a class="scnd" href=#>Oftalmología</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Deporte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Waterpolo</a></li>
                <li><a class="scnd" href=#>Fútbol</a></li>
                <li><a class="scnd" href=#>Bulletball</a></li>
                <li><a class="scnd" href=#>Baloncesto</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Ciencia</h4>
              <ul>
                <li><a class="scnd" href=#>Física</a></li>
                <li><a class="scnd" href=#>Química</a></li>
                <li><a class="scnd" href=#>Bioquimica</a></li>
                <li><a class="scnd" href=#>Biotecnología</a></li>
              </ul>
            </td>
            <td>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Técnicos<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Electricista</a></li>
                <li><a class="scnd" href=#>Fontanero</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Legislación<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Abogacía</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Artes Marciales<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Taekwondo</a></li>
                <li><a class="scnd" href=#>Aikido</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Cuidados del Hogar<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Limpieza</a></li>
                <li><a class="scnd" href=#>Cuidado de niños</a></li>
              </ul>
            </td>
            <td>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Arte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Música</a></li>
                <li><a class="scnd" href=#>Pintura</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Idiomas<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Inglés</a></li>
                <li><a class="scnd" href=#>Español</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Estética<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Peluquería</a></li>
                <li><a class="scnd" href=#>Barbería</a></li>
              </ul>
            </td>
          </tr>
        </tbody>
      </table>
          </div>
        </div>
        <div class="btn-group"> <button class="desplegableMenu" data-toggle="dropdown"> Consultas</button>
          <div class="dropdown-menu">  
       <table class="tablaFiltros" id="gh-sbc">
        <tbody>
          <tr>
            <td>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Medicina<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Odontología</a></li>
                <li><a class="scnd" href=#>Pediatría</a></li>
                <li><a class="scnd" href=#>Medicina Familiar</a></li>
                <li><a class="scnd" href=#>Oftalmología</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Deporte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Waterpolo</a></li>
                <li><a class="scnd" href=#>Fútbol</a></li>
                <li><a class="scnd" href=#>Bulletball</a></li>
                <li><a class="scnd" href=#>Baloncesto</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Ciencia</h4>
              <ul>
                <li><a class="scnd" href=#>Física</a></li>
                <li><a class="scnd" href=#>Química</a></li>
                <li><a class="scnd" href=#>Bioquimica</a></li>
                <li><a class="scnd" href=#>Biotecnología</a></li>
              </ul>
            </td>
            <td>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Técnicos<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Electricista</a></li>
                <li><a class="scnd" href=#>Fontanero</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Legislación<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Abogacía</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Artes Marciales<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Taekwondo</a></li>
                <li><a class="scnd" href=#>Aikido</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Cuidados del Hogar<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Limpieza</a></li>
                <li><a class="scnd" href=#>Cuidado de niños</a></li>
              </ul>
            </td>
            <td>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Arte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Música</a></li>
                <li><a class="scnd" href=#>Pintura</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Idiomas<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Inglés</a></li>
                <li><a class="scnd" href=#>Español</a></li>
              </ul>
              <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Estética<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
              <ul>
                <li><a class="scnd" href=#>Peluquería</a></li>
                <li><a class="scnd" href=#>Barbería</a></li>
              </ul>
             
            </td>
          </tr>
        </tbody>
      </table>
          </div>
        </div>
        <ul class="navbar-nav">
          <li class="nav-item" style=""> <a class="nav-link" href="#">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  </a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">Log In </a> </li>
          <li class="nav-item"> <a class="nav-link" href="#">Sign up </a> </li>
          <li class="nav-item" ><a class="nav-link" href="crearconsultaurgente.htm">Publicar CU</a></li>
          <li class="nav-item" ><a class="nav-link" href="crearsolicitudpresupuesto.htm">Pedir Presupuesto</a></li>
          <li class="nav-item" ><a class="nav-link" href="listadosolicitudes.htm">Solicitudes</a></li>

        </ul>
      </div>
    </div>
  </nav></body>

</html>