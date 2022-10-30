<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
</head>
<body>
<%@include file="views/header.jsp" %>

<div class="container">

    <table class="table table-striped table-info">
        <tbody>
        <tr>
            <th>ID</th>
            <th>DATE</th>
            <th>DESCRIPTION</th>
            <th>CALORIES</th>
        </tr>
        <c:forEach var="meal" items="${listMeals}">
            <tr>
                <td>${meal.getId}</td>
                <td>${meal.getDate}</td>
                <td>${meal.getDescription}</td>
                <td>${meal.getCalories}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="container">
        </form>
        <div class="col-lg-4">
            <div>
                <form action="meals" method="post">
                    <label for="get" class="form-label">Id:</label>
                    <input type="number" id="get" name="mealId" value="" class="form-control" required>
                    <button class="btn btn-info" type="submit">Find meal by id</button>
                </form>
            </div>
            <div>

                <c:choose>
                    <c:when test="${!meal.equals('Meal does not  exist')}">
                        <table class="table table-striped table-info">
                            <tr>
                                <td>${meal.getId}</td>
                                <td>${meal.getDate}</td>
                                <td>${meal.getDescription}</td>
                                <td>${meal.getCalories}</td>
                            </tr>
                        </table>
                        <br/>
                    </c:when>
                    <c:otherwise>
                        "${meal}"
                        <br/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%@include file="views/footer.jsp" %>
</body>
</html>