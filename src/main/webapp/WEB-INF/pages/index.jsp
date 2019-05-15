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
<section class="section main">
    <div class="wrap">
        <div class="finding_menu">
            <h1>Tickets search</h1>
            <form:form method="POST" cssClass="form" modelAttribute="searchData" action="/railroad/trains">
                <div class="wrapper_dropdown">
                    <small><spring:message code="from"/></small>
                    <div class="wrapper_dropdown_in js-dropdown">
                        <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                        <input id="depart_station" class="js-dropdown_value dropdownCheck" type="text" name="departStation">
                        <ul id="from_stations">
                        </ul>
                    </div>
                </div>
                <div class="wrapper_dropdown">
                    <small><spring:message code="to"/></small>
                    <div class="wrapper_dropdown_in js-dropdown">
                        <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                        <input id="arrival_station" class="js-dropdown_value dropdownCheck" type="text" name="arrivalStation">
                        <ul id="to_stations">
                        </ul>
                    </div>
                </div>
                <div id="date_depart" class="wrapper_input">
                    <small>Departure</small>
                    <div class="wrapper_input_in">
                        <fmt:formatDate value="${currentDate}" pattern="yyyy-MM-dd" var="date"/>
                        <form:input cssClass="date" id="depart_date" type="date" path="departDate" value="${date}"/>
                    </div>
                </div>
                <div id="date_return_depart" class="wrapper_input">
                    <small>Return</small>
                    <div class="wrapper_input_in">
                        <form:input cssClass="date" id="depart_return_date" type="date" path="departReturnDate"/>
                    </div>
                </div>
            </form:form>
            <div class="btn btn_blue" id="findBtn"><spring:message code="findButton"/></div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/findingMenuHandler.js"></script>
<script type="text/javascript" src="/resources/js/searchDateValidCheck.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
</body>
</html>
