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
<link rel="stylesheet" href="diferentesPresupuestos.css">
</head>
<body>
<jsp:include page="barrasuperior.jsp" flush="true" />
	<nav class="navbar navbar-light border-dark">
		<div class="container d-flex justify-content-center">
			<a class="navbar-brand text-primary" href="#"> <i
				class="fa d-inline fa-lg fa-circle-o text-dark"></i> <b
				class="text-dark"> PRESUPUESTOS DE SOLICITUD</b>
			</a>
		</div>
	</nav>
		<nav class="navbar navbar-dark bg-dark border-light">
			<div class="container"></div>
		</nav>
		<div class="form-group"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-3" style="">
					<div class="form-group">
						<label>TITULO:</label>
					</div>
				</div>
				<div class="col-md-6" style="">
					<div class="form-group">
						<label contenteditable="true">${model.solicitud.titulo}</label>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-3" style="">
					<div class="form-group">
						<label>DESCRIPCION :</label>
					</div>
				</div>
				<div class="col-md-6" style="">
					<div class="form-group">
						<label contenteditable="true">${model.solicitud.descripcion}</label>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group"></div>
				</div>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>Presupuestos</th>
					<th>Pais</th>
					<th>Provincia</th>
					<th>Ciudad</th>
					<th>Accion</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${model.presupuestos}" var="presupuesto">
					<form action="<c:url value="/validarPresupuesto.htm" />" method="post" >
						<tr>
							<td style="max-width: 500px; word-wrap: break-word;"><input
								type="hidden" id="presupuestoId" name="presupuestoId"
								value="${presupuesto.id}"> ${presupuesto.profesionalOrigen.nombre} ${presupuesto.profesionalOrigen.apellidos}</td>
							<td>${presupuesto.profesionalOrigen.getPais()}</td> 
							<td>${presupuesto.profesionalOrigen.getProvincia()}</td> 
							<td>${presupuesto.profesionalOrigen.getCiudad()}</td> 	
							<td><button class="bg-primary">
									<img src="https://image.flaticon.com/icons/svg/159/159604.svg"
										width="20" height="20" alt="Eye free icon"
										title="Eye free icon">
								</button></td>
						</tr>
					</form>
				</c:forEach>
			</tbody>
		</table>
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