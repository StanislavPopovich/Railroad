<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All ways</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th width="200">Start station</th>
            <th width="200">End station</th>
            <th width="200">Distance</th>
        </tr>
        <c:forEach items="${ways}" var="way">
            <tr>
                <td>${way.firstStation}</td>
                <td>${way.secondStation}</td>
                <td>${way.distance}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>
        <a href="<c:url value="/railroad/moderator/add_way"/>">Add new ways</a>
    </h2>
</div>
</body>
</html>
