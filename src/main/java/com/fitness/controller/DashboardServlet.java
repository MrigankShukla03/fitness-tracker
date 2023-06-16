package com.fitness.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.model.User;
import com.fitness.util.DBUtil;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check if the user is logged in
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}

		// Forward the request to the dashboard.jsp
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stepCounts = request.getParameter("stepCounts");
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		String sleepDuration = request.getParameter("sleepDuration");
		String waterIntake = request.getParameter("waterIntake");

		if (stepCounts.isEmpty() || weight.isEmpty() || height.isEmpty() || sleepDuration.isEmpty() || waterIntake.isEmpty()) {
			request.setAttribute("errorMessage", "Please enter all the details!");
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			return;
		}

		try {
			int steps = Integer.parseInt(stepCounts);
			double weightValue = Double.parseDouble(weight);
			double heightValue = Double.parseDouble(height) / 100.0; // Convert cm to m
			double sleepDurationValue = Double.parseDouble(sleepDuration);
			double waterIntakeValue = Double.parseDouble(waterIntake);

			// Calculate and set BMI
			double bmi = weightValue / (heightValue * heightValue);
			request.setAttribute("bmi", bmi);

			// Calculate and set calories burned and distance walked
			double caloriesBurned = calculateCaloriesBurned(steps);
			double distanceWalked = calculateDistanceWalked(steps);
			request.setAttribute("caloriesBurned", caloriesBurned);
			request.setAttribute("distanceWalked", distanceWalked);

			// Set other attributes
			request.setAttribute("stepCounts", steps);
			request.setAttribute("sleepDuration", sleepDurationValue);
			request.setAttribute("waterIntake", waterIntakeValue);

			// Save the data into the database
			User user = (User) request.getSession().getAttribute("user");
			saveUserData(user.getUsername(), steps, weightValue, heightValue, sleepDurationValue, waterIntakeValue, bmi, caloriesBurned, distanceWalked);

		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid input format!");
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
			return;
		}

		doGet(request, response);
	}

	private double calculateCaloriesBurned(int steps) {
		// Calculate calories burned based on steps count
		// Example: caloriesBurned = steps * 0.05;
		return steps * 0.05;
	}

	private double calculateDistanceWalked(int steps) {
		// Calculate distance walked based on steps count
		// Example: distanceWalked = steps * 0.7 / 1000;
		return steps * 0.7 / 1000;
	}

	private void saveUserData(String username, int steps, double weight, double height, double sleepDuration,
			double waterIntake, double bmi, double caloriesBurned, double distanceWalked) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			// Get a database connection
			connection = DBUtil.getConnection();

			// Prepare the SQL statement
			String sql = "INSERT INTO userData (username, steps, weight, height, sleepDuration, waterIntake, bmi, caloriesBurned, distanceWalked) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			// Set the parameters
			statement.setString(1, username);
			statement.setInt(2, steps);
			statement.setDouble(3, weight);
			statement.setDouble(4, height);
			statement.setDouble(5, sleepDuration);
			statement.setDouble(6, waterIntake);
			statement.setDouble(7, bmi);
			statement.setDouble(8, caloriesBurned);
			statement.setDouble(9, distanceWalked);

			// Execute the statement
			statement.executeUpdate();

		} catch (SQLException e) {
			// Handle any database errors
			e.printStackTrace();
		} finally {
			// Close the statement and connection
			DBUtil.closeStatement(statement);
			DBUtil.closeConnection(connection);
		}
	}

}
