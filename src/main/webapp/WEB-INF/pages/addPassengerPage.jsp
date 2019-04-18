<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Passenger</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="modal_form">
            <div class="buy_ticket">
                <div class="ticket_info">
                    <div class="ticket_train">
                        <img src="/resources/img/train.svg">
                        <div><spring:message code="trainBuy"/></div>
                        <div>№  ${trainForm.number}</div>
                    </div>
                    <div class="route">
                        <h2><spring:message code="routeBuy"/></h2>
                        <div>
                            <div class="departStation_more">${trainForm.stations[0]}</div>
                            <div class="arrivalStation_more">${trainForm.stations[1]}</div>
                        </div>
                    </div>
                    <div class="depart_info">
                        <h2><spring:message code="departDateBuy"/></h2>
                        <p>${trainForm.departDate}</p>
                    </div>
                </div>
            </div>
            <form:form method="POST" modelAttribute="ticket" action="/railroad/ticket/buy">
                <div class="wrapper_input">
                    <form:label path="passengerDto.lastName"><spring:message code="lastName"/></form:label>
                    <form:input id="lastname" type="text" path="passengerDto.lastName" autofocus="true"
                                onkeyup="javascript: checkLastName(); return false;"/>
                    <div class="error" id="error_name"><form:errors path="passengerDto.lastName"/></div>
                </div>
                <div class="wrapper_input">
                    <form:label path="passengerDto.name"><spring:message code="name"/></form:label>
                    <form:input id="name" type="text" path="passengerDto.name"
                                onkeyup="javascript: checkName(); return false;"/>
                </div>
                <div class="wrapper_input">
                    <fmt:formatDate value="${birthDate}" pattern="yyyy-MM-dd" var="Date"/>
                    <spring:message code="birthDate"/>
                    <form:input cssClass="date" id="birthDate" type="date" path="passengerDto.birthDate" value="${Date}"
                                onkeyup="javascript: checkBirthDate(); return false;"/>
                </div>
                <div class="wrapper_input">
                    <form:hidden id="number"  path="trainTicketDto.number" value="${trainForm.number}"/>
                    <form:hidden id="number"  path="trainTicketDto.departDate" value="${trainForm.departDate}"/>
                    <form:hidden id="number"  path="trainTicketDto.arrivalDate" value="${trainForm.arrivalDate}"/>
                    <form:hidden id="number"  path="trainTicketDto.stations" value="${trainForm.stations}"/>
                </div>
                <button id="b_button" type="submit" disabled="true"><spring:message code="buyTicket"/></button>
            </form:form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/passenger_valid_check.js"></script>
</body>
</html>
