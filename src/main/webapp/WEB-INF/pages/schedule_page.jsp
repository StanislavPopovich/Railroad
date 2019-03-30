<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
        <p class=""> <a href="<c:url value='/railroad/moderator/add-schedule'/>">Add schedule</a></p>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
        <p class=""> <a href="<c:url value='/railroad/admin/add-schedule'/>">Add schedule</a></p>

    </c:if>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
