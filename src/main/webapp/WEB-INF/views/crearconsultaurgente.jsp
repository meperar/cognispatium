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
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
</head>

<body>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<div class="container">
			<button class="navbar-toggler navbar-toggler-right border-0"
				type="button" data-toggle="collapse" data-target="#navbar13">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbar13">
				<a class="navbar-brand d-none d-md-block" href="#"> <i
					class="fa d-inline fa-lg fa-stop-circle-o"></i> <b>
						CogniSpatium</b>
				</a>
				<ul class="navbar-nav mx-auto">
					<li class="nav-item"><a class="nav-link" href="#">Features</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Pricing</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fa fa-twitter fa-fw"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fa fa-facebook fa-fw"></i>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> <i
							class="fa fa-slack fa-fw"></i>
					</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="py-5 text-center" style="">
		<div class="container">
			<div class="row" style="">
				<div class="mx-auto col-lg-6 col-10">
					<h1>Crear una consulta urgente</h1>
					<p class="mb-3">En esta pantalla podrá introducir los datos
						para crear su consulta urgente.</p>
					<form class="text-left">
						<div class="form-group">
							<label for="form16">Servicio</label> <select name="programa">
								<option value="Windows" selected="selected">Windows</option>
								<option value="Machintosh">Mac</option>
								<option value="Linux">Linux</option>
							</select>
						</div>
						<div class="form-group">
							<label for="form16">Tiempo máximo de espera</label> <input
								type="time" class="form-control" id="form16"
								placeholder="Tiempo de espera">
						</div>
						<div class="form-group">
							<label for="form16">Resumen</label> <input type="text"
								class="form-control" id="form16"
								placeholder="Resumen de la consulta">
						</div>
						<div class="form-group">
							<label for="form18">Descripción</label> <input type="text"
								class="form-control" id="form18" style=""
								placeholder="Introduzca una descripcion de la consulta">
						</div>
					</form>
					<div class="row">
						<div class="col-md-6">
							<a href="<c:url value="/hello.htm"/>">Respoder
								a consulta urgente</a>
						</div>
						<div class="col-md-6">
							<button type="submit" class="btn btn-primary">
								Crear consulta<br>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="py-3">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<i class="d-block fa fa-stop-circle mb-3 text-muted fa-3x"></i>
					<p>
						<a href="https://goo.gl/maps/AUq7b9W7yYJ2" target="_blank">
							Fake street, 100 <br>NYC - 20179, USA
						</a>
					</p>
					<p>
						<a href="tel:+246 - 542 550 5462">+1 - 256 845 87 86</a>
					</p>
					<p class="mb-0">
						<a href="mailto:info@pingendo.com">info@pingendo.com</a>
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
					<p class="mb-0">© 2014-2018 Pingendo. All rights reserved</p>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>