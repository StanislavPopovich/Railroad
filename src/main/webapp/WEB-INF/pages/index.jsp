<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Start page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="find_container">
            <form id="start_station">
                <form:select cssClass="select" id="start" type="text" path="departStation">
                    <form:option value="0"><spring:message code="departStation"/></form:option>
                    <form:options items="${stations}"/>
                </form:select>
            </form>
            <form id="end_station_login">
                <form:select cssClass="select" id="end" type="text" path="arrivalStation">
                    <form:option value="0"><spring:message code="arrivalStation"/></form:option>
                </form:select>
            </form>
            <form id="date_depart">
                <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" var="trainDate"/>
                <form:input cssClass="date" id="date" type="date" path="date" value="${trainDate}"/>
            </form>
            <form id="findButton">
                <button id="findBtn"><spring:message code="findButton"/></button>
            </form>
        </div>
        <div id="find">

        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajaxRequestsHandler.js"></script>
<script type="text/javascript" src="/resources/js/searchDateValidCheck.js"></script>
</body>
</html>
