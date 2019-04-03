<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Ticket</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div>
                <form:form method="POST" modelAttribute="passenger" action="/railroad/user/buy-ticket/result">
                    <div class="wrapper_input">
                        <form:label path="lastName"> Lastname</form:label>
                        <form:input id="lastname" type="text" path="lastName" autofocus="true"/>
                        <form:label path="name">name</form:label>
                        <form:input id="name" type="text" path="name"/>
                        <fmt:formatDate value="${birthDate}" pattern="yyyy-MM-dd" var="Date"/>
                        <form:input cssClass="date" id="date" type="date" path="birthDate" value="${Date}"/>
                        <form:hidden path="userName" value="${pageContext.request.remoteUser}"/>
                        <button id="button" type="submit">Buy</button>
                    </div>
                </form:form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajax_requests_handler.js"></script>
</body>
</html>
