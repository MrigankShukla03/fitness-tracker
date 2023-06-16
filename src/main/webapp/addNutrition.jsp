<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
	<%@ page import="com.fitness.model.Nutrition" %>
<!-- addNutrition.jsp -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>Add Nutrition</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <h2>Add Nutrition</h2>
        <nav>
            <ul>
                <li><a href="dashboard">Dashboard</a></li>
                <!-- Add other navigation links as needed -->
            </ul>
        </nav>
        <form action="nutrition" method="post">
            <label for="foodName">Food Name:</label>
            <input type="text" name="foodName" id="foodName" required>

            <label for="calories">Calories:</label>
            <input type="number" name="calories" id="calories" required>

            <!-- Add other input fields for nutrition details as needed -->

            <input type="submit" value="Add Nutrition">
        </form>

        <h3>Nutrition Entries</h3>
        <table>
            <tr>
                <th>Food Name</th>
                <th>Calories</th>
                <!-- Add other table headers for nutrition details as needed -->
            </tr>
            <% for (com.fitness.model.Nutrition nutritionEntry : (List<com.fitness.model.Nutrition>) request.getAttribute("nutritionEntries")) { %>
                <tr>
                    <td><%= nutritionEntry.getFoodName() %></td>
                    <td><%= nutritionEntry.getCalories() %></td>
                    <!-- Add other table cells for nutrition details as needed -->
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
