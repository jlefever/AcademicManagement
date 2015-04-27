<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table style="vertical-align: top;">
	<tr>
		<td style="width: 25%; vertical-align: top;">
			<!-- Somewhat contrived example of using the errors tag 'action' attribute. -->
			<stripes:errors action="/Programs.action" /> 
			<stripes:form action="/Programs.action" focus="">
				<table>
  					<c:forEach items="${actionBean.programs}" var="programs">
  						<tr>
  							<td>${programs}</td>
  						</tr>
        			</c:forEach> 
				</table>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="Year"/>:</td>
						<td><stripes:text name="view"/></td>
					</tr>
					<tr>
						<td style="test-align: center;"><stripes:submit name="view" value = "View Programs"/></td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="name" />:</td>
						<td><stripes:text name="name" /></td>
						<td style="font-weight: bold;"><stripes:label for="description"/>:</td>
						<td><stripes:text name="description" /></td>
						<td style="font-weight: bold;"><stripes:label for="year"/>:</td>
						<td><stripes:text name="year" /></td>
					</tr>
					<tr>
						<td style="text-align: center;"><stripes:submit name="add" value="Add" /></td>
					</tr>
				</table>
			</stripes:form>
		</td>
	</tr>
</table>