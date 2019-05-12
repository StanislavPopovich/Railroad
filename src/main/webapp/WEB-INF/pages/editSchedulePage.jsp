<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div id="" class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu type_black">
                <div>
                    <div class="title"><spring:message code="editTrainSchedule"/></div>
                    <div class="wrapper_dropdown">
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="trainNumber"/></span>
                            <input id="train_number" class="js-dropdown_value dropdownCheck" type="text"
                                   name="departStation">
                            <ul id="train_numbers">

                            </ul>
                        </div>
                    </div>
                    <div class="wrapper_dropdown">
                        <div class="wrapper_dropdown_in js-dropdown">
                            <span class="dropdown_value dropdown"><spring:message code="trainDates"/></span>
                            <input id="train_date" class="js-dropdown_value dropdownCheck" type="text"
                                   name="arrivalStation">
                            <ul id="train_dates">
                            </ul>
                        </div>
                    </div>
                    <button class="btn btn_blue" id="change_train_schedule" type="submit" disabled="true">
                        <spring:message code="changeSchedule"/></button>
                </div>
            </div>
            <div class="content content_type-schedule">
                <div id="updateSchedule" class="items">

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/generalFunctions.js"></script>
<script type="text/javascript" src="/resources/js/scheduleHandler.js"></script>
<script type="text/javascript" src="/resources/js/message.js"></script>
<script type="text/javascript" src="/resources/js/validator.js"></script>
</body>
</html>
