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
                <c:if test="${alreadyBought}">
                    <p><spring:message code="alreadyBought"/></p>
                </c:if>
                <form:form method="POST" modelAttribute="passenger" action="/railroad/passenger/add">
                    <div class="wrapper_input">
                        <form:label path="lastName"><spring:message code="lastName"/></form:label>
                        <form:input id="lastname" type="text" path="lastName" autofocus="true"
                                    onkeyup="checkLastName(); return false;"/>
                        <div class="error" id="error_lastName"><form:errors path="lastName"/></div>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="name"><spring:message code="name"/></form:label>
                        <form:input id="name" type="text" path="name"
                                    onkeyup="checkName(); return false;"/>
                        <div class="error" id="error_name"><form:errors path="name"/></div>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="birthDate"><spring:message code="birthDate"/></form:label>
                        <form:input  id="birthDate" type="text" path="birthDate" placeholder="yyyy-MM-dd"
                        onkeyup="checkBirthDate(); return false"/>
                        <div class="error" id="error_birthDate"><form:errors path="birthDate"/></div>
                    </div>
                    <div class="wrapper_input">
                        <form:label path="email"><spring:message code="email"/></form:label>
                        <form:input id="email" type="text" path="email"
                        onkeyup="checkEmail(); return false;"/>
                        <div class="error" id="error_email"><form:errors path="email"/></div>
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
