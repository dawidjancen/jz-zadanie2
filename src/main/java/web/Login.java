package web;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import domain.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
	
		User user = new User(login, password);
		
		loginUser(user, request, response);
	}
	
	private void loginUser(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String url = "jdbc:hsqldb:hsql://localhost/workdb";

		try {
			Connection connection = DriverManager.getConnection(url);
			
			String createUserTableSql = 
				"CREATE TABLE IF NOT EXISTS users("
				+ "id int GENERATED BY DEFAULT AS IDENTITY,"
				+ "login VARCHAR(20),"
				+ "password VARCHAR(20),"
				+ "type VARCHAR(20)"
				+ ")";

			Statement createTable = connection.createStatement();
			createTable.executeUpdate(createUserTableSql);
			
			String getUserSql = 
				"SELECT login, password, type FROM users WHERE login='"
					+ user.getLogin() + "'";
			
			Statement getUser = connection.createStatement();
		    ResultSet rs = getUser.executeQuery(getUserSql);
			
			if (rs.next() && rs.getString("password").equals(user.getPassword())) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				HttpSession session = httpRequest.getSession();
				
				user.setType(rs.getString("type"));
				
				session.setAttribute("loggedIn", true);
				session.setAttribute("login", user.getLogin());
				session.setAttribute("type", user.getType());
				
				response.sendRedirect("/profile");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}