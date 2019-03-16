<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trains</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <h2>Trains</h2>

        <table>
            <tr>
                <th width="100">number</th>
                <th width="100">start station</th>
                <th width="100">end station</th>
                <th width="100">seats</th>
                <th width="100">route</th>
            </tr>
            <c:forEach items="${trains}" var="train">
                <tr>
                    <td>${train.number}</td>
                    <td>${train.startStation}</td>
                    <td>${train.endStation}</td>
                    <td>${train.seats}</td>
                    <td>
                        <c:forEach items="${train.stations}" var="station">
                            ${station},
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>

    <h3>
        <a href="<c:url value="/railroad/moderator/add_train"/>">Add new train</a>
    </h3>
</div>

</body>
</html>
