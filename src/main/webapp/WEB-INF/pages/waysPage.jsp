<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>All wayEntities</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
    <c:if test="${isUser}">
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
                <a href="<c:url value="/railroad/user/add-way"/>"><button>Add new way</button></a><br/>
            </h2>
        </div>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
