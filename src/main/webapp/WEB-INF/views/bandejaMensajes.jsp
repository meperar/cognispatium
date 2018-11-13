
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
			<div class="row">
				<div class="col-md-12">
					<table class="table">
						<thead>
							<tr>
								<th>Titulo</th>
								<th>Descripcion</th>
								<th>Cliente/Profesional</th>
								<th>Fecha</th>
								<th>Accion</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mensajes.mensajes}" var="mensaje">
								<form action="#" method="post" class="text-left">
									<tr>
										<td style="max-width: 500px;word-wrap:break-word;"><input type="hidden" id="mensajeId" name="mensajeId"
											value="${mensaje.id}"> ${mensaje.asunto}</td>
										<td style="max-width: 500px;word-wrap:break-word;">${mensaje.descripcion}</td>
										<td>${mensaje.cliente.nombre}
											${mensaje.profesional.apellidos}</td>
										<td>${mensaje.fecha}</td>
										<td><button class="bg-primary">
												<i class="far fa-comments"></i>
											</button></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<script type="text/javascript">
function searchAjax() {
	$.ajax({
	    dataType : "json",
	    url : 'bandejamensajes.htm',
	    headers : {
	        'Accept' : 'application/json',
	        'Content-Type' : 'application/json'
	    },
	    type: 'POST',
	    data:$('#mensajes').serialize(),
	    success : function(responce) {   

	        /* what  i have to put here to updte my table <table id="table_grid"> */
	        $.each( responce,function(key, card) {
	            var htmlrow ="<tr><td>" + card.name + "</td></tr>";         
	            $('#table-ajax').append(htmlrow);
	        }

	    },      
	    error : function(){
	        alert("error");
	    }
	});
</script>
<head>
  <link rel="icon" href="https://i.imgur.com/CjvIMZT.png">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="theme.css" type="text/css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
</head>
<body >
<jsp:include page="barrasuperior.jsp" flush="true" />
<nav class="navbar navbar-light">
  </nav>
  <nav class="navbar navbar-dark bg-dark">
    <div class="container d-flex justify-content-center"> <a class="navbar-brand" href="#">
        <b> Correo Interno</b>
      </a> </div>
  </nav>
  <div class="row">
    <div class="col-md-2">
      <ul class="list-group">
      <button></button>
        <li class=" border-0 list-group-item d-flex justify-content-between align-items-center"><a href="#" onclick="searchAjax()">Todos <i class="fa fa-list fa-lg text-dark"></i></a></li>
        <li class=" border-0 list-group-item d-flex justify-content-between align-items-center" onclick="searchAjax()">No leidos<br><img src="https://image.flaticon.com/icons/svg/25/25236.svg" width="25" height="25"></li>
        <li class=" border-0 list-group-item d-flex justify-content-between align-items-center" contenteditable="true" onclick="searchAjax()">Leidos<br><img src="https://image.flaticon.com/icons/svg/66/66159.svg" width="25" height="25"></li>
        <li class=" border-0 list-group-item d-flex justify-content-between align-items-center" onclick="searchAjax()">Eliminados<br><img src="https://image.flaticon.com/icons/svg/95/95327.svg" width="25" height="25"></li>
      </ul>
    </div>
    <div class="col-md-10">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <c:forEach items="${mensajes.mensajes}" var="mensaje">
              <form action="#" method="post" class="text-left"></form>
            </c:forEach>
            <table class="table">
              <thead>
                <tr>
                  <th>Titulo</th>
                  <th>Descripcion</th>
                  <th>Cliente/Profesional</th>
                  <th>Fecha</th>
                  <th>Accion</th>
                </tr>
              </thead>
              <tbody id="table-ajax">
                <tr>
                  <td style="max-width: 500px;word-wrap:break-word;"><input type="hidden" id="mensajeId" name="mensajeId" value="${mensaje.id}"> ${mensaje.asunto}</td>
                  <td style="max-width: 500px;word-wrap:break-word;">${mensaje.descripcion}</td>
                  <td>${mensaje.cliente.nombre} ${mensaje.profesional.apellidos}</td>
                  <td>${mensaje.fecha}</td>
                  <td><button class="bg-primary">
                      <i class="far fa-comments"></i>
                    </button></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

>>>>>>> Stashed changes
</html>