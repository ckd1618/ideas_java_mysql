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
	<a href="/logout">Logout</a>
	<h1>Welcome, <c:out value="${user.name}" /></h1>
	<h2>Ideas</h2>

	<table>
	
			<tr>
				<th>Idea</th>
				<th>Created By</th>
				<th>Likes</th>
				<th>Action</th>
			</tr>

	<!-- <%= request.getAttribute("full") %>
		<%= session.getAttribute("full") %>  -->	
		
		<c:forEach items="${ideas}" var="idea">
			<tr>
				<td><a href="/ideas/${idea.id}" ><c:out value="${idea.content}" /></a></td>
				<td><c:out value="${idea.user.name}" /></td>
				<td><c:out value="${idea.likecount}" /></td>
				<td><a href="/ideas/likecount/${idea.id}">Like</a></td>
			</tr>
		</c:forEach>
	
	</table>

	<br><br>
	<a href="/ideas/new">Create an Idea</a>


</body>
</html>