<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Outcomes</title>
<link rel="stylesheet" type="text/css" href="/style.css" />
</head>
<body>
	<stripes:form action="/Indicators.action" focus="">
		<h1>Indicators</h1>
		<h5>Program - ${program.name}</h5>
		<h5>Outcome - ${outcome.name}</h5>
		<table class="gridtable">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Min Met</th>
				<th>Program Id</th>
				<th></th>
				<c:if test="${user.permissions == 2}">
					<th></th>
				</c:if>
			</tr>
			<c:forEach items="${actionBean.indicators}" var="indicator">
				<tr>
					<td>${indicator.id}</td>
					<td>${indicator.name}</td>
					<td>${indicator.description}</td>
					<td>${indicator.minMet}</td>
					<td>${indicator.outcomeId}</td>
					<!-- View -->
					<td><stripes:link href="/Indicators.action"
							event="measurements">View<stripes:param
								name="viewId" value="${indicator.id}" />
						</stripes:link></td>
					<!-- Delete -->
					<c:if test="${user.permissions == 2 }">
						<td><stripes:link href="/Indicators.action" event="delete">Delete<stripes:param
									name="id" value="${indicator.id}" />
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
					<td><stripes:text class="add" name="minmet" /></td>
					<td></td>
					<td><stripes:submit class="button" name="add" value="Add" /></td>
				</tr>
			</c:if>
		</table>
		<br />
		<!-- Edit -->
		<c:if test="${user.permissions == 2}">
			<h3>Edit Indicator</h3>
			<table>
				<tr>
					<td><stripes:label for="Indicator Id" />:</td>
					<td><stripes:text name="indicatorId" /></td>
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
					<td><stripes:label for="New Min Met" />:</td>
					<td><stripes:text name="newminmet" /></td>
				</tr>
				<tr>
					<td><stripes:submit class="button" name="edit"
							value="Edit Indicator" /></td>
				</tr>
			</table>
		</c:if>
		<stripes:submit class="button" name="back" value="Back to Outcomes" />
	</stripes:form>
</body>
</html>

