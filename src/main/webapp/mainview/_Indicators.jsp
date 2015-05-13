<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
	<table style="vertical-align: top;">
		<tr>
			<td style="width: 25%; vertical-align: top;">
				<!-- Somewhat contrived example of using the errors tag 'action' attribute. -->
				<stripes:errors action="/Indicators.action" /> 
				<stripes:form action="/Indicators.action" focus="">
					<table>
	  					<c:forEach items="${actionBean.indicators}" var="indicators">
	  						<tr>
	  							<td>${indicators}</td>
	  						</tr>
	        			</c:forEach> 
					</table>
					<c:if test="${user.permissions == 2}">
						<table>
							<tr>
								<td style="font-weight: bold;"><stripes:label for="name" />:</td>
								<td><stripes:text name="name" /></td>
								<td style="font-weight: bold;"><stripes:label for="description"/>:</td>
								<td><stripes:text name="description" /></td>
								<td style="font-weight: bold;"><stripes:label for="minMet"/>:</td>
								<td><stripes:text name="minMet" /></td>
							</tr>
							<tr>
								<td style="text-align: center;"><stripes:submit name="add" value="Add" /></td>
							</tr>
					</table>
					<table>
						<tr>
							<td style="font-weight: bold;"><stripes:label for="Indicator Id"/>:</td>
							<td><stripes:text name="indicatorId"/></td>
							<td style="font-weight: bold;"><stripes:label for="New Name" />:</td>
							<td><stripes:text name="newname" /></td>
							<td style="font-weight: bold;"><stripes:label for="New Description"/>:</td>
							<td><stripes:text name="newdescription" /></td>
							<td style="font-weight: bold;"><stripes:label for="New MinMet"/>:</td>
							<td><stripes:text name="newminmet" /></td>
						</tr>
						<tr>
							<td style="text-align: center;"><stripes:submit name="edit" value="Edit Program" /></td>
						</tr>
					</table>
					<table>
						<tr>
							<td style="font-weight: bold;"><stripes:label for="Indicator id"/>:</td>
							<td><stripes:text name="id" /></td>
						</tr>
						<tr>
							<td style="text-align: left;"><stripes:submit name="delete" value="Delete" /></td>
						</tr>
					</table>
					</c:if>
					<table>
						<tr>
							<td style="font-weight: bold;"><stripes:label for="View Measurements for Indicator Id"/>:</td>
							<td><stripes:text name="viewId"/></td>
						</tr>
						<tr>
							<td style="text-align: left;"><stripes:submit name="measurements" value ="View Measurements"/></td>
						</tr>
					</table>
					<table>
						<tr>
							<td style="text-align: left;"><stripes:submit name="back" value = "Back to Outcomes"/></td>
						</tr>
					</table>
				</stripes:form>
			</td>
		</tr>
	</table>
</head>