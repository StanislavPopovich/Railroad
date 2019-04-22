<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Tickets</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="content">
            <div id="ticket_buttons" class="ticket_buttons">
                <div id="upcoming_trips" class="active">
                    Upcoming
                </div>
                <div id="completed_trips">
                    Completed
                </div>
            </div>
            <div id="find_orders">

            </div>
        </div>
    </div>

    <form:form id="currentPassenger_form" modelAttribute="passengerForm" action="" method="post">
        <form:hidden id="currentPassenger_lastName" path="lastName" value="${passenger.lastName}"/>
        <form:hidden id="currentPassenger_name" path="name" value="${passenger.name}"/>
        <form:hidden id="currentPassenger_birthDate" path="birthDate" value="${passenger.birthDate}"/>
    </form:form>

</section>

<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/userPassengerHandler.js"></script>
</body>
</html>
