<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
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
<div class="py-5">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
								<th>Titulo</th>
								<th>Descripcion</th>
								<th>Cliente/Profesional</th>
								<th>Fecha</th>
								<th>Accion</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mensajes.mensajes}" var="mensaje">
								<form action="#" method="post" class="text-left">
								<tr>
									<td><input type="hidden" id="mensajeId"
										name="mensajeId" value="${mensaje.id}">
										${mensaje.asunto}</td>
									<td>${mensaje.descripcion}</td>
									<td>${mensaje.cliente.nombre}
										${mensaje.profesional.apellidos}</td>
									<td>${mensaje.fecha}</td>	
									<td><button class="bg-primary">
											<i class="fas fa-plus-square"></i>
										</button></td>
								</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>