<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
</head>
<body>
	<h1>
		<fmt:message key="heading" />
	</h1>
	<p>
		<fmt:message key="greeting" />
		<c:out value="${model.now}" />
	</p>
	<h3>Usuarios</h3>
	<c:forEach items="${model.professionals}" var="prof">
		<c:out value="${prof.nombre}" />
		<i> <c:out value="${prof.apellidos}" /></i>
		<i> <c:out value="${prof.email}" /></i>
		<br>
		<br>
	</c:forEach>
	<br>
</body>
</html>