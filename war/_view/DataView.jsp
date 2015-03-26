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
</body>
</html>