<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Delete schedule</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="select_train">
            <form:select cssClass="select" id="train_number" type="text" path="trainsNumbers">
                <form:option value="0"><spring:message code="trainNumbers"/></form:option>
                <form:options items="${trainsNumbers}"/>
            </form:select>
            <form:select cssClass="select" id="train_dates" type="text" path="departDate">
                <form:option value="0"><spring:message code="departDateInfo"/></form:option>
            </form:select>
            <div id="btn_train_number" class="btn btn_blue">delete train from schedule</div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/scheduleHandler.js"></script>
</body>
</html>
