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
	<a href="/ideas">Home</a>
	<h1>Create a new idea</h1>
	
 <p><form:errors path="idea.*" /></p> 	

	<form:form action="/ideas/new" method="post" modelAttribute="idea">
		<table>
			<tr>
				<td>Content: </td>
				<td><form:input placeholder="An Idea" path="content"/> <form:errors path="content"/></td>
			</tr>
			<tr>
				<td></td>
				
				<td><input type="submit" value="Create"/></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>