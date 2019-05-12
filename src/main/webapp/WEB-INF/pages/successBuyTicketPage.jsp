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
    <div class="wrapper_center">
        <div class="wrap wrapper-flex">
            <div class="content content_type-train">
                <div class="success">
                    <h1>Success</h1>
                    <div>
                        <span>Passenger: </span>
                        <span>${passenger.lastName} ${passenger.name}</span>
                    </div>
                </div>
                <div id="items" class="items">
                    <c:choose>
                        <c:when test="${not empty trainTicket.returnTrain}">
                            <c:choose>
                                <c:when test="${not empty trainTicket.toTrain.secondTrain && not
                                empty trainTicket.returnTrain.secondTrain}">
                                    <%--FIRST TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.firstTrain.number}</div>
                                            </div>
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.secondTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.secondTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.secondTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.firstTrain.departDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.secondTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.secondTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <%--SECOND TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.returnTrain.firstTrain.number}</div>
                                            </div>
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.returnTrain.secondTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.returnTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.returnTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.returnTrain.secondTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.returnTrain.secondTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.returnTrain.firstTrain.departDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.returnTrain.secondTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.returnTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.returnTrain.secondTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${not empty trainTicket.toTrain.secondTrain && empty
                                trainTicket.returnTrain.secondTrain}">
                                    <%--FIRST TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.firstTrain.number}</div>
                                            </div>
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.secondTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.secondTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.secondTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.firstTrain.departDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.secondTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.secondTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <%--SECOND TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.returnTrain.firstTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.returnTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.returnTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.returnTrain.firstTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.returnTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${empty trainTicket.toTrain.secondTrain && not
                                empty trainTicket.returnTrain.secondTrain}">
                                    <%--FIRST TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.firstTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.firstTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <%--SECOND TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.returnTrain.firstTrain.number}</div>
                                            </div>
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.returnTrain.secondTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.returnTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.returnTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.returnTrain.secondTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.returnTrain.secondTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.returnTrain.firstTrain.departDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.returnTrain.secondTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.returnTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.returnTrain.secondTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <%--FIRST TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.firstTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.firstTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <%--SECOND TRAIN--%>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.returnTrain.firstTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.returnTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.returnTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.returnTrain.firstTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.returnTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${not empty trainTicket.toTrain.secondTrain}">
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.firstTrain.number}</div>
                                            </div>
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.secondTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.secondTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.secondTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.firstTrain.departDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.secondTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.secondTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="item">
                                        <div class="wrapper_item">
                                            <div class="train">
                                                <div class="img"><img src="/resources/img/train.svg"></div>
                                                <div class="trainNumber_1">№${trainTicket.toTrain.firstTrain.number}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="wrapper_rout">
                                                <div class="route">
                                                    <div class="label"><span>From</span></div>
                                                    <div class="departStation_1">${trainTicket.toTrain.firstTrain.departStation}</div>
                                                </div>
                                                <div class="arrow_right"></div>
                                                <div class="route">
                                                    <div class="label"><span>To</span></div>
                                                    <div class="arrivalStation_1">${trainTicket.toTrain.firstTrain.arrivalStation}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Departure date</span></div>
                                                <div class="date_depart_1">${trainTicket.toTrain.firstTrain.departDate}</div>
                                            </div>
                                        </div>
                                        <div class="wrapper_item">
                                            <div class="date">
                                                <div class="label"><span>Arrival date</span></div>
                                                <div class="date_arrival_1">${trainTicket.toTrain.firstTrain.arrivalDate}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>
