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
		<a><b style="font-size: 20px"> MIS PRESUPUESTOS</b></a>
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
						<th>Solicitud</th>
						<th>Estado</th>
						<th>Cliente</th>
						<th>Pais</th>
						<th>Provincia</th>
						<th>Ciudad</th>
						<th>Fecha creación</th>
						<th>Accion</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${model.presupuestos}" var="presupuesto">
						
							<tr>
							<form action="#" method="post" class="text-left">
								<td style="max-width: 500px; word-wrap: break-word;"><input
									type="hidden" id="presupuestoId" name="presupuestoId"
									value="${presupuesto.id}"> ${presupuesto.getSolicitudOrigen().getTitulo()}</td>
								<td style="max-width: 500px; word-wrap: break-word;">${presupuesto.estado}</td>
								<td>${presupuesto.getSolicitudOrigen().clienteOrigen.getNombre()} ${presupuesto.getSolicitudOrigen().clienteOrigen.getApellidos()}</td>
								<td>${presupuesto.getSolicitudOrigen().clienteOrigen.getPais()}</td>
								<td>${presupuesto.getSolicitudOrigen().clienteOrigen.getProvincia()}</td>
								<td>${presupuesto.getSolicitudOrigen().clienteOrigen.getCiudad()}</td>
								<td>${presupuesto.getFechaCreacionFormat()}</td>
								<td><button class="bg-primary" name="info">
										<img src="https://image.flaticon.com/icons/svg/1/1176.svg"
											alt="Information button free icon"
											title="Ver datos de mi presupuesto" height="20" width="20">
									</button>
									<c:if test="${presupuesto.getEstado() == 'aceptado' || presupuesto.getEstado() == 'aceptado_cliente'}">
									<button class="bg-primary" name="resolverPresupuesto">
										<img src="https://image.flaticon.com/icons/svg/64/64886.svg" alt="resolver presupuesto" title="resolver presupuesto"
										height="20" width="20">
									</button>
									</c:if>
									</td>	
							</form>							
							</tr>						
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
				<p class="mb-0">Â© 2018 CogniSpatium. All rights reserved</p>
			</div>
		</div>
	</div>
</body>

</html>