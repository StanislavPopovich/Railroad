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
                    <form:hidden id="number"  path="trainDto.number" value="${train.number}"/>
                    <form:hidden id="number"  path="trainDto.departDate" value="${train.departDate}"/>
                    <form:hidden id="number"  path="trainDto.arrivalDate" value="${train.arrivalDate}"/>
                    <form:hidden id="number"  path="trainDto.stations" value="${train.stations}"/>
                </div>
                <button id="b_button" type="submit">Buy</button>
            </form:form>
        </div>
    </div>
</section>
</body>
</html>
