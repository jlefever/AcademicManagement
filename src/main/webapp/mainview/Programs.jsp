<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table style="vertical-align: top;">
	<tr>
		<td style="width: 25%; vertical-align: top;">
			<!-- Somewhat contrived example of using the errors tag 'action' attribute. -->
			<stripes:errors action="/Programs.action" /> <stripes:form
				action="/Programs.action" focus="">

				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="Year" />:</td>
						<td><stripes:text name="viewYear" /></td>
					</tr>
					<tr>
						<td style="test-align: left;"><stripes:submit name="view"
								value="View Programs" /></td>
					</tr>
				</table>
				<table border="1">
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Year</th>
					<c:forEach items="${actionBean.programs}" var="program">
						<tr>
							<td>${program.id}</td>
							<td>${program.name}</td>
							<td>${program.description}</td>
							<td>${program.year}</td>
						</tr>
					</c:forEach>
					<c:if test="${user.permissions == 2 }">
						<tr>
							<td></td>
							<td><stripes:text name="name"/></td>
							<td><stripes:text name="description"/></td>
							<td><stripes:text name="year"/></td>
							<td><stripes:submit name="add" value="Add"/></td>
						</tr>
					</c:if>
				</table>
				<%-- <c:if test="${user.permissions == 2}">
					<table>
						<tr>
							<td style="font-weight: bold;"><stripes:label for="name" />:</td>
							<td><stripes:text name="name" /></td>
							<td style="font-weight: bold;"><stripes:label
									for="description" />:</td>
							<td><stripes:text name="description" /></td>
							<td style="font-weight: bold;"><stripes:label for="year" />:</td>
							<td><stripes:text name="year" /></td>
						</tr>
						<tr>
							<td style="text-align: left;"><stripes:submit name="add"
									value="Add" /></td>
						</tr>
					</table>
					<table>
						<tr>
							<td style="font-weight: bold;"><stripes:label
									for="Program Id" />:</td>
							<td><stripes:text name="progId" /></td>
							<td style="font-weight: bold;"><stripes:label for="New Name" />:</td>
							<td><stripes:text name="newname" /></td>
							<td style="font-weight: bold;"><stripes:label
									for="New Description" />:</td>
							<td><stripes:text name="newdescription" /></td>
							<td style="font-weight: bold;"><stripes:label for="New Year" />:</td>
							<td><stripes:text name="newyearid" /></td>
						</tr>
						<tr>
							<td style="text-align: left;"><stripes:submit name="edit"
									value="Edit Program" /></td>
						</tr>
					</table>
					<table>
						<tr>
							<td style="font-weight: bold;"><stripes:label
									for="Program id" />:</td>
							<td><stripes:text name="id" /></td>
						</tr>
						<tr>
							<td style="text-align: left;"><stripes:submit name="delete"
									value="Delete" /></td>
						</tr>
					</table>
				</c:if> --%>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label
								for="View Outcomes for Program Id" />:</td>
						<td><stripes:text name="viewId" /></td>
					</tr>
					<tr>
						<td style="text-align: left;"><stripes:submit name="outcomes"
								value="View Outcomes" /></td>
					</tr>
				</table>
			</stripes:form>
		</td>
	</tr>
</table>