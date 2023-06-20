<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.fitness.model.Workout"%>
<!-- dashboard.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/dashboard.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
	crossorigin="anonymous"></script>
</head>
<body>
 	<header>
        <h1>Fitness Dashboard</h1>
        <h2 class="header-user">
			Welcome,
			<%= ((com.fitness.model.User) request.getSession().getAttribute("user")).getUsername() %>!
		</h2>
    </header>
    <nav>
        <ul>
            <li><a href="/fitness-tracker">Home</a></li>
            <li><a href="workout">Workouts</a></li>
            <li><a href="nutrition">Nutrition</a></li>
            <li><a href="progress">Progress</a></li>
            <li><a href="profile">Profile</a></li>
            <li><a href="/fitness-tracker">Logout</a></li>
        </ul>
    </nav>
    <main class="dashboard">
    <div class="formBoard">
    <form action="dashboard" method="post">
    	<div class="date">
			<% if (request.getAttribute("errorMessage") != null) { %>
	          <p class="error"><%= request.getAttribute("errorMessage") %></p>
	        <% } %>
	       <input type="date" name="date" placeholder="Enter Date">
	       <p>Date: ${date} </p>
        </div>
        <div class="card" id="stepCount">
            <div class="card-header">
                <h2>Step Counts</h2>
            </div>
            <div class="card-body">
                <input type="number" name="stepCounts" placeholder="Enter today's steps">
                <br>
                <p>Today's Steps: ${stepCounts}</p>
                <p>Calories Burned: ${caloriesBurned}</p>
                <p>Distance Walked: ${distanceWalked} Km</p>
                
            </div>
        </div>
        <div class="card" id="bmi">
            <div class="card-header">
                <h2>BMI</h2>
            </div>
            <div class="card-body">
                <input type="number" name="weight" placeholder="Enter your weight (Kg)">
                 <br>
                <input type="number" name="height" placeholder="Enter your height (Cm)">
                <p>Your BMI: ${bmi}</p>
            </div>
        </div>
        <div class="card" id="sleepCycle">
            <div class="card-header">
                <h2>Sleep Cycle</h2>
            </div>
            <div class="card-body">
                <input type="number" name="sleepDuration" placeholder="Enter last night's sleep duration (Hours)">
                <p>Last Night's Sleep: ${sleepDuration} Hours</p>
            </div>
        </div>
        <div class="card" id="waterIntake">
            <div class="card-header">
                <h2>Water Intake</h2>
            </div>
            <div class="card-body">
                <input type="number" name="waterIntake" placeholder="Enter today's water intake (Liters)">
                <p>Today's Water Intake: ${waterIntake} Liters</p>
            </div>
        </div>
       	<div class="submit-button">
            <button type="submit">Submit</button>
        </div>
    </form>
    </div>
    </main>
</body>
</html>
