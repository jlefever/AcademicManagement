<%@ page language="java" contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Data View</title>
</head>
<body>
	<table>
	<th>ID</th>
	<th>Year</th>
	<c:forEach items="${years}" var="current">
		<tr>
			<td><c:out value="${current.id}" /></td>
			<td><c:out value="${current.year}" /></td>
		</tr>
	</c:forEach>
	</table>
	<form action="${pageContext.servletContext.contextPath}/DataView" method="post">
		<p>
     	<label class="AddYear">Add Year:</label>
     	<input type="text" name="year" size="12" value="${year}">
	</form>
	<c:if test="${! empty errorMessage}">
		<div class="error">${errorMessage}</div>
	</c:if>	
</body>
</html>