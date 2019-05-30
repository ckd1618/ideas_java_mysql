<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ideas</title>
</head>
<body>
	<a href="/logout">Logout</a> <a href="/ideas">Home</a>
	
	<h1>Edit <c:out value="${idea.content}" /></h1>
	
 <p><form:errors path="idea.*" /></p> 

	<form:form action="/ideas/${idea.id}/edit" method="post" modelAttribute="idea">
	<input type="hidden" name="_method" value="put">
		<table>
			<tr>
				<td>Content: </td>
				<td><form:input value="${idea.content}" path="content"/><form:errors path="content"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update"/></td>
			</tr>
			<tr>
				<td></td>
				<td><a href="/delete/${idea.id}">Delete</a></td>
			</tr>
		</table>
		
			
			
		<c:forEach items="${errors}" var="error">
			<c:out value="${error.getDefaultMessage()}" />
		</c:forEach>
			
			
	</form:form>
	
	
	
	
</body>
</html>