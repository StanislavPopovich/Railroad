<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <h2 class="h2">Select station and date</h2>
        <div class="schedule_info">
            <form id="start_station">
                <form:select cssClass="select" id="start" type="text" path="station">
                    <form:option value="0"><spring:message code="station"/></form:option>
                    <form:options items="${stations}"/>
                </form:select>
            </form>
            <form id="date_depart">
                <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" var="trainDate"/>
                <form:input cssClass="date" id="date" type="date" path="date" value="${trainDate}"/>
            </form>
            <div id="find_schedule" class="btn btn_blue">Select</div>
        </div>

        <div id="schedule" class="schedule">





        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/userScheduleHandler.js"></script>
</body>
</html>
