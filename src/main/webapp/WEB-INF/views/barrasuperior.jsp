<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

.spbutton{
    background:rgba(0, 0, 0, 0);
    color:inherit;
    border:none; 
    padding:1px;
    font: inherit;
    cursor: pointer;
    white-space: nowrap;
}

.spbutton:hover{
	text-decoration:underline;
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





<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="theme.css" type="text/css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">
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
         	 		 <c:forEach items="${med.serviMed}" var="serviM">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviM.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviM.nombre}"/></button></li>
         	 		 </form> 
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Deporte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${dep.serviDep}" var="serviD">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviD.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviD.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Ciencia<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cien.serviCien}" var="serviC">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviC.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviC.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul>    	 		 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Técnicos<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${tec.serviTec}" var="serviT">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviT.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviT.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Legislación<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${leg.serviLeg}" var="serviL">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviL.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviL.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Artes Marciales<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${artm.serviArtM}" var="serviAM">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviAM.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviAM.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Cuidados del Hogar<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cuiho.serviCuiHo}" var="serviCH">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviCH.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviCH.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Arte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${art.serviArt}" var="serviA">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviA.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviA.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Idiomas<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${idio.serviIdio}" var="serviI">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviI.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviI.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Estética<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${est.serviEst}" var="serviE">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceId" name="serviceId" value="${serviE.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviE.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
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
         	 		 <c:forEach items="${med.serviMed}" var="serviM">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviM.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviM.nombre}"/></button></li>
         	 		 </form> 
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Deporte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${dep.serviDep}" var="serviD">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviD.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviD.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Ciencia<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cien.serviCien}" var="serviC">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviC.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviC.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul>    	 		 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Técnicos<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${tec.serviTec}" var="serviT">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviT.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviT.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Legislación<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${leg.serviLeg}" var="serviL">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviL.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviL.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Artes Marciales<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${artm.serviArtM}" var="serviAM">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviAM.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviAM.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Cuidados del Hogar<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cuiho.serviCuiHo}" var="serviCH">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviCH.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviCH.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Arte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${art.serviArt}" var="serviA">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviA.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviA.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Idiomas<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${idio.serviIdio}" var="serviI">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviI.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviI.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Estética<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${est.serviEst}" var="serviE">
         	 		 <form action="#" method="post" class="text-left">
         	 		 <input type="hidden" id="serviceIdC" name="serviceIdC" value="${serviE.id}">
         	 		 <li><button class="spbutton"><c:out value="${serviE.nombre}"/></button></li>
         	 		 </form>
         	 		 </c:forEach>
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
          <li class="nav-item"> <a class="nav-link" href="usersignup.htm">Sign up </a> </li>
          <li class="nav-item" ><a class="nav-link" href="crearconsultaurgente.htm">Publicar CU</a></li>
          <li class="nav-item" ><a class="nav-link" href="crearsolicitudpresupuesto.htm">Pedir Presupuesto</a></li>
          <li class="nav-item" ><a class="nav-link" href="listadosolicitudes.htm">Solicitudes</a></li>
          <li class="nav-item" ><a class="nav-link" href="listadoconsultas.htm">Consultas</a></li>
		  <li class="nav-item" ><a class="nav-link" href="bandejamensajes.htm">Bandeja de Mensajes</a></li>
		   <li class="nav-item" ><a class="nav-link" href="misSolicitudes.htm">Mis Solicitudes</a></li>
		  
        </ul>
      </div>
    </div>
  </nav></body>

</html>