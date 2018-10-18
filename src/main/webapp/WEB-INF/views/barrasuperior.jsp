<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
         	 		 <li><a class="scnd" href=#>hola</a></li>
         	 		 <c:forEach items="${med.serviMed}" var="serviM">
         	 		 <li><c:out value="${serviM.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Deporte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${dep.serviDep}" var="serviD">
         	 		 <li><c:out value="${serviD.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Ciencia<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cien.serviCien}" var="serviC">
         	 		 <li><c:out value="${serviC.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul>    	 		 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Técnicos<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${tec.serviTec}" var="serviT">
         	 		 <li><c:out value="${serviT.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Legislación<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${leg.serviLeg}" var="serviL">
         	 		 <li><c:out value="${serviL.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Artes Marciales<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${artm.serviArtM}" var="serviAM">
         	 		 <li><c:out value="${serviAM.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Cuidados del Hogar<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cuiho.serviCuiHo}" var="serviCH">
         	 		 <li><c:out value="${serviCH.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Arte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${art.serviArt}" var="serviA">
         	 		 <li><c:out value="${serviA.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Idiomas<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${idio.serviIdio}" var="serviI">
         	 		 <li><c:out value="${serviI.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Estética<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${est.serviEst}" var="serviE">
         	 		 <li><c:out value="${serviE.nombre}"/></li>
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
         	 		 <li><a class="scnd" href=#>hola</a></li>
         	 		 <c:forEach items="${med.serviMed}" var="serviM">
         	 		 <li><c:out value="${serviM.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Deporte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${dep.serviDep}" var="serviD">
         	 		 <li><c:out value="${serviD.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Ciencia<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cien.serviCien}" var="serviC">
         	 		 <li><c:out value="${serviC.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul>    	 		 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Técnicos<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${tec.serviTec}" var="serviT">
         	 		 <li><c:out value="${serviT.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Legislación<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${leg.serviLeg}" var="serviL">
         	 		 <li><c:out value="${serviL.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Artes Marciales<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${artm.serviArtM}" var="serviAM">
         	 		 <li><c:out value="${serviAM.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Cuidados del Hogar<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${cuiho.serviCuiHo}" var="serviCH">
         	 		 <li><c:out value="${serviCH.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 </td>
         	 		 <td>
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Arte<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${art.serviArt}" var="serviA">
         	 		 <li><c:out value="${serviA.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Idiomas<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${idio.serviIdio}" var="serviI">
         	 		 <li><c:out value="${serviI.nombre}"/></li>
         	 		 </c:forEach>
         	 		 </ul> 
         	 		 <h4 class="gh-sbc-parent"><a title="" href=# class="titulosFiltros">Estética<i class="gh-sbc-h3i gh-sprRetina"></i></a></h4>
         	 		 <ul>
         	 		 <c:forEach items="${est.serviEst}" var="serviE">
         	 		 <li><c:out value="${serviE.nombre}"/></li>
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
          <li class="nav-item"> <a class="nav-link" href="#">Sign up </a> </li>
          <li class="nav-item" ><a class="nav-link" href="crearconsultaurgente.htm">Publicar CU</a></li>
          <li class="nav-item" ><a class="nav-link" href="crearsolicitudpresupuesto.htm">Pedir Presupuesto</a></li>
          <li class="nav-item" ><a class="nav-link" href="listadosolicitudes.htm">Solicitudes</a></li>
		  <li class="nav-item" ><a class="nav-link" href="pagoTarjeta.htm">Pagar</a></li>
        </ul>
      </div>
    </div>
  </nav></body>

</html>