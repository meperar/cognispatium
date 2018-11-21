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
	<style type="text/css">
	td.a1{font-weight: bold;}
	</style>
</head>
<body>
	<jsp:include page="barrasuperior.jsp" flush="true" />
	<nav class="navbar navbar-light"></nav>
	<nav class="navbar navbar-dark bg-dark">
		<div class="container d-flex justify-content-center">
			<a class="navbar-brand" href="#"> <b> Correo Interno:
					${mensajeTipo}</b>
			</a>
		</div>
	</nav>
	<div class="row">
		<div class="col-md-2">
			<ul class="list-group">
				<li
					class=" border-0 list-group-item d-flex justify-content-between align-items-center">
					<a href="getTodos.htm"">Todos <i
						class="fa fa-list fa-lg text-dark"></i></a>
				</li>
				<li
					class=" border-0 list-group-item d-flex justify-content-between align-items-center">
					<a href="getNoLeidos.htm">No leidos <i><img
							src="https://image.flaticon.com/icons/svg/25/25236.svg"
							width="20" height="20"></i></a>
				</li>
				<li
					class=" border-0 list-group-item d-flex justify-content-between align-items-center">
					<a href="getLeidos.htm">Leidos <i><img
							src="https://image.flaticon.com/icons/svg/66/66159.svg"
							width="20" height="20"></i></a>
				</li>
				<li
					class=" border-0 list-group-item d-flex justify-content-between align-items-center">
					<a href="getEliminados.htm">Eliminado <i><img
							src="https://image.flaticon.com/icons/svg/95/95327.svg"
							width="20" height="20"></i></a>
				</li>
			</ul>
		</div>
		<div class="col-md-10">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<table class="table" id="myTableId">
							<thead>
								<tr>
									<th>Titulo</th>
									<th>Descripcion</th>
									<th>Cliente/Profesional</th>
									<th>Fecha</th>
									<c:if test="${mensajeTipo != 'Eliminados'}">
										<th>Accion</th>
									</c:if>
								</tr>
							</thead>
							<tbody id="table-ajax">
								<c:forEach items="${mensajes.mensajes}" var="mensaje">
									<form action="<c:url value="/bandejamensajes.htm" />"
										method="post" class="text-left">
										<c:choose>
											<c:when test="${mensaje.estado.ordinal() == 1}">
												<tr>
													<td class="a1" style="max-width: 500px; word-wrap: break-word;"><input
														type="hidden" id="mensajeId" name="mensajeId"
														value="${mensaje.id}"><B> ${mensaje.asunto}</B></td>
													<td  class="a1" style="max-width: 500px; word-wrap: break-word;"><B>${mensaje.descripcion}</B></td>
													<td class="a1"><B>${mensaje.usuarioOrigen.nombre}
														${mensaje.usuarioOrigen.apellidos}</B></td>
													<td class="a1"><B>${mensaje.fecha}</B></td>
													<c:if test="${mensajeTipo != 'Eliminados'}">
														<td><button name="action" value="Ver"
																class="bg-primary">
																<i class="far fa-comments"></i>
															</button></td>
													</c:if>
													<c:if test="${mensajeTipo != 'Eliminados'}">
														<td><button name="action" value="Eliminar"
																class="bg-primary">
																<i class="fas fa-trash"></i>
															</button></td>
													</c:if>
												</tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td style="max-width: 500px; word-wrap: break-word;"><input
														type="hidden" id="mensajeId" name="mensajeId"
														value="${mensaje.id}">${mensaje.asunto}</td>
													<td style="max-width: 500px; word-wrap: break-word;">${mensaje.descripcion}</td>
													<td>${mensaje.usuarioOrigen.nombre}
														${mensaje.usuarioOrigen.apellidos}</td>
													<td>${mensaje.fecha}</td>
													<c:if test="${mensajeTipo != 'Eliminados'}">
														<td><button name="action" value="Ver"
																class="bg-primary">
																<i class="far fa-comments"></i>
															</button></td>
													</c:if>
													<c:if test="${mensajeTipo != 'Eliminados'}">
														<td><button name="action" value="Eliminar"
																class="bg-primary">
																<i class="fas fa-trash"></i>
															</button></td>
													</c:if>
												</tr>
											</c:otherwise>
										</c:choose>
									</form>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>