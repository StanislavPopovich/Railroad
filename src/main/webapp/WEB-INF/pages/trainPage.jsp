<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trains</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <h2>Trains</h2>
        <table>
            <tr>
                <th width="100">number</th>
                <th width="100">seats</th>
                <th width="100">route</th>
            </tr>
            <c:forEach items="${trains}" var="train">
                <tr>
                    <td>${train.number}</td>
                    <td>${train.seats}</td>
                    <td>
                        <c:forEach items="${train.stations}" var="station" varStatus="status">
                            ${station}
                            <c:if test="${!status.last}">
                               =>
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <h3>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <a href="<c:url value="/railroad/moderator/add-train"/>"><button>Add new train</button></a><br/>
            <a href="<c:url value='/railroad/moderator/'/>"><button>to moderator page</button></a>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="<c:url value="/railroad/admin/add-train"/>"><button>Add new train</button></a><br/>
            <a href="<c:url value='/railroad/admin/'/>"><button>to admin page</button></a>
        </c:if>
    </h3>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
