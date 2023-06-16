<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
	<%@ page import="com.fitness.model.Workout" %>
<!-- addWorkout.jsp -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Add Workout</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <h2>Add Workout</h2>
         <nav>
            <ul>
                <li><a href="dashboard">Dashboard</a></li>
                <!-- Add other navigation links as needed -->
            </ul>
        </nav>
        <form action="workout" method="post">
        	<% if (request.getAttribute("errorMessage") != null) { %>
		            <p class="error"><%= request.getAttribute("errorMessage") %></p>
	        <% } %>
            <label for="name">Workout Name:</label>
            <input type="text" name="name" id="name" required>

            <label for="duration">Duration (minutes):</label>
            <input type="number" name="duration" id="duration" required>

            <label for="caloriesBurned">Calories Burned:</label>
            <input type="number" name="caloriesBurned" id="caloriesBurned" required>

            <input type="submit" value="Add Workout">
        </form>

        <h3>Workouts</h3>
        <table>
            <tr>
                <th>Workout Name </th>
                <th>Duration (minutes) </th>
                <th>Calories Burned </th>
            </tr>
            <% for (com.fitness.model.Workout workout : (List<com.fitness.model.Workout>) request.getAttribute("workouts")) { %>
                <tr>
                    <td><%= workout.getWorkoutName() %> </td>
                    <td><%= workout.getDuration() %> </td>
                    <td><%= workout.getCaloriesBurned() %> </td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
