<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h1>
		<fmt:message key="increasebenefits.heading" />
	</h1>
	<form:form method="post" modelAttribute="IncreaseBenefits">
		<table>
			<tr>
				<td align="right" width="20%">Increase (%):</td>
				<td width="20%"><form:input path="benefits" /></td>
				<td width="60%"><form:errors path="benefits" cssClass="error" />
				</td>
			</tr>
		</table>
		<br>
		<input type="submit" value="Execute">
	</form:form>
	<a href="<c:url value="hello.htm"/>">Home</a>
</body>
</html>