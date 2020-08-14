<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Vacations</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.5.0/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="jumbotron">
            <h1>${destination.name}, ${destination.country.name}</h1>
        </div>
        <ul class="list-group">
            <li class="list-group-item bg-dark text-light">Activities:</li>
            <c:forEach items="${destination.activities}" var="act">
                <li class="list-group-item">
                    ${act.name}
                    <a href="/remove/${destination.id}/${act.id}" class="close">&times;</a>
                </li>
            </c:forEach>
            <li class="list-group-item">
                <form:form action="/destination/${destination.id}/planActivity" method="post" modelAttribute="activity" class="form-inline">
                    <form:input path="name" class="form-control" list="acts" />
                    <datalist id="acts">
                        <c:forEach items="${activities}" var="ac">
                            <option>${ac.name}</option>
                        </c:forEach>
                    </datalist>
                    <input type="submit" value="Plan Activity" class="btn btn-primary" />
                </form:form> 
            </li>
        </ul>
    </div>
</body>
</html>