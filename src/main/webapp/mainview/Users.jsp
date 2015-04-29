<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table style="vertical-align: top;">
	<tr>
		<td style="width: 25%; vertical-align: top;">
			<!-- Somewhat contrived example of using the errors tag 'action' attribute. -->
			<stripes:errors action="/Users.action" /> 
			<stripes:form action="/Users.action" focus="">
				<table>
  					<c:forEach items="${actionBean.users}" var="users">
  						<tr>
  							<td>${users}</td>
  						</tr>
        			</c:forEach> 
				</table>
				<table>
					<tr>
						<td style="test-align: left;"><stripes:submit name="view" value = "View Users"/></td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="Username" />:</td>
						<td><stripes:text name="name" /></td>
						<td style="font-weight: bold;"><stripes:label for="Email"/>:</td>
						<td><stripes:text name="email" /></td>
						<td style="font-weight: bold;"><stripes:label for="Password" />:</td>
						<td><stripes:text name="pwd" /></td>
						<td style="font-weight: bold;"><stripes:label for="Permissions"/>:</td>
						<td><stripes:text name="permissions" /></td>
					</tr>
					<tr>
						<td style="text-align: left;"><stripes:submit name="add" value="Add User" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="Username"/>:</td>
						<td><stripes:text name="userId"/></td>
						<td style="font-weight: bold;"><stripes:label for="New Email" />:</td>
						<td><stripes:text name="newemail" /></td>
						<td style="font-weight: bold;"><stripes:label for="New Password" />:</td>
						<td><stripes:text name="newpwd" /></td>
						<td style="font-weight: bold;"><stripes:label for="New Permissions"/>:</td>
						<td><stripes:text name="newpermissions" /></td>
					</tr>
					<tr>
						<td style="text-align: left;"><stripes:submit name="edit" value="Edit User" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="Username"/>:</td>
						<td><stripes:text name="id" /></td>
					</tr>
					<tr>
						<td style="text-align: left;"><stripes:submit name="delete" value="Delete User" /></td>
					</tr>
				</table>
			</stripes:form>
		</td>
	</tr>
</table>