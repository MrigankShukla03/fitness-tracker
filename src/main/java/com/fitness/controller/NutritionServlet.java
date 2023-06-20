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
import com.fitness.util.DBUtil;

@WebServlet("/nutrition")
public class NutritionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if the user is logged in
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}

		// Fetch existing nutrition entries for the user
		List<Nutrition> nutritionEntries = getNutritionEntries(user);

		// Set the nutrition entries as an attribute to be displayed in the JSP
		request.setAttribute("nutritionEntries", nutritionEntries);

		// Forward the request to the addNutrition.jsp
		request.getRequestDispatcher("addNutrition.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if the user is logged in
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("login");
			return;
		}

		// Retrieve nutrition details from the form
		String foodName = request.getParameter("foodName");
		int calories = Integer.parseInt(request.getParameter("calories"));
		String date = request.getParameter("date");

		// Create a new Nutrition object
		Nutrition nutritionEntry = new Nutrition(foodName, calories, date);

		// Add the new nutrition entry to the list of existing nutrition entries for the user
		addNutritionEntry(user, nutritionEntry);

		// Redirect to the doGet method to display the updated nutrition entries
		doGet(request, response);
	}

	public List<Nutrition> getNutritionEntries(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<Nutrition> nutritionEntries = new ArrayList<>();

		try {
			// Get a database connection from the DBUtil class
			conn = DBUtil.getConnection();

			// Retrieve nutrition entries for the user from the database
			String query = "SELECT foodName, caloriesIntake, date FROM nutrition WHERE username = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();

			while (rs.next()) {
				String foodName = rs.getString("foodName");
				int calories = rs.getInt("caloriesIntake");
				String date = rs.getString("date");

				// Create a Nutrition object and add it to the list
				Nutrition nutritionEntry = new Nutrition(foodName, calories, date);
				nutritionEntries.add(nutritionEntry);
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
		return nutritionEntries;
	}

	private void addNutritionEntry(User user, Nutrition nutritionEntry) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			// Get a database connection from the DBUtil class
			conn = DBUtil.getConnection();

			// Insert the new nutrition entry into the database
			String query = "INSERT INTO nutrition (username, foodName, caloriesIntake, date) VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, nutritionEntry.getFoodName());
			stmt.setInt(3, nutritionEntry.getCalories());
			stmt.setString(4, nutritionEntry.getDate());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// Handle any database errors
			e.printStackTrace();
		} finally {
			// Close the resources
			DBUtil.closeStatement(stmt);
			DBUtil.closeConnection(conn);
		}
	}
}
