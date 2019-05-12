<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Schedule</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div id="" class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu type_black">
                <div>
                    <div class="title"><spring:message code="selectMenuAndDate"/><br/></div>
                    <div class="wrapper_dropdown">
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="selectStation"/></span>
                            <input id="depart_station" class="js-dropdown_value dropdownCheck" type="text"
                                   name="departStation">
                            <ul id="from_stations">
                            </ul>
                        </div>
                    </div>
                    <div id="date_depart" class="wrapper_input">
                        <div class="wrapper_input_in">
                            <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" var="trainDate"/>
                            <form:input cssClass="date" id="date" type="date" path="date" value="${trainDate}"/>
                        </div>
                    </div>
                    <div id="find_schedule" class="btn btn_blue"><spring:message code="select"/></div>
                </div>
            </div>
            <div class="content content_type-schedule">
                <div id="items" class="items">

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script type="text/javascript" src="/resources/js/scheduleHandler.js"></script>
<script type="text/javascript" src="/resources/js/validator.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
</body>
</html>
