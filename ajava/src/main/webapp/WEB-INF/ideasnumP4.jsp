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
	<h1><c:out value="${idea.content}" /></h1>

	<h3>Created By: <c:out value="${idea.user.name}" /></h3>
	
	<h1>Users who liked your idea:</h1>
	<table>
		<tr>
			<th>Name</th>
		</tr>

			<tr>
				<td><c:out value="${idea.user.name}" /></td>
			</tr>

	</table>
	<br><br>
	<a href="/ideas/${idea.id}/edit">Edit</a>
</body>
</html>