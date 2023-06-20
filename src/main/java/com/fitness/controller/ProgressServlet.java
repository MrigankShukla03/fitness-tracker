package com.fitness.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.model.Nutrition;
import com.fitness.model.User;
import com.fitness.model.Workout;
import com.fitness.util.DBUtil;

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check if the user is logged in
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}
		// Fetch the user's workout and Nutrients history
		List<Workout> workouts = getWorkoutHistory(user);
		List<Nutrition> nutrients = getNutrientHistory(user);

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// Get a database connection from the DBUtil class
			conn = DBUtil.getConnection();
			// Retrieve user details from register table
			String registerQuery = "SELECT username, email FROM register WHERE username = ?";
			stmt = conn.prepareStatement(registerQuery);
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();

			if (rs.next()) {
				// Set user details as attributes
				request.setAttribute("username", rs.getString("username"));
				request.setAttribute("email", rs.getString("email"));
			}
			request.setAttribute("user", user);

			// Retrieve user details from userData table
			String userDataQuery = "SELECT steps, height, weight, bmi, caloriesBurned, distanceWalked, sleepDuration, waterIntake, date FROM userData WHERE username = ?";
			stmt = conn.prepareStatement(userDataQuery);
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();

			if (rs.next()) {
				// Set user fitness details as attributes
				request.setAttribute("steps", rs.getInt("steps"));
				request.setAttribute("height", rs.getDouble("height"));
				request.setAttribute("weight", rs.getDouble("weight"));
				request.setAttribute("bmi", rs.getDouble("bmi"));
				request.setAttribute("caloriesBurned", rs.getDouble("caloriesBurned"));
				request.setAttribute("distanceWalked", rs.getDouble("distanceWalked"));
				request.setAttribute("sleepDuration", rs.getDouble("sleepDuration"));
				request.setAttribute("waterIntake", rs.getDouble("waterIntake"));
				request.setAttribute("date", rs.getString("date"));
			}
			// Set the workout history as an attribute to be displayed in the JSP
			request.setAttribute("workouts", workouts);
			request.setAttribute("nutrients", nutrients);
		} catch (SQLException e) {
			// Handle any database errors
			e.printStackTrace();
			// Redirect to an error page or display an error message
		} finally {
			// Close the resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(stmt);
			DBUtil.closeConnection(conn);
		}
		// Forward the request to the progress.jsp
		request.getRequestDispatcher("progress.jsp").forward(request, response);
	}

	private List<Workout> getWorkoutHistory(User user) {
		WorkoutServlet ws = new WorkoutServlet();
		return ws.getWorkouts(user);
	}

	private List<Nutrition> getNutrientHistory(User user) {
		NutritionServlet ns = new NutritionServlet();
		return ns.getNutritionEntries(user);
	}
}
