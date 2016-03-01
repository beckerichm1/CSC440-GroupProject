package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/SignUpController")
public class SignUpController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		  {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	        Date parsed;
			try {
				parsed = format.parse(request.getParameter("birth"));
		        java.sql.Date birth = new java.sql.Date(parsed.getTime());
			
			System.out.println("Beginning the insert of new user.");
			database.Account.insertAccount((String)request.getParameter("userName"), (String)request.getParameter("fName"), 
					(String)request.getParameter("lName"), (String)request.getParameter("email"), 
					birth, (String)request.getParameter("pw"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }  
}
