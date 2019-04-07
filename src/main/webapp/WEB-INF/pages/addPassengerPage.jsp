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
            <form:form method="POST" modelAttribute="passenger" action="/railroad/user/ticket/buy">
                <div class="wrapper_input">
                    <form:label path="lastName"> Lastname</form:label>
                    <form:input id="lastname" type="text" path="lastName" autofocus="true"/>
                </div>
                <div class="wrapper_input">
                    <form:label path="name">name</form:label>
                    <form:input id="name" type="text" path="name"/>
                </div>
                <div class="wrapper_input">
                    <fmt:formatDate value="${birthDate}" pattern="yyyy-MM-dd" var="Date"/>
                    <form:input cssClass="date" id="date" type="date" path="birthDate" value="${Date}"/>
                </div>
                <button id="button" type="submit">Buy</button>
            </form:form>
        </div>
    </div>
</section>

</body>
</html>
