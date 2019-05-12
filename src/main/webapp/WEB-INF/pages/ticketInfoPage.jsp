<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>TicketInfo</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div class="wrap wrapper-flex">
            <div class="content content_type-train">
                <h1 class="h1"><spring:message code="ticketInfo"/> № ${ticket.number}</h1>
                <div class="passenger_info">
                    <div>
                        <span><spring:message code="passengerInfo"/>: </span>
                        <span>${ticket.passengerDto.lastName} ${ticket.passengerDto.name}</span>
                    </div>
                    <div>
                        <span><spring:message code="birthDateInfo"/>: </span>
                        <span>${ticket.passengerDto.birthDate}</span>
                    </div>
                </div>
                <div id="items" class="items">
                    <div class="item">
                        <div class="wrapper_item">
                            <div class="train">
                                <div class="img"><img src="/resources/img/train.svg"></div>
                                <div class="trainNumber_1">№ ${ticket.trainTicketDto.number}</div>
                            </div>
                        </div>
                        <div class="wrapper_item">
                            <div class="wrapper_rout">
                                <div class="route">
                                    <div class="label"><span><spring:message code="departStationInfo"/></span></div>
                                    <div class="departStation_1">${ticket.trainTicketDto.departStation}</div>
                                </div>
                                <div class="arrow_right"></div>
                                <div class="route">
                                    <div class="label"><span><spring:message code="arrivalStationInfo"/></span></div>
                                    <div class="arrivalStation_1">${ticket.trainTicketDto.arrivalStation}</div>
                                </div>
                            </div>
                        </div>
                        <div class="wrapper_item">
                            <div class="date">
                                <div class="label"><span><spring:message code="departDateInfo"/>:</span></div>
                                <div class="date_depart_1">${ticket.trainTicketDto.departDate}</div>
                            </div>
                        </div>
                        <div class="wrapper_item">
                            <div class="date">
                                <div class="label"><span><spring:message code="arrivalDateInfo"/>:</span></div>
                                <div class="date_arrival_1">${ticket.trainTicketDto.arrivalDate}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
