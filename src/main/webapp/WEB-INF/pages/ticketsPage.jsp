
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tickets</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="main">
    <div class="container">
        <div id="ticket_buttons" class="ticket_buttons">
            <div id="upcoming_trips" class="active">
                Forthcoming
            </div>
            <div id="completed_trips">
                Completed
            </div>
        </div>
        <div id="find_orders">

        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="/resources/js/userTicketsHandler.js"></script>
</body>
</html>
