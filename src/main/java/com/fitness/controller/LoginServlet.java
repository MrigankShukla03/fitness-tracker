package com.fitness.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.model.User;
import com.fitness.util.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (username != null) { // Perform a null check on the username parameter
			// Validate username and password

			// Retrieve the user from the database based on the username
			User user = getUserFromDatabase(username);

			if (user != null && user.getPassword().equals(password)) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("dashboard");
			} else {
				request.setAttribute("errorMessage", "Invalid Username or Password!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else {
			// Handle the case when the username parameter is null
			request.setAttribute("errorMessage", "Username is required");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private User getUserFromDatabase(String username) {
		Connection conn = null;
		PreparedStatement statement = null;
		try  {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM register WHERE username = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String password = resultSet.getString("password");
				return new User(username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close the statement and connection
			DBUtil.closeStatement(statement);
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward the request to the registration page
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
}
