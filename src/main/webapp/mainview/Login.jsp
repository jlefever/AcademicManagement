<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="/style.css" />
</head>
<body>
	<stripes:errors action="/Login.action" />
	<stripes:form action="/Login.action" focus="">
		<table>
			<tr>
				<td style="font-weight: bold;"><stripes:label for="username" />:</td>
			</tr>
			<tr>
				<td><stripes:text name="username" /></td>
			</tr>
			<tr>
				<td style="font-weight: bold;"><stripes:label for="password" />:</td>
			</tr>
			<tr>
				<td><stripes:password name="password" /></td>
			</tr>
			<tr>
				<td>
					<%-- If the security servlet attached a targetUrl, carry that along. --%>
					<stripes:hidden name="targetUrl"
						value="${request.parameterMap['targetUrl']}" /> <stripes:submit class="button"
						name="login" value="Login" />
				</td>
			</tr>
		</table>
	</stripes:form>
</body>
</html>