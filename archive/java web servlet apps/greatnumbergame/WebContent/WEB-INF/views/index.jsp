<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	    <style>
        body {
            text-align: center;

        }
        #box {
            height: 300px;
            width: 300px;
            display: inline-block;
            background-color: red;
            color: white;
            text-align: center
            vertical-align: middle;
            line-height: 300px;
            font-size: 30px;
            <% boolean greenbox = (boolean) session.getAttribute("greenbox"); %>
            <% if (greenbox == true) { %>
                background-color: green;
            <% } %>
        }
    </style>
</head>
<body>
	<h1>Welcome to the Great Number Game!</h1>
    <p>I am thinking of a number between 1 and 100</p>
    <p>Take a guess!</p>

    <% if ((String) session.getAttribute("output") != null) { %>
        <div id='box'> <%= (String) session.getAttribute("output") %>
			<% if (greenbox == true) { %>
           		<form action='/greatnumbergame' method='POST'>
        			<input type='submit' value='Play Again!'>
    			</form>    
            <% } %>
        </div> <br> <br> <br>
    	
    <% } %>
    <form action='/greatnumbergame/2' method='POST'>
        <input type='text' name='number'> <br> <br>
        <input type='submit' value='Submit'>
    </form>
</body>
</html>