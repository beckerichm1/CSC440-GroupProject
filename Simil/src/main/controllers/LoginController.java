package controllers;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/LoginController")
public class LoginController extends HttpServlet { 
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response){
	        try {
			    String username = request.getParameter("username");
			    String password = request.getParameter("password");
			    String temp = database.Account.getPass(username);
			    if((temp).equals(password)){
			    	//get AccountType
			    	String accountType = database.Account.getAccountType(username);
				    // Get the session - if no session exists create one
				    HttpSession session = request.getSession(true);
				    // Set some attribute values to the session
				    // In this case user and account type from the request and client
				    session.setAttribute("username", username);
				    session.setAttribute("accountType", accountType);
				    //session.setAttribute("password", password);
				    response.setHeader("Access-Control-Allow-Origin", "*");
				    response.setContentType("text/html;charset=UTF-8");
			        response.getWriter().write("Success");
					response.sendRedirect("/Simil/views/Home/UserHome.jsp");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		    

		}
}