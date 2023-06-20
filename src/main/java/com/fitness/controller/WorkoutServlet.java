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

import com.fitness.model.User;
import com.fitness.model.Workout;
import com.fitness.util.DBUtil;

@WebServlet("/workout")
public class WorkoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if the user is logged in
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}

		// Fetch existing workouts for the user
		List<Workout> workouts = getWorkouts(user);

		// Set the workouts as an attribute to be displayed in the JSP
		request.setAttribute("workouts", workouts);

		// Forward the request to the addWorkout.jsp
		request.getRequestDispatcher("addWorkout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if the user is logged in
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}

		// Retrieve workout details from the form
		String workoutName = request.getParameter("name");
		String duration = request.getParameter("duration");
		String caloriesBurned = request.getParameter("caloriesBurned");
		String date = request.getParameter("date");

		// Validate workout details
		if (workoutName.isEmpty() || duration.isEmpty() || caloriesBurned.isEmpty()) {
			request.setAttribute("errorMessage", "Please enter all the details!");
			doGet(request, response);
			return;
		}

		try {
			// Create a new Workout object
			Workout workout = new Workout(workoutName, duration, caloriesBurned, date);

			// Add the new workout to the list of existing workouts for the user
			addWorkout(user, workout);

			// Redirect to the doGet method to display the updated workouts
			doGet(request, response);
		} catch (SQLException e) {
			// Handle any database errors
			e.printStackTrace();
			// Redirect to an error page or display an error message
		}
	}

	public List<Workout> getWorkouts(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Workout> workouts = new ArrayList<>();

		try {
			// Get a database connection from the DBUtil class
			conn = DBUtil.getConnection();

			// Retrieve workouts for the user from the database
			String query = "SELECT workoutName, duration, caloriesBurned, date FROM workouts WHERE username = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString("workoutName");
				String duration = rs.getString("duration");
				String caloriesBurned = rs.getString("caloriesBurned");
				String date = rs.getString("date");

				// Create a Workout object and add it to the list
				Workout workout = new Workout(name, duration, caloriesBurned, date);
				workouts.add(workout);
			}
		} catch (SQLException e) {
			// Handle any database errors
			e.printStackTrace();
		} finally {
			// Close the resources
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(stmt);
			DBUtil.closeConnection(conn);
		}
		return workouts;
	}

	private void addWorkout(User user, Workout workout) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// Get a database connection from the DBUtil class
			conn = DBUtil.getConnection();

			// Insert the new workout into the database
			String query = "INSERT INTO workouts (username, workoutName, duration, caloriesBurned, date) VALUES (?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, workout.getWorkoutName());
			stmt.setString(3, workout.getDuration());
			stmt.setString(4, workout.getCaloriesBurned());
			stmt.setString(5, workout.getDate());
			stmt.executeUpdate();
		} finally {
			// Close the resources
			DBUtil.closeStatement(stmt);
			DBUtil.closeConnection(conn);
		}
	}
}
