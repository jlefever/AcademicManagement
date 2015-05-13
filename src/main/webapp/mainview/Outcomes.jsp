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
	<stripes:form action="/Outcomes.action" focus="">
		<h1>Outcomes</h1>
		<h3>${program.name}</h3>
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
			<c:forEach items="${actionBean.outcomes}" var="outcome">
				<tr>
					<td>${outcome.id}</td>
					<td>${outcome.name}</td>
					<td>${outcome.description}</td>
					<td>${outcome.minMet}</td>
					<td>${outcome.programId}</td>
					<!-- View -->
					<td><stripes:link href="/Outcomes.action" event="indicators">View<stripes:param
								name="viewId" value="${outcome.id}" />
						</stripes:link></td>
					<!-- Delete -->
					<c:if test="${user.permissions == 2 }">
						<td><stripes:link href="/Outcomes.action" event="delete">Delete<stripes:param
									name="id" value="${outcome.id}" />
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
					<td><stripes:text class="add" name="minMet" /></td>
					<td></td>
					<td><stripes:submit class="button" name="add" value="Add" /></td>
				</tr>
			</c:if>
		</table>
		<br />
		<!-- Edit -->
		<c:if test="${user.permissions == 2}">
			<h3>Edit Outcome</h3>
			<table>
				<tr>
					<td><stripes:label for="Outcome Id" />:</td>
					<td><stripes:text name="outcomeId" /></td>
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
							value="Edit Outcome" /></td>
				</tr>
				<tr>
				<td><stripes:submit class="button" name="back" value="Back to Programs" /></td>
				</tr>
			</table>
		</c:if>
	</stripes:form>
</body>
</html>

