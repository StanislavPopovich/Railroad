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
    <div class="wrapper_center">
        <div class="wrap wrapper-flex">
            <div id="search_menu" class="left_menu type_black">
                <div>
                    <div class="title">All orders</div>
                    <div id="upcoming" class="btn btn_blue active">Upcoming trips</div>
                    <div id="completed" class="btn btn_blue">Completed trips</div>
                </div>
            </div>
            <div class="content content_type-train">
                <div id="items" class="items">

                </div>
            </div>
        </div>
    </div>
    <form:form modelAttribute="passengerForm" action="" method="post">
        <form:hidden id="currentPassenger_lastName" path="lastName" value="${currentPassenger.lastName}"/>
        <form:hidden id="currentPassenger_name" path="name" value="${currentPassenger.name}"/>
        <form:hidden id="currentPassenger_birthDate" path="birthDate" value="${currentPassenger.birthDate}"/>
    </form:form>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/passengerTickets.js"></script>
</body>
</html>
