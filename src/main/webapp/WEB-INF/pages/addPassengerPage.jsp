<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<html>
<head>
    <title>Passenger</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="wrapper_center">
        <div class="wrap wrapper-flex type_buy_ticket">
            <div id="search_menu" class="left_menu type_black">
                <div class="type_buy_ticket">
                    <div class="title">Add passenger</div>
                    <form:form method="POST" modelAttribute="passenger" action="/railroad/passenger/add/result">
                        <div class="wrapper_input">
                            <small><spring:message code="lastName"/></small>
                            <div class="wrapper_input_in">
                                <input id="lastname" name="lastName"  type="text" onkeyup="checkLastName(); return false;" value="">
                                <div class="error" id="error_lastName"><form:errors path="lastName"/></div>
                            </div>
                        </div>
                        <div class="wrapper_input">
                            <small><spring:message code="name"/></small>
                            <div class="wrapper_input_in">
                                <input id="name" name="name"  type="text" onkeyup="checkName(); return false;" value="">
                                <div class="error" id="error_name"><form:errors path="name"/></div>
                            </div>
                        </div>
                        <div class="wrapper_input">
                            <small><spring:message code="birthDate"/></small>
                            <div class="wrapper_input_in">
                                <input id="birthDate" name="birthDate"  type="text" placeholder="yyyy-MM-dd" onkeyup="checkBirthDate(); return false;" value="">
                                <div class="error" id="error_birthDate"><form:errors path="birthDate"/></div>
                            </div>
                        </div>
                        <div class="wrapper_input">
                            <small><spring:message code="email"/></small>
                            <div class="wrapper_input_in">
                                <input id="email" name="email"  type="text" onkeyup="checkEmail(); return false;" value="">
                                <div class="error" id="error_email"><form:errors path="email"/></div>
                            </div>
                        </div>
                        <button id="b_button" class="btn btn_blue" type="submit" disabled="true"><spring:message code="buyTicket"/></button>

                    </form:form>
                </div>
            </div>
            <div class="content content_type-train">
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
<script type="text/javascript" src="/resources/js/passengerValidCheck.js"></script>
</body>
</html>
