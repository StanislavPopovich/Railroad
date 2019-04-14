<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Passenger</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="modal_form">
            <form:form method="POST" modelAttribute="ticket" action="/railroad/user/ticket/buy">
                <div class="wrapper_input">
                    <form:label path="passengerDto.lastName">Last Name</form:label>
                    <form:input id="lastname" type="text" path="passengerDto.lastName" autofocus="true"/>
                </div>
                <div class="wrapper_input">
                    <form:label path="passengerDto.name">Name</form:label>
                    <form:input id="lastname" type="text" path="passengerDto.name" autofocus="true"/>
                </div>
                <div class="wrapper_input">
                    <fmt:formatDate value="${birthDate}" pattern="yyyy-MM-dd" var="Date"/>
                    <form:input cssClass="date" id="date" type="date" path="passengerDto.birthDate" value="${Date}"/>
                </div>
                <div class="wrapper_input">
                    <form:hidden id="number"  path="trainTicketDto.number" value="${trainForm.number}"/>
                    <form:hidden id="number"  path="trainTicketDto.departDate" value="${trainForm.departDate}"/>
                    <form:hidden id="number"  path="trainTicketDto.arrivalDate" value="${trainForm.arrivalDate}"/>
                    <form:hidden id="number"  path="trainTicketDto.stations" value="${trainForm.stations}"/>
                </div>
                <button id="b_button" type="submit">Buy</button>
            </form:form>
        </div>
    </div>
</section>
</body>
</html>
