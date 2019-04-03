<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <p class=""> <a href="<c:url value='/railroad/user/add-schedule'/>">Add schedule</a></p>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
