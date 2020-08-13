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
        <h1>Vacations</h1>
        
        <div class="card">
            <div class="card-header">${country.name}</div>
            <div class="card-body">
                <ul class="list-group">
                    <li class="list-group-item bg-dark text-light">Destinations: ${country.destinations.size()}</li>
                    <c:forEach items="${country.destinations}" var="dest">
                        <li class="list-group-item">
                            ${dest.name}<br>
                            ${dest.description}
                        </li>
                    </c:forEach>
                    <li class="list-group-item">
                        <form:form class="form-inline" action="/destination/${country.id}" method="post" modelAttribute="destination">
                            <form:input path="name" class="form-control" placeholder="Destination name" />
                            <form:input path="description" class="form-control" placeholder="Description" />
                            <input type="hidden" name="country" value="${country.id}" />
                            <input type="submit" class="btn btn-info" value="Add Destination to ${country.name}" />
                        </form:form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>