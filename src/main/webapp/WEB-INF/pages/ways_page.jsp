<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All wayEntities</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <table>
        <tr>
            <th width="200">Start stationEntity</th>
            <th width="200">End stationEntity</th>
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
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <a href="<c:url value="/railroad/moderator/add-way"/>"><button>Add new way</button></a><br/>
            <a href="<c:url value='/railroad/moderator/'/>"><button>to moderator page</button></a>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="<c:url value="/railroad/admin/add-way"/>"><button>Add new way</button></a><br/>
            <a href="<c:url value='/railroad/admin/'/>"><button>to admin page</button></a>
        </c:if>
    </h2>
</div>
</body>
</html>
