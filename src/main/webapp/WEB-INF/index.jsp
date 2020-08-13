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
        
        <div class="row">
            <div class="col">
                <h3>Add a Country</h3>
                <form:form action="/country" method="post" modelAttribute="country">
                    <div class="form-group">
                        <label>Country Name:</label>
                        <form:input path="name" class="form-control" />
                        <form:errors path="name" class="text-danger" />
                    </div>
                    <input type="submit" class="btn btn-primary" value="Add Country" />
                </form:form>
            </div>
            <div class="col">
                <h3>Add a Destination</h3>
                <form:form action="/destination" method="post" modelAttribute="destination">
                    <div class="form-group">
                        <label>Destination Name:</label>
                        <form:input path="name" class="form-control" />
                        <form:errors path="name" class="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>Description:</label>
                        <form:textarea path="description" class="form-control" />
                        <form:errors path="description" class="text-danger" />
                    </div>
                    <div class="form-group">
                        <label>Country:</label>
                        <select name="country" class="form-control">
                            <c:forEach items="${countries}" var="cnt">
                                <option value="${cnt.id}">${cnt.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Add Destination" />
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>