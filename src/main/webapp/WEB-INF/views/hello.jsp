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
	<h3>Products</h3>
	<c:forEach items="${model.professionals}" var="prof">
		<c:out value="${prof.name}" />
		<i> <c:out value="${prof.surname}" /></i>
		<i> <c:out value="${prof.benefits}" /></i>
		<br>
		<br>
	</c:forEach>
	<br>
	<a href="<c:url value="viewincreasebenefits.htm"/>">Increase Prices</a>
	<br>
</body>
</html>