<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="factura.css">
</head>
<jsp:include page="barrasuperior.jsp" flush="true" />
<body >

<div class="row">
	<form action="<c:url value="/factura.htm" />"
					method="POST">
        <div class="col-md-5 p-4">
          <h3>Datos Factura</h3>

          <p class="lead mt-3"> <b>Cliente:</b></p>
          <p>${model.pago.clienteOrigen.getNombre()} ${model.pago.clienteOrigen.getApellidos()}</p>
          
          <p class="lead mt-3"> <b>Importe:</b></p>
          <p> ${model.pago.getPrecio()} </p>
          
          <p class="lead mt-3"> <b>Servicio:</b></p>         
          <p> </p>
          
          <p class="lead mt-3"> <b>Descripción:</b></p>
          <p>  ${model.pago.getDescripcion()}</p>
        </div>
        
       </form>
       <form action="<c:url value="/factura.htm" />" method="post">
       	<center>
       		<button  type="submit" class="btn btn-primary">Guardar</button>
       	</center>
       </form>
       
       
      </div>
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <pingendo onclick="window.open('https://pingendo.com/', '_blank')" style="cursor:pointer;position: fixed;bottom: 20px;right:20px;padding:4px;background-color: #00b0eb;border-radius: 8px; width:220px;display:flex;flex-direction:row;align-items:center;justify-content:center;font-size:14px;color:white">Made with Pingendo Free&nbsp;&nbsp;<img src="https://pingendo.com/site-assets/Pingendo_logo_big.png" class="d-block" alt="Pingendo logo" height="16"></pingendo>
  <div class="container">
    <div class="row">
      <div class="col-md-12 text-center">
        <p>
          <img src="https://i.imgur.com/xmZULKf.png" width="200" title="source: imgur.com">
        </p>
        <p>
          <a href="https://goo.gl/maps/Bu1VZj2Eew42" target="_blank"> UPV, ETSINF <br>VLC, Spain </a>
        </p>
        <p class="mb-0">
          <a href="mailto:info@cognispatium.com">info@cognispatium.com</a>
        </p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12 d-flex align-items-center justify-content-center my-3">
        <a href="#"> <i class="d-block fa fa-facebook-official text-muted fa-lg mr-2"></i>
        </a> <a href="#"> <i class="d-block fa fa-instagram text-muted fa-lg mx-2"></i>
        </a> <a href="#"> <i class="d-block fa fa-google-plus-official text-muted fa-lg mx-2"></i>
        </a> <a href="#"> <i class="d-block fa fa-pinterest-p text-muted fa-lg mx-2"></i>
        </a> <a href="#"> <i class="d-block fa fa-reddit text-muted fa-lg mx-2"></i>
        </a> <a href="#"> <i class="d-block fa fa-twitter text-muted fa-lg ml-2"></i>
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