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
                                <a href="/railroad/schedule/add" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>Delete train from schedule</div>
                                <a href="/railroad/schedule/delete" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>Change schedule of train</div>
                                <a href="/railroad/schedule/edit" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>View schedule</div>
                                <a href="/railroad/user/schedule" class="btn btn_blue">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="function">
                    <h2>Trains</h2>
                    <div>
                        <ul>
                            <li>
                                <div>View all trains</div>
                                <a href="/railroad/trains" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>Add train</div>
                                <a href="/railroad/train/add" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>Delete train</div>
                                <a href="/railroad/train/delete" class="btn btn_blue">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="function">
                    <h2>Stations</h2>
                    <div>
                        <ul>
                            <li>
                                <div>Add station</div>
                                <a href="/railroad/station/add" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>Change station</div>
                                <a href="/railroad/station/update" class="btn btn_blue">Next</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="function">
                    <h2>Passengers</h2>
                    <div>
                        <ul>
                            <li>
                                <div>View all passengers of train</div>
                                <a href="/railroad/passenger/" class="btn btn_blue">Next</a>
                            </li>
                            <li>
                                <div>Change passenger</div>
                                <a href="/railroad/station/update" class="btn btn_blue">Next</a>
                            </li>
                        </ul>
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
