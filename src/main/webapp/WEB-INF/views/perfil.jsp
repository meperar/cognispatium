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
</style>
</head>

<body>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css" style="">
<jsp:include page="barrasuperior.jsp" flush="true" />

<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<table class="perfil">
						<tbody>	
							<tr>
								<td rowspan="2">
									<img src="https://i.imgur.com/Yiay52m.png" width = 256 title="source: imgur.com" /><br>
									<c:if test="${boolModel.esProfesional}">
										<a>&emsp;&emsp;&emsp;<c:forEach begin="1" end="5" varStatus="loop">
	   										<img src="https://i.imgur.com/gYOuM8u.png" width = 25 title="source: imgur.com" />
										</c:forEach></a>
									</c:if>
									<br>
									<br>
									<h3><b>${model.usuario.nombre} ${model.usuario.apellidos}</b></h3>
									<a><font size="+1">Edad: N/A</font></a><br>
									<a><font size="+1">DNI: ${model.usuario.dni}</font></a><br>
									<a><font size="+1">E-mail: ${model.usuario.email}</font></a><br>
									<a><font size="+1">Teléfono: ${model.usuario.telefono}</font></a>
								</td>
								<td>&nbsp;</td>
								<td>
									<div class="subrayadoGordo">
										<h3><b>Consultas</b></h3>
									</div>
									<br>
									<div style="height:400px; overflow-y: scroll; ">
										<ul style="list-style-type: none;">
											<c:choose>
											    <c:when test="${intModel.numConsultas > 0}">
											        <c:forEach items="${model.consultas}" var="cons">	
														<li>
															<h4><b>${cons.titulo}</b></h4>
															<div class="subrayadoFino" style="overflow-x: hidden;">
																${cons.descripcion}
																<br>
																<br>
															</div>
														</li>
													</c:forEach>
													
											    </c:when>    
											    <c:otherwise>
											        <li>
														<h4><b>Aun no ha realizado ninguna consulta&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</b></h4>
													</li>
											    </c:otherwise>
											</c:choose>
										</ul>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
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