<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="buy_ticket buy_ticket_successful">
            <h1>Successful</h1>
            <div class="ticket_info">
                <div class="ticket_train">
                    <img src="/resources/img/train.svg">
                    <div><spring:message code="trainBuy"/></div>
                    <div>№  ${ticket.trainTicketDto.number}</div>
                </div>
                <div class="caption_passenger">
                    <h2>Passenger</h2>
                    <p>${ticket.passengerDto.lastName} ${ticket.passengerDto.name}</p>
                </div>
                <div class="route">
                    <h2><spring:message code="routeBuy"/></h2>
                    <div>
                        <div class="departStation_more">${ticket.trainTicketDto.stations[0]}</div>
                        <div class="arrivalStation_more">${ticket.trainTicketDto.stations[1]}</div>
                    </div>
                </div>
                <div class="depart_info">
                    <h2><spring:message code="departDateBuy"/></h2>
                    <p>${ticket.trainTicketDto.departDate}</p>
                </div>
            </div>
            <a class="btn" href="/railroad/ticket/buy/success">To main page</a>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
