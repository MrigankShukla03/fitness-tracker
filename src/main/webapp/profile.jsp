<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmOgxCvD3qSKzFKo4ghIgY1Jho06Mch5qqfEVX"
        crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="text-center">Profile</h1>
        <nav>
            <ul>
                <li><a href="dashboard">Dashboard</a></li>
                <!-- Add other navigation links as needed -->
            </ul>
        </nav>
        <table class="table">
            <tbody>
                <tr>
                    <th>Username:</th>
                    <td>${username}</td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td>${email}</td>
                </tr>
                <tr>
                    <th>Steps:</th>
                    <td>${steps}</td>
                </tr>
                <tr>
                    <th>Height:</th>
                    <td>${height} m/cm</td>
                </tr>
                <tr>
                    <th>Weight:</th>
                    <td>${weight} Kg</td>
                </tr>
                <tr>
                    <th>BMI:</th>
                    <td>${bmi}</td>
                </tr>
                <tr>
                    <th>Calories Burned:</th>
                    <td>${caloriesBurned}</td>
                </tr>
                <tr>
                    <th>Distance Walked:</th>
                    <td>${distanceWalked} Km</td>
                </tr>
                <tr>
                    <th>Sleep Duration:</th>
                    <td>${sleepDuration} Hrs</td>
                </tr>
                <tr>
                    <th>Water Intake:</th>
                    <td>${waterIntake} Ltrs</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
