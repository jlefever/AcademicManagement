<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<table style="vertical-align: top;">
	<tr>
		<td style="vertical-align: top;">
			<c:choose>
				<c:when test="${empty user}">
					<p>Please create a username and password</p>
				</c:when>
			<c:otherwise>
        		<p>logged in as '${user.username}'.</p>
        	</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>