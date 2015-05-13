<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Measurements</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
</head>
<body>
	<stripes:form action="/Measurements.action" focus="">
		<h1>Measurements</h1>
		<table class="gridtable">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Met</th>
				<th>Indicator ID</th>
				<th></th>
				<c:if test="${user.permissions == 2}">
					<th></th>
				</c:if>
			</tr>
			<c:forEach items="${actionBean.measurements}" var="measurement">
				<tr>
					<td>${measurement.id}</td>
					<td>${measurement.name}</td>
					<td>${measurement.description}</td>
					<td>${measurement.met}</td>
					<td>${measurement.indicatorId}</td>
					<!-- View -->
					<td><stripes:link href="/Measurements.action" event="rubric">View<stripes:param
								name="viewId" value="${measurement.id}" />
						</stripes:link></td>
					<!-- Delete -->
					<c:if test="${user.permissions == 2 }">
						<td><stripes:link href="/Measurements.action" event="delete">Delete<stripes:param
									name="id" value="${measurement.id}" />
							</stripes:link></td>
					</c:if>
				</tr>
			</c:forEach>
			<!-- Add -->
			<c:if test="${user.permissions == 2 }">
				<tr>
					<td></td>
					<td><stripes:text class="add" name="name" /></td>
					<td><stripes:text class="add" name="description" /></td>
					<td></td>
					<td></td>
					<td><stripes:submit class="button" name="add" value="Add" /></td>
				</tr>
			</c:if>
		</table>
		<br />
		<!-- Edit -->
		<c:if test="${user.permissions == 2}">
			<h3>Edit Measurement</h3>
			<table>
				<tr>
					<td><stripes:label for="Measurement Id" />:</td>
					<td><stripes:text name="measurementId" /></td>
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
					<td><stripes:submit class="button" name="edit"
							value="Edit Measurement" /></td>
				</tr>
				<tr>
					<td><stripes:submit class="button" name="back" value="Back to Indicators" /></td>
				</tr>
			</table>
		</c:if>
	</stripes:form>
</body>
</html>

