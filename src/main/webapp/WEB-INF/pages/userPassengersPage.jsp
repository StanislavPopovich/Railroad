<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="content">
            <c:if test="${!empty passengers}">
                <c:forEach items="${passengers}" var="passenger">
                    <div class="passenger_info">
                        <p>${passenger.lastName} ${passenger.name} </p>
                    </div>
                </c:forEach>
            </c:if>

        </div>
    </div>
</section>
</body>
</html>
