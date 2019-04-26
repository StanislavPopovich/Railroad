<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                            <div class="departStation_more">${trainForm.departStation}</div>
                            <div class="arrivalStation_more">${trainForm.arrivalStation}</div>
                        </div>
                    </div>
                    <div class="depart_info">
                        <h2><spring:message code="departDateBuy"/></h2>
                        <p>${trainForm.departDate}</p>
                    </div>
                </div>

                <form:form method="POST" modelAttribute="ticket" action="/railroad/ticket/buy">
                    <div class="wrapper_input">
                        <form:label path="passengerDto.lastName"><spring:message code="lastName"/></form:label>
                        <form:input id="lastname" type="text" path="passengerDto.lastName" autofocus="true"
                                    onkeyup="javascript: checkLastName(); return false;"/>
                        <div class="error" id="error_lastName"><form:errors path="passengerDto.lastName"/></div>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="passengerDto.name"><spring:message code="name"/></form:label>
                        <form:input id="name" type="text" path="passengerDto.name"
                                    onkeyup="javascript: checkName(); return false;"/>
                        <div class="error" id="error_name"><form:errors path="passengerDto.name"/></div>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="passengerDto.name"><spring:message code="birthDate"/></form:label>
                        <form:input cssClass="correct" id="birthDate" type="date" path="passengerDto.birthDate" value="${Date}"/>
                        <div class="error" id="error_birthDate"><form:errors path="passengerDto.birthDate"/></div>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="email"><spring:message code="email"/></form:label>
                        <form:input id="email" type="text" path="email"
                        onkeyup="javascript: checkEmail(); return false;"/>
                        <div class="error" id="error_email"><form:errors path="email"/></div>
                    </div>
                    <div class="input_hidden">
                        <form:hidden id="number"  path="trainTicketDto.number" value="${trainForm.number}"/>
                        <form:hidden id="number"  path="trainTicketDto.departDate" value="${trainForm.departDate}"/>
                        <form:hidden id="number"  path="trainTicketDto.arrivalDate" value="${trainForm.arrivalDate}"/>
                        <form:hidden id="number"  path="trainTicketDto.departStation" value="${trainForm.departStation}"/>
                        <form:hidden id="number"  path="trainTicketDto.arrivalStation" value="${trainForm.arrivalStation}"/>
                    </div>
                    <div class="wpapper_btn">
                        <button id="b_button" type="submit" disabled="true"><spring:message code="buyTicket"/></button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/passengerValidCheck.js"></script>
</body>
</html>
