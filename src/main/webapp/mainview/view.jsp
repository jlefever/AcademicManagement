<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head><title>View</title></head>
<body>
<h1>View</h1>

This is a list of Programs; don't get too exicistedskfj.

<stripes:form beanclass="edu.ycp.cs320.acadman.view.MyActionBean">
    <table class="display">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Year</th>
        </tr>
        <c:forEach items="${programManager.allPrograms}" var="program" varStatus="loop">
            <tr>
                <td>
                    ${program.id}
                    <stripes:hidden name="program[${loop.index}].id" value="${program.id}"/>
                </td>
                <td>
                    <stripes:text name="program[${loop.index}].name" value="${person.name}"/>
                </td>
                <td>
                    <stripes:text name="program[${loop.index}].description" value="${person.description}"/>
                </td>
                <td>
                    <stripes:text name="program[${loop.index}].year" value="${person.year}"/>
                </td>
            </tr>
            <c:set var="newIndex" value="${loop.index + 1}" scope="page"/>
        </c:forEach>
        <%-- And now, an empty row, to allow the adding of new users. --%>
        <tr>
            <td></td>
            <td></td>
            <td>
                <stripes:text name="program[${newIndex}].name"/>
            </td>
            <td>
                <stripes:text name="program[${newIndex}].description"/>
            </td>
            <td>
                <stripes:text name="program[${newIndex}].year"/>
            </td>
        </tr>
    </table>
 
    <div class="buttons">
        <stripes:submit name="Save" value="Save Changes"/>
    </div>
</stripes:form>

</body>
</html>