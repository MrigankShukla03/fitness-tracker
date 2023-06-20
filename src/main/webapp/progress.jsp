<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fitness.model.User"%>
<%@ page import="com.fitness.model.Workout" %>
<%@ page import="com.fitness.model.Nutrition" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Progress</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <header>
            <h1>User Progress</h1>
        </header>

        <nav>
            <ul>
                <li><a href="dashboard">Dashboard</a></li>
                <li><a href="profile">Profile</a></li>
            </ul>
        </nav>

        <main class="main">
            <section>
                <h2>User Details</h2>
                <p><strong>Username:</strong> ${username}</p>
				<p><strong>Email:</strong> ${email}</p>
            </section>

            <section>
                <h2>Progress</h2>
                <p><strong>Date:</strong> ${date}</p>
                <p><strong>Steps Count:</strong> ${steps}</p>
				<p><strong>Calories Burned:</strong> ${caloriesBurned}</p>
				<p><strong>Distance Walked:</strong> ${distanceWalked} Kms</p>
				<p><strong>Height:</strong> ${height} m/cm</p>
				<p><strong>Weight:</strong> ${weight} Kg</p>
				<p><strong>BMI:</strong> ${bmi}</p>
				<p><strong>Sleep Hours:</strong> ${sleepDuration} Hrs</p>
				<p><strong>Water Intake:</strong> ${waterIntake} Ltrs</p>
            </section>

            <section>
                <h2>Workout History</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Duration</th>
                            <th>Calories Burned</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
            			<% for (com.fitness.model.Workout workout : (List<com.fitness.model.Workout>) request.getAttribute("workouts")) { %>
		                <tr>
		                    <td><%= workout.getWorkoutName() %> </td>
		                    <td><%= workout.getDuration() %> </td>
		                    <td><%= workout.getCaloriesBurned() %> </td>
		                    <td><%= workout.getDate() %> </td>
		                </tr>
		            	<% } %>
                    </tbody>
                </table>
            </section>
            <section>
                <h2>Nutrients History</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Food Name</th>
                            <th>Calories Intake</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
            			<% for (com.fitness.model.Nutrition nutritionEntry : (List<com.fitness.model.Nutrition>) request.getAttribute("nutrients")) { %>
		                <tr>
		                    <td><%= nutritionEntry.getFoodName() %></td>
		                    <td><%= nutritionEntry.getCalories() %></td>
		                    <td><%= nutritionEntry.getDate() %></td>
		                </tr>
		            	<% } %>
                    </tbody>
                </table>
            </section>
        </main>
    </div>
</body>
</html>