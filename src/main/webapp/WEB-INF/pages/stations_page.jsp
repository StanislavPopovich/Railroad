<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Stations</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <table>
        <tr>
            <th width="80">Station</th>
        </tr>
        <c:forEach items="${stations}" var="station">
            <tr>
                <td>${station}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>
        <a href="<c:url value="/railroad/moderator/add_station"/>">Add new station</a>
    </h2>
</div>

</body>
</html>
