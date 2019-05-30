<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Products and Categories</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	<body>
	<div class="container">
		<h1>Products and Categories</h1>
		<a href="/">Home</a> | <a href="/categories/new">Add a Category</a>
		<form:form action="/products/new" method="POST" modelAttribute="product">
			<div class="form-group">
				<form:label for="name" path="name">Name: 
				<form:input type="text" path="name" class="form-control" id="name"/>
				</form:label>
			</div>
			<div class="form-group">
				<form:label for="description" path="description">Description: 
				<form:input type="text" path="description" class="form-control" id="description"/>
				</form:label>
			</div>
			<div class="form-group">
				<form:label for="price" path="price">Price: 
				<form:input type="number" min="0.00" max="10000.00" step="0.01" path="price" class="form-control" id="price"/>
				</form:label>
			</div>
				<input type="submit" value="Add Product" class="btn btn-default">
				
		</form:form>
		<% if(request.getAttribute("errors") != null){ %>
		<fieldset>
		<legend>Errors</legend>
		<c:forEach items="${errors}" var="error">
			<p><c:out value="${error.getDefaultMessage()}"/></p>
		</c:forEach>
		</fieldset>
		<% } %>
		</div>
	</div>
	</body>
</html>