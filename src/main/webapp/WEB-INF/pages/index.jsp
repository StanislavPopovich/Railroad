<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Start page</title>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
<c:set var="now" value = "<%= new java.util.Date()%>" />
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="find_container">
            <form id="start_station">
                <form:select cssClass="select" id="start" type="text" path="startStation">
                    <form:option value="0" label="Select start station"/>
                    <form:options items="${stations}"/>
                </form:select>
            </form>
            <form id="end_station_login">
                <form:select cssClass="select" id="end" type="text" path="endStation">
                    <form:option value="0" label="Select destination station"/>
                </form:select>
            </form>
            <form id="data_depart">
                <form:input cssClass="date" id="date" type="date" path="date" />
            </form>
        </div>
        <div id="find">

        </div>
    </div>
</section>


<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajax_requests_handler.js"></script>
</body>
</html>
