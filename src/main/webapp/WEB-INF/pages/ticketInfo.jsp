<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>TicketInfo</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div class="content">
            <h2><spring:message code="ticketInfo"/> № ${ticket.number}</h2>
            <div class="item_ticket">
                <div class="ticket_train">
                    <img src="/resources/img/train.svg">
                    <div><spring:message code="trainInfo"/></div>
                    <div>№ ${ticket.trainTicketDto.number}</div>
                </div>
                <div class="ticket_info">
                    <div class="passenger_info">
                        <h3><spring:message code="passengerInfo"/></h3>
                        <p>${ticket.passengerDto.lastName} ${ticket.passengerDto.name}</p>
                        <div>
                            <p><spring:message code="birthDateInfo"/>:</p>
                            <small>${ticket.passengerDto.birthDate}</small>
                        </div>
                    </div>
                    <div class="depart_info">
                        <h3><spring:message code="departStationInfo"/></h3>
                        <p>${ticket.trainTicketDto.stations.get(0)}</p>
                        <div>
                            <p><spring:message code="departDateInfo"/>:</p>
                            <small>${ticket.trainTicketDto.departDate}</small>
                        </div>
                    </div>
                    <div class="arrival_info">
                        <h3><spring:message code="arrivalStationInfo"/></h3>
                        <p>${ticket.trainTicketDto.stations.get(1)}</p>
                        <div>
                            <p><spring:message code="arrivalDateInfo"/>:</p>
                            <small>${ticket.trainTicketDto.arrivalDate}</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
