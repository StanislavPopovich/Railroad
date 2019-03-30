<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Start page</title>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <form id="start_station">
        <form:select id="start" type="text" path="startStation">
            <form:option value="0" label="Select start station"/>
            <form:options items="${stations}"/>
        </form:select>
        <br/>
    </form>
    <form id="end_station_login">
        <form:select id="end" type="text" path="endStation">
            <form:option value="0" label="Select destination station"/>
        </form:select><br/>
    </form>
    <div id="find">

    </div>


</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajax_requests_handler.js"></script>
</body>
</html>
