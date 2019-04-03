<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Stations</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')" var="isUser"/>
    <c:if test="${isUser}">
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
                <a href="<c:url value="/railroad/user/add-station"/>"><button>Add new station</button></a><br/>
            </h2>
        </div>
    </c:if>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
