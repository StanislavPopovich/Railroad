<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Stations</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <table>
        <tr>
            <th width="80">Station</th>
        </tr>
        <c:forEach items="${stations}" var="station">
            <tr>
                <td>${station}</td>
            </tr>
        </c:forEach>
    </table>
    <h2>
        <c:if test="${pageContext.request.isUserInRole('ROLE_MODERATOR')}">
            <a href="<c:url value="/railroad/moderator/add-station"/>"><button>Add new station</button></a><br/>
            <a href="<c:url value='/railroad/moderator/'/>"><button>to moderator page</button></a>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
            <a href="<c:url value="/railroad/admin/add-station"/>"><button>Add new station</button></a><br/>
            <a href="<c:url value='/railroad/admin/'/>"><button>to admin page</button></a>
        </c:if>
    </h2>
</div>

</body>
</html>
