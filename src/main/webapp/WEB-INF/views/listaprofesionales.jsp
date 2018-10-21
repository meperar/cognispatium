<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
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
<jsp:include page="barrasuperior.jsp" flush="true" />
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css" style="">
    <div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<table class="listado">
						<thead>
							<tr>
								<th colspan="2"><h3>Profesionales - ${servicio.servicio.ambito} (${servicio.servicio.nombre})</h3></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${model.profesional}" var="profs">	
								<tr>
									<td style="border: 0px"> &emsp; </td>
									<td>
										<h4><img src="https://i.imgur.com/4omBc9u.png" width = 128 title="source: imgur.com" />&emsp; ${profs.nombre} ${profs.apellidos}</h4>
										<a>&nbsp;<c:forEach begin="1" end="5" varStatus="loop">
    										<img src="https://i.imgur.com/gYOuM8u.png" width = 18 title="source: imgur.com" />
										</c:forEach></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>