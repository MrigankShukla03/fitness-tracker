<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- register.jsp -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
	<script src="./js/script.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="wrapper">
        <div class="form-wrapper sign-up">
            <form action="register" method="post">
                <h2>Register</h2>
                <%-- Add this code where you want to display the error message --%>
				<% if (request.getAttribute("errorMessage") != null) { %>
				    <p class="error"><%= request.getAttribute("errorMessage") %></p>
				<% } %>
                <div class="input-group">
                    <input type="text" name="username" id="username" required>
                    <label for="username">Username</label>
                </div>
                <div class="input-group">
                    <input type="email" name="email" id="email" required>
                    <label for="email">Email</label>
                </div>
                <div class="input-group">
                    <input type="password" name="password" id="password" required>
                    <label for="password">Password</label>
                </div>
                <button type="submit" class="btn">Register</button>
                <div class="sign-link">
                    <p>Already have an account? <a href="login" class="signIn-link">Login</a></p>
                </div>
                
            </form>
        </div>
    </div>
</body>
</html>