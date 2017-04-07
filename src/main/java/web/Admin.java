package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;

@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();
		
		String url = "jdbc:hsqldb:hsql://localhost/workdb";

		try {
			Connection connection = DriverManager.getConnection(url);
			
			String getUsersSql = "SELECT login, type FROM users";

			Statement getUsers = connection.createStatement();
		    ResultSet rs = getUsers.executeQuery(getUsersSql);

		    writer.println("<p>Lista u¿ytkowników:</p>");
		    
			while (rs.next()) {
				String login = rs.getString("login");
				String type = rs.getString("type");
				
				writer.println("<p>Login: " + login + ", ");
				writer.println("Typ konta: " + type + "</p>");
			}
			
			writer.println("<hr />");
			
			writer.println("<form action='admin' method='post'>");
			writer.println("<input type='text' id='login' name='login' />");
	        writer.println("<label>Nadaj status premium:</label><input type='radio' name='action' value='give' />");
	        writer.println("<label>Odbierz status premium:</label><input type='radio' name='action' value='take' />");
	        writer.println("<input type='submit' id='submit' value='Wyœlij' />");
			writer.println("</form>");
			
			writer.println("<a href='/profile'></a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String action = request.getParameter("action");
		String type = "normal";
        
		if (action.equals("give")) {
			type = "premium";
		}
	
		User user = new User(login, null, type);
		
		updateUser(user, response);
	}
	
	private void updateUser(User user, HttpServletResponse response) throws IOException {
		String url = "jdbc:hsqldb:hsql://localhost/workdb";

		try {
			Connection connection = DriverManager.getConnection(url);
			
			String updateUserSql = 
				"UPDATE users SET type='"
				+ user.getType() + "' WHERE login='"
				+ user.getLogin() + "'";

			Statement updateUser = connection.createStatement();
			int i = updateUser.executeUpdate(updateUserSql);
			
			if (i > 0) {
				response.sendRedirect("/admin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

