package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Profile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		String login = (String)session.getAttribute("login");
		String type = (String)session.getAttribute("type");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = response.getWriter();

		writer.println("<p>Login: " + login + "</p>");
		writer.println("<p>Typ konta: " + type + "</p>");

		if (type.equals("premium")) {
			writer.println("<p><a href='/premium'>Strona Premium</a></p>");
		}
		
		if (type.equals("admin")) {
			writer.println("<p><a href='/premium'>Strona Premium</a></p>");
			writer.println("<p><a href='/admin'>Zarz¹dzanie kontami</a></p>");
		}
	}
}
