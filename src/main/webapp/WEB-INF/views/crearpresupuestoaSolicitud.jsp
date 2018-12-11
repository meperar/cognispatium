<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="https://static.pingendo.com/bootstrap/bootstrap-4.1.3.css">
</head>

<body>
<jsp:include page="barrasuperior.jsp" flush="true" />
  <div class="py-3">
    <div class="container">
      <div class="row">
        <div class="col-md-8 p-4" style="">
          <h1>Crear presupuesto</h1>
          <p>En esta pagina podra ofrecer un presupuesto a la solicitud del cliente elegida.</p>
        </div>
      </div>
      <div class="row">
        <div class="col-md-5 p-4">
          <h3>Datos solicitud</h3>
          <p>${model.solicitud.clienteOrigen.getNombre()} ${model.solicitud.clienteOrigen.getApellidos()}</p>
          <p class="lead mt-3"> <b>Pais</b></p>
          <p> ${model.solicitud.clienteOrigen.getPais()} </p>
          <p class="lead mt-3"> <b>Provincia</b></p>
          <p> ${model.solicitud.clienteOrigen.getProvincia()} </p>
          <p class="lead mt-3"> <b>Ciudad</b></p>
          <p> ${model.solicitud.clienteOrigen.getCiudad()} </p>
          <p class="lead mt-3"> <b>Titulo</b></p>
          <p> ${model.solicitud.titulo} </p>
          <p class="lead mt-3"> <b>Descripcion</b></p>
          <p>  ${model.solicitud.descripcion}</p>
          <p class="lead mt-3"> <b>Servicio</b></p>
          <p>  ${model.solicitud.servicioOrigen.nombre}</p>
        </div>
        <div class="col-md-7 p-4">
          <h3 class="mb-3">Presupuesto</h3>
          <form action="<c:url value="/crearpresupuestoaSolicitud.htm" />" method="post" >
          <input type="hidden" id="solicitudId" name="solicitudId" value="${model.solicitud.id}">
            <div class="form-row">
              <div class="form-group col-md-12"> <input type="number" class="form-control" id="form36" placeholder="Precio total estimado (euros)" required="required" name="precio"> </div>
            </div>
            <div class="form-group"> <textarea maxlength="1000" class="form-control" id="form43" rows="3" placeholder="Descripción del presupuesto"  required="required" name="descripcion"></textarea> </div> <button type="submit" class="btn btn-primary">Send</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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