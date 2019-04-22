<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix = "spring"  uri = "http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>User page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <c:if test="${pageContext.request.isUserInRole('ROLE_USER')}">
            <div id="find_orders">

            </div>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <div class="functions_moderator">
                <div class="function">
                    <h2>Schedule</h2>
                    <div>
                        <ul>
                            <li>
                                <div>Add train to schedule</div>
                                <a href="/railroad/schedule/add" class="btn btn_blue">Add</a>
                            </li>
                            <li>
                                <div>Delete train from schedule</div>
                                <a href="/railroad/schedule/delete" class="btn btn_red">Delete</a>
                            </li>
                            <li>
                                <div>Change schedule of train</div>
                                <a href="" class="btn btn_yellow">Change</a>
                            </li>
                            <li>
                                <div>Look schedule for trains</div>
                                <a href="" class="btn btn_green">Look</a>
                            </li>
                        </ul>




                    </div>
                </div>
                <div class="function">
                    <h2>Trains</h2>
                    <div>

                    </div>
                </div>
                <div class="function">
                    <h2>Stations</h2>
                    <div>

                    </div>
                </div>
                <div class="function">
                    <h2>Passengers</h2>
                    <div>

                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <div id="find_users">

            </div>
        </c:if>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/ajaxStartPageHandler.js"></script>
</body>
</html>
