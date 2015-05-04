<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table style="vertical-align: top;">
	<tr>
		<td style="width: 25%; vertical-align: top;">
			<!-- Somewhat contrived example of using the errors tag 'action' attribute. -->
			<stripes:errors action="/Rubrics.action" /> 
			<stripes:form action="/Rubrics.action" focus="">
				<table>
  					<tr>
  						<td>${actionBean.rubric}</td>
  					</tr>
				</table>
				<table>
					<tr>
						<td style="font-weight: bold;"><stripes:label for="Below Expectations"/>:</td>
						<td><stripes:text name="below"/></td>
						<td style="font-weight: bold;"><stripes:label for="Meets Expectations" />:</td>
						<td><stripes:text name="meets" /></td>
						<td style="font-weight: bold;"><stripes:label for="Exceeds Expectations"/>:</td>
						<td><stripes:text name="exceeds" /></td>
						<td style="font-weight: bold;"><stripes:label for="Target"/>:</td>
						<td><stripes:text name="target" /></td>
					</tr>
					<tr>
						<td style="text-align: center;"><stripes:submit name="edit" value="Submit Data" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="text-align: left;"><stripes:submit name="back" value = "Back to Measurement"/></td>
					</tr>
				</table>
			</stripes:form>
		</td>
	</tr>
</table>