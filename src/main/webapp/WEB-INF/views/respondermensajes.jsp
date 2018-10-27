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
<div class="py-5">
		<div class="container">		
		 <div class="col-md-12">
          <h2 style="text-align: center;">Datos mensaje</h2>
        </div>
      </div>
      <div class="row">
        <div class="col-md-6 p-4">
          <p class="lead mt-3"> <b>Remitente</b></p>
          <p>${model.mensaje.profesional.getNombre()} ${model.mensaje.profesional.getApellidos()}</p>
          <p class="lead mt-3"> <b>Destinatario</b></p>
          <p>${model.mensaje.cliente.getNombre()} ${model.mensaje.cliente.getApellidos()}</p>
          <p class="lead mt-3"> <b>Titulo</b></p>
          <p> ${model.mensaje.asunto} </p>
          <p class="lead mt-3"> <b>Descripcion</b></p>
          <p> ${model.mensaje.descripcion}</p>
        </div>
        <div class="col-md-6 p-4">
          <p class="lead mt-3"> <b>Responder</b></p>
           <form action="<c:url value="/respondermensajes.htm" />" method="post" >
          <input type="hidden" id="mensajeId" name="mensajeId" value="${model.mensaje.id}">
          <div class="form-row w-100">
            <textarea class="form-control" id="form43" rows="10" placeholder="" required="required" name="descripcion"></textarea> </div>
          <button type="submit" class="btn btn-primary m-2">Send</button>
          </form>
        </div>
      </div>
    </div>
</body>
</html>