package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		  {
			System.out.println("Beginning the insert of new user.");
			System.out.println(request.getParameter("birth"));
			database.Account.insertAccount((String)request.getParameter("userName"), (String)request.getParameter("fName"), 
					(String)request.getParameter("lName"), (String)request.getParameter("email"), 
					request.getParameter("birth"), (String)request.getParameter("pw"));
		  }  
}
