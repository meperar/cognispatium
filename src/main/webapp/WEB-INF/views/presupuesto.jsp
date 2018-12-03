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
<link rel="stylesheet" href="theme.css">
</head>

<body>
	<jsp:include page="barrasuperior.jsp" flush="true" />
	<nav class="navbar navbar-light border-dark">
		<div class="container d-flex justify-content-center">
			<a class="navbar-brand text-primary" href="#"> <i
				class="fa d-inline fa-lg fa-circle-o text-dark"></i> <b
				class="text-dark"> PRESUPUESTO</b>
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
					<label>DESCRIPCION:</label>
				</div>
			</div>
			<div class="col-md-6" style="">
				<div class="form-group">
					<label contenteditable="true">${model.presupuesto.descripcion}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="">
				<div class="form-group">
					<label>PROFESIONAL:</label>
				</div>
			</div>
			<div class="col-md-6" style="">
				<div class="form-group">
					<label contenteditable="true">${model.presupuesto.profesionalOrigen.nombre} ${model.presupuesto.profesionalOrigen.apellidos}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="">
				<div class="form-group">
					<label>PAIS:</label>
				</div>
			</div>
			<div class="col-md-6" style="">
				<div class="form-group">
					<label contenteditable="true">${model.presupuesto.profesionalOrigen.getPais()}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="">
				<div class="form-group">
					<label>PROVINCIA:</label>
				</div>
			</div>
			<div class="col-md-6" style="">
				<div class="form-group">
					<label contenteditable="true">${model.presupuesto.profesionalOrigen.getProvincia()}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="">
				<div class="form-group">
					<label>CIUDAD:</label>
				</div>
			</div>
			<div class="col-md-6" style="">
				<div class="form-group">
					<label contenteditable="true">${model.presupuesto.profesionalOrigen.getCiudad()}</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3" style="">
				<div class="form-group">
					<label>PRECIO:</label>
				</div>
			</div>
			<div class="col-md-6" style="">
				<div class="form-group">
					<label contenteditable="true">${model.presupuesto.precio}</label>
				</div>
			</div>
		</div>
	</div>
	<div class="py-5">
		<div class="container">
			<div class="row">
				<form action="<c:url value="/pagoTarjetaSolicitud.htm" />"
					method="get">
					<div class="col-md-12 text-center">
						<input type="hidden" id="presupuestoId" name="presupuestoId"
							value="${model.presupuesto.id}">
						<button class="btn btn-primary text-center w-25">Pagar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="form-group"></div>
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
				<p class="text-dark">
					<a href="https://goo.gl/maps/Bu1VZj2Eew42" target="_blank">
						UPV, ETSINF <br>VLC, Spain
					</a>
				</p>
				<p class="mb-0 text-dark">
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