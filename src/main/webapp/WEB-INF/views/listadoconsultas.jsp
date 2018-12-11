<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
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
	<br>
	<div style="text-align: center;">
		<a><b style="font-size: 20px"> CONSULTAS </b></a>
		<hr style="border-top: 2px solid #000000;">
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<form action="<c:url value="/listadoconsultas.htm" />" method="GET">
					<label for="form16">Servicio</label> <br> <select
						name='servicio'>
						<c:set var="servId" value="${model.serviciId}" />
						<c:if test="${servId == null}">
							<option disabled selected >-- Seleccione un
								servicio --</option>
						</c:if>
						<c:forEach items="${model.servicios}" var="servicio">
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
					</select> <input class="btn btn-primary" type="submit" name="action"
						value="Filtrar" />
				</form>
			</div>
			<br>
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
								<th>Titulo</th>
								<th>Descripcion</th>
								<th>Cliente</th>
								<th>Estado</th>
								<th>Accion</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${model.consultas}" var="consulta">
								<form action="<c:url value="/responderconsultaurgente.htm" />"
									method="get" class="text-left">
									<tr>
										<td style="max-width: 500px;word-wrap:break-word;"><input type="hidden"
											id="consultaId" name="consultaId" value="${consulta.id}">
											<p>${consulta.titulo}</p></td>
										<td style="max-width: 500px;word-wrap:break-word;"><p>${consulta.descripcion}</p></td>
										<td>${consulta.clienteOrigen.nombre}
											${consulta.clienteOrigen.apellidos}</td>
											<td>${consulta.estado}</td>
										<td><button class="bg-primary">
												<i class="fas fa-pen-alt"></i>
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
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<div class="py-3">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<p>
						<img src="https://i.imgur.com/xmZULKf.png" width="200"
							title="source: imgur.com">
					</p>
					<p>
						<a href="https://goo.gl/maps/Bu1VZj2Eew42" target="_blank">
							UPV, ETSINF <br>VLC, Spain
						</a>
					</p>
					<p class="mb-0">
						<a href="mailto:info@cognispatium.com">info@cognispatium.com</a>
					</p>
				</div>
			</div>
			<div class="row">
				<div
					class="col-md-12 d-flex align-items-center justify-content-center my-3">
					<a href="#"> <i
						class="d-block fa fa-facebook-official text-muted fa-lg mr-2"></i>
					</a> <a href="#"> <i
						class="d-block fa fa-instagram text-muted fa-lg mx-2"></i>
					</a> <a href="#"> <i
						class="d-block fa fa-google-plus-official text-muted fa-lg mx-2"></i>
					</a> <a href="#"> <i
						class="d-block fa fa-pinterest-p text-muted fa-lg mx-2"></i>
					</a> <a href="#"> <i
						class="d-block fa fa-reddit text-muted fa-lg mx-2"></i>
					</a> <a href="#"> <i
						class="d-block fa fa-twitter text-muted fa-lg ml-2"></i>
					</a>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">
					<p class="mb-0">© 2018 CogniSpatium. All rights reserved</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>