<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>User meals</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
</head>
<body>
<%@include file="views/header.jsp" %>

<div class="container">

    <c:choose>
        <c:when test="${user!= null}">
            <h4 class="text-info text-right-">${user.getName()} ${user.getEmail} ${user.getCalories}</h4></c:when>
        <c:otherwise> "${user}"</c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${!mealList.equals('Meals does not exist')}">
            <table class="table table-striped table-info">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>DATE</th>
                    <th>DESCRIPTION</th>
                    <th>CALORIES</th>
                </tr>
                <c:forEach var="meal" items="${mealList}">
                    <tr>
                        <td>${meal.getId}</td>
                        <td>${meal.getDate}</td>
                        <td>${meal.getDescription}</td>
                        <td>${meal.getCalories}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>${mealList}</c:otherwise>
    </c:choose>
</div>
<%@include file="views/footer.jsp" %>
</body>
</html>