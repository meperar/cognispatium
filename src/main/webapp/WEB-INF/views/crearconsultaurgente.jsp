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
	type="text/css" integrity="">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css"
	integrity="">
</head>
<body>
	<jsp:include page="barrasuperior.jsp" flush="true" />
	<div class="py-5 text-center" style="">
		<div class="container">
			<div class="row" style="">
				<div class="mx-auto col-lg-6 col-10">
					<h1>Crear una consulta urgente</h1>
					<p class="mb-3">En esta pantalla podra introducir los datos para crear su consulta urgente.</p>
					<form action="<c:url value="/pagoTarjeta.htm" />" method="GET"
						class="text-left">
						<div class="form-group">
							<label for="form16">Servicio</label> <br> <select
								name='servicio' required="required">
								<option disabled selected >--</option>
								<c:forEach items="${servicios.ambitos}" var="ambito">
									<optgroup label="${ambito}">
										<c:forEach items="${servicios.serviciosxambitos.get(ambito)}"
											var="servicio">
											<option value="${servicio.id}" label="${servicio.nombre}"></option>
										</c:forEach>
									</optgroup>
								</c:forEach>
							</select>
						</div>
						
						 <div class="form-group" >
						    <label for="form16">Tiempo maximo de espera (HH:MM)</label> <br>             				
                  				<select name="tiempoEspera"  required ="required">
                  					<option disabled selected>--</option>
                    				<option value="01:00">01:00</option>
                    				<option value="01:30">01:30</option>      
                    				<option value="02:00">02:00</option>
                    				<option value="02:30">02:30</option>
                    				<option value="03:00">03:00</option>                   
                  				</select>
              			 </div>
              			 
						<div class="form-group">
							<label for="form16">Resumen</label> <input type="text"
								name="titulo" class="form-control" id="form16"
								placeholder="Resumen de la consulta" required maxlength="80" />
						</div>
						<div class="form-group">
							<label for="form18">Descripcion</label>
							<textarea class="form-control" id="form18" name="descripcion"
								style="" placeholder="Introduzca una descripcion de la consulta"
								required="required" maxlength="500"></textarea>
						</div>
						<div class="form-group">
							<label for="form18">El precio a pagar por la consulta es de 2.00 EUROS. </label>
						</div>
						
						<button type="submit" class="btn btn-primary"> Crear consulta<br>
						</button>
					</form>
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

	<div class="py-3">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center">
					<p>
						<img src="https://i.imgur.com/xmZULKf.png" width=200
							title="source: imgur.com" />
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