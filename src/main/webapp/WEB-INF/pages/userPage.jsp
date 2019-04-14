<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>User page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
            <div id="find_orders">

            </div>
            <form:form modelAttribute="ticket" action="/railroad/user/ticket-more" method="post">
                <form:hidden id="trainTicketDto_more_number" path="trainTicketDto.number" value=""/>
                <form:hidden id="trainTicketDto_more_departDate" path="trainTicketDto.departDate" value=""/>
                <form:hidden id="trainTicketDto_more_arrivalDate" path="trainTicketDto.arrivalDate" value=""/>
                <form:hidden id="trainTicketDto_more_stations" path="trainTicketDto.stations" value=""/>
                <form:hidden id="passengerTicketDto_more_lastName" path="passengerDto.lastName" value=""/>
                <form:hidden id="passengerTicketDto_more_name" path="passengerDto.name" value=""/>
                <form:hidden id="passengerTicketDto_more_birthDate" path="passengerDto.birthDate" value=""/>
            </form:form>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <div id="find_trains">

            </div>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <div id="find_users">

            </div>
        </c:if>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajaxUserHandler.js"></script>
</body>
</html>
