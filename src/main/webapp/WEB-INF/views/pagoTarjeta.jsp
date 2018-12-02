<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet"
	href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">


<script>
	$(document).ready(function() {
		$('input:checkbox').click(function() {
			$('input:checkbox').not(this).prop('checked', false);
			//desactiva el boton si no hay ningun checkbox activado.
			$('#btnconfirm').prop("disabled", !this.checked);
			//
			myFunction();
		});
	});
</script>

</head>
<jsp:include page="barrasuperior.jsp" flush="true" />
<body class="border border-dark">
	<nav
		class="navbar text-uppercase border border-dark shadow navbar-light">
		<div class="container">
			<a class="navbar-brand text-primary" href="#"> <i
				class="fa d-inline fa-circle fa-lg"></i> <b>Pagos</b>
			</a>
		</div>
	</nav>
	<form action="#" method="post">
		<div class="py-0">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="row">
							<div class="col-md-6" style="">
								<c:forEach items="${model.tarjetas}" var="tarjeta">
									
										<div class="row">
											<div
												class="col-md-9 col-lg-3 m-0 d-inline-flex align-items-end flex-row-reverse justify-content-start text-right"
												style="">
												<input class="form-control w-25" type="checkbox"
													placeholder="Type here" name="cb-tarjeta"
													value="${tarjeta.id}">
												
											</div>
											<div class="col-md-9 px-1" style="">
												<div class="row"><label class="text-left" style=""> Tarjeta:	***********${tarjeta.numero.toString().substring(12, 15)}</label>
												</div> <div class="row"><label class="text-left" style=""> Titular:		${tarjeta.titular}</label></div>
											</div>
											
										</div>
																		
								<br>	
								</c:forEach>
								
								<div class="row">
									<div
										class="col-md-9 col-lg-3 m-0 d-inline-flex align-items-end flex-row-reverse justify-content-start text-right"
										style="">
										<input class="form-control w-25" type="checkbox" id="myCheck">
									</div>
									<div class="col-md-9 px-1" style="">
										<label class="text-left" style=""> Nueva Tarjeta</label>
									</div>
								</div>
							</div>
							<script>
							function myFunction() {
								var checkBox = document.getElementById("myCheck");
								var header = document.getElementById("header");
								if (checkBox.checked == false) {
									header.style.display = "none";
									document.getElementById("titular").required = false;
									document.getElementById("numTarjeta").required = false;
									document.getElementById("cvv").required = false;
									document.getElementById("mes").required = false;
									document.getElementById("anyo").required = false;
								} else {
									header.style.display = "block";
									document.getElementById("titular").required = true;
									document.getElementById("numTarjeta").required = true;
									document.getElementById("cvv").required = true;
									document.getElementById("mes").required = true;
									document.getElementById("anyo").required = true;
								}
							}
							</script>
							<div class="col-md-6" style="display: none" id="header">
								<div class="col-md-12 p-5" style="">
									<h3 class="mb-3">Nueva Tarjeta</h3>
									<div class="form-group">
										<label>Titular :&nbsp;</label> <input name="titular"
											id="titular" type="text" class="form-control"
											placeholder="Introduza el titular" required>
									</div>
									<div class="form-group">
										<label>Numero de Tarjeta :&nbsp;</label> <input type="text"
											name="numTarjeta" id="numTarjeta" pattern="[0-9]{16,}"
											required title="Introduzca 16 digitos" maxlength="16"
											class="form-control"
											placeholder="Introduza el numero de la tarjeta">
									</div>
									<div class="form-group">
										<label>CVV (?) :</label> <input name="cvv" id="cvv"
											type="text" pattern="[0-9]{3,}" required
											title="Introduzca 3 digitos" maxlength="3"
											class="form-control"
											placeholder="Introduzca el código de seguridad">
									</div>
									<div class="form-group">
										<label>Fecha de caducidad :</label>
									</div>
									<div class="row">
										<div class="col-md-2 text-right" style="">
											<label>Mes :</label>
										</div>
										<div class="col-md-4" style="">
											<div class="btn-group">
												<select name="mes" required>
													<option disabled="" selected="" value="--">--</option>
													<option value="01">Enero</option>
													<option value="02">Febrero</option>
													<option value="03">Marzo</option>
													<option value="04">Abril</option>
													<option value="05">Mayo</option>
													<option value="06">Junio</option>
													<option value="07">Julio</option>
													<option value="08">Agosto</option>
													<option value="09">Septiembre</option>
													<option value="10">Octubre</option>
													<option value="11">Noviembre</option>
													<option value="12">Diciembre</option>
												</select>
											</div>
										</div>
										<div class="col-md-2 text-right" style="">
											<label>Año :</label>
										</div>
										<div class="col-md-3" style="">
											<div class="btn-group">
												<select name="anyo" id="anyo" required>
													<option disabled="" selected="" value="--">--</option>
													<option value="2019">2019</option>
													<option value="2018">2020</option>
													<option value="2019">2021</option>
													<option value="2018">2022</option>
													<option value="2019">2023</option>
													<option value="2018">2024</option>
													<option value="2019">2025</option>
													<option value="2018">2026</option>
													<option value="2019">2027</option>
													<option value="2018">2028</option>
													<option value="2019">2029</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="text-center py-0">
			<div class="container">
				<div class="row">
					<div class="col-md-7 mx-auto">
						<div class="row">
							<div class="col-md-6">
								<button type="reset"
									class="btn mt-4 btn-block btn-outline-dark p-2">
									<b>CANCELAR</b>
								</button>
							</div>
							<div class="col-md-6">
								<button type="submit" id="btnconfirm" disabled
									class="btn mt-4 btn-block btn-outline-dark p-2">
									<b>CONFIRMAR PAGO</b>
								</button>
							</div>
						</div>
						<h3 class="m-2">You are in good company</h3>
						<div class="row text-muted">
							<div class="col-md-6 col-4 p-2 mb-2"
								style="transition: all 0.25s;">
								<i class="d-block fa fa-cc-visa fa-3x"></i>
							</div>
							<div class="col-md-6 col-4 p-2 mb-2"
								style="transition: all 0.25s;">
								<i class="d-block fa fa-paypal fa-3x"></i>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
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
					<p class="mb-0">Â© 2018 CogniSpatium. All rights reserved</p>
				</div>
			</div>
		</div>
	</div>


</body>

</html>