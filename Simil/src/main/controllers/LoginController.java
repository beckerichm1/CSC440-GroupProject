package controllers;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet { 
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
		  {

		    String username = request.getParameter("username");
		    String password = request.getParameter("password");
		    
		    // Get the session - if no session exists create one
		    HttpSession session = request.getSession(true);
		    // Set some attribute values to the session
		    // In this case user and password from the request and client
		    session.setAttribute("username", username);
		    //session.setAttribute("password", password);

		}
}