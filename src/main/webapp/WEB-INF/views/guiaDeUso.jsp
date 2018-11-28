<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<jsp:include page="barrasuperior.jsp" flush="true" />
<h3>Esta página web está pensada para buscar profesionales en distitas areas.
Puedes regisrarte como profesional o como cliente.
</h3>
<br/>
<h4>
	<p>El cliente puede:</p>
	<p> - Listar profesinales por area</p>
	<p> - Listar las consultas por area</p>
	<p> - Pulicar consulatas urgentes que podrán ser respondidas por profesionales</p>
	<p> - Pedir presupuestos que serán atendidos por los profesionales</p>
	<p> - Mostrar las solicitudes que has creado</p>
	<p> - Mostrar el historial de las solicitudes. Esto son las solicitudes que han sido resueltas por el profesional</p>
	<p> - Gestionar la información del prefil</p>
	<p> - Hacer log out de la aplicación</p>
</h4>
<br/>
<h4>
	<p>El profesional puede:</p>
	<p> - Listar profesinales por area</p>
	<p> - Listar las consultas por area</p>
	<p> - Listar las solicitudes de persupuestos creadas por los clientes.
	En este listado solo aparecen las solicitudes de las areas en la que está registrado el profesional</p>
	<p> - Mostrar las consultas creadas por los clientes</p>
	<p> - Mostrar los presupuesto creados por los clientes</p>
	<p> - Mostrar el historial de los presupuesto terminados.
	Para que esto suceda el profesional y el cliente deben haber marcado el presupuesto como resuelto.</p>
	<p> - Mostrar los mensajes recibidosl</p>
	<p> - Gestionar la información del prefil del profesional</p>
	<p> - Hacer log out de la aplicación</p>
</h4>
</body>
</html>