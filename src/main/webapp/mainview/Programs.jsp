<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Programs</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
</head>
<body>
	<stripes:form action="/Programs.action" focus="">
		<h1>Programs</h1>
		<%-- <stripes:submit name="view" value="View Programs" /> --%>
		<table class="gridtable">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Year</th>
				<th></th>
				<c:if test="${user.permissions == 2}">
					<th></th>
				</c:if>
			</tr>
			<c:forEach items="${actionBean.programs}" var="program">
				<tr>
					<td>${program.id}</td>
					<td>${program.name}</td>
					<td>${program.description}</td>
					<td>${program.year}</td>
					<!-- View -->
					<td><stripes:link href="/Programs.action" event="outcomes">View<stripes:param
								name="viewId" value="${program.id}" />
						</stripes:link></td>
					<!-- Delete -->
					<c:if test="${user.permissions == 2 }">
						<td><stripes:link href="/Programs.action" event="delete">Delete<stripes:param
									name="id" value="${program.id}" />
							</stripes:link></td>
					</c:if>
				</tr>
			</c:forEach>
			<!-- Add -->
			<c:if test="${user.permissions == 2 }">
				<tr>
					<td></td>
					<td><stripes:text name="name" /></td>
					<td><stripes:text name="description" /></td>
					<td><stripes:text name="year" /></td>
					<td><stripes:submit class="button" name="add" value="Add" /></td>
				</tr>
			</c:if>
		</table>
		<br />
		<!-- Edit -->
		<c:if test="${user.permissions == 2}">
			<h3>Edit Program</h3>
			<table>
				<tr>
					<td><stripes:label for="Program Id" />:</td>
					<td><stripes:text name="progId" /></td>
				</tr>
				<tr>
					<td><stripes:label for="New Name" />:</td>
					<td><stripes:text name="newname" /></td>
				</tr>
				<tr>
					<td><stripes:label for="New Description" />:</td>
					<td><stripes:text name="newdescription" /></td>
				</tr>
				<tr>
					<td><stripes:label for="New Year" />:</td>
					<td><stripes:text name="newyearid" /></td>
				</tr>
				<tr>
					<td><stripes:submit class="button" name="edit"
							value="Edit Program" /></td>
				</tr>
			</table>
		</c:if>
	</stripes:form>
</body>
</html>

