package com.fitness.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitness.model.User;
import com.fitness.util.DBUtil;

@WebServlet(urlPatterns = "/register", loadOnStartup = 5)
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		// Validate username, password, and email
		if (!isValid(username, password, email)) {
			request.setAttribute("errorMessage", "Invalid username, password, or email");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}

		User user = new User(username, password, email);

		// Save the user details to the database
		boolean registrationSuccess = saveUserToDatabase(user);

		if (registrationSuccess) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("dashboard");
		} else {
			request.setAttribute("errorMessage", "User already Registered!");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward the request to the registration page
		request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

	private boolean isValid(String username, String password, String email) {
		// Username validation: alphanumeric with special symbols (_ and .) only
		String usernameRegex = "^[a-zA-Z0-9_.]+$";
		if (!username.matches(usernameRegex)) {
			return false;
		}

		// Email validation: should contain @ symbol
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		if (!email.matches(emailRegex)) {
			return false;
		}

		// Password validation: alphanumeric with special symbols (#, _, @ and $) only
		String passwordRegex = "^[a-zA-Z0-9#_@$]+$";
		if (!password.matches(passwordRegex)) {
			return false;
		}
		return true;
	}

	private boolean saveUserToDatabase(User user) {
		Connection conn = null;
		PreparedStatement statement= null;
		try {
			conn = DBUtil.getConnection();
			String query = "INSERT INTO register (username, password, email) VALUES (?, ?, ?)";
			statement = conn.prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLIntegrityConstraintViolationException e) {
			// Handle the specific exception for duplicate entry violation
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// Close the statement and connection
			DBUtil.closeStatement(statement);
			DBUtil.closeConnection(conn);
		}
	}
}
