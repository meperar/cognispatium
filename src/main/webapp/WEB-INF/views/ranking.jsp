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
  	background-color: transparent; }
  
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
				<table class="listado">
					<thead>
						<tr>
							<th colspan="2"><h3>Profesionales - ${model.servicioElegido.ambito} (${model.servicioElegido.nombre})</h3></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${model.profesionales}" var="profs">	
							<tr>
								<td style="border: 0px"> &emsp; </td>
								<td>
									<h4>
									<c:if test="${profs.getFotos() == null}"><img src="https://i.imgur.com/Yiay52m.png" width = 128 title="source: imgur.com" /><br></c:if>
									<c:if test="${profs.getFotos() != null}"><img src='data:image/png;base64,${profs.getFotos()}' alt="\" height="115" width="128"><br></c:if>
									 ${profs.getNombre()} ${profs.getApellidos()}</h4>
									<c:choose>
									<c:when test="${profs.getValoracionMedia() >= 0}">
										<h4>&nbsp;
											<c:forEach begin="1" end="${profs.getValoracionMedia()}" varStatus="loop">
		   										<img src="https://i.imgur.com/rhSk7m7.png" width = 18 title="source: imgur.com" />
											</c:forEach>
											<c:forEach begin="1" end="${5 - profs.getValoracionMedia()}" varStatus="loop">
		   										<img src="https://i.imgur.com/hYfF8io.png" width = 18 title="source: imgur.com" />
											</c:forEach>
										</h4>
									</c:when>
									<c:when test="${profs.getValoracionMedia() == 0}">
										Sin valoración
									</c:when>
									</c:choose>
									<br/>
									<c:if test="${usR.getDTYPE()=='cliente'}">
									<form action="<c:url value="/votarProfesional.htm" />">
									<p></p>
									<input type="hidden"
										id="profesionalId" name="profesionalId" value="${profs.id}">
									</form>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

  	
  
  
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  
</body>

</html>