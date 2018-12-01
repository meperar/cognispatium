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
<link rel="stylesheet" href="misSolicitud.css">
</head>
<body>
	<jsp:include page="barrasuperior.jsp" flush="true" />
	
	<br>
	<div style="text-align: center;">
		<a><b style="font-size: 20px"> MIS SOLICITUDES</b></a>
		<hr style="border-top: 2px solid #000000;">
	</div>
	<br>
	<br>
	<br>
	<div class="row">
		<div class="col-md-12">
			<div class="form-group"></div>
			<table class="table">
				<thead>
					<tr>
						<th>Titulo</th>
						<th>Descripcion</th>
						<th>Estado</th>
						<th>Accion</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${model.solicitudes}" var="solicitud">
						
							<tr>
							<form action="#" method="post" class="text-left">
								<td style="max-width: 500px; word-wrap: break-word;"><input
									type="hidden" id="solicitudId" name="solicitudId"
									value="${solicitud.id}"> ${solicitud.titulo}</td>
								<td style="max-width: 500px; word-wrap: break-word;">${solicitud.descripcion}</td>
								<td>${solicitud.estado}</td>
								<td><button class="bg-primary" name="info">
										<img src="https://image.flaticon.com/icons/svg/1/1176.svg"
											alt="Information button free icon"
											title="Ver datos de mi solicitud" height="20" width="20">
									</button>
									<button class="bg-primary" name="borrar">
									<img src="https://image.flaticon.com/icons/svg/121/121116.svg"
										alt="Eliminar solicitud" title="Eliminar solicitud"
										height="20" width="20">
								</button>
								<c:if test="${solicitud.getEstado() == 'adjudicada' || solicitud.getEstado() == 'aceptado_profesional' || solicitud.getEstado() == 'aceptado_cliente'|| solicitud.getEstado() == 'resuelta'}">
								<button class="bg-primary" name="valorarProfesional">
								<img src="https://image.flaticon.com/icons/svg/1087/1087916.svg" alt="Valorar profesional" title="Valorar profesional"
										height="20" width="20">
								</button>
								</c:if>
								<c:if test="${solicitud.getEstado() == 'adjudicada' || solicitud.getEstado() == 'aceptado_profesional'}">
								<button class="bg-primary" name="resolverSolicitud">
								<img src="https://image.flaticon.com/icons/svg/64/64886.svg" alt="resolver solicitud" title="resolver solicitud"
										height="20" width="20">
								</button>
								</c:if>
								</td>
									
							</tr>
						</form>
					</c:forEach>
				</tbody>
			</table>
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
</body>
</html>