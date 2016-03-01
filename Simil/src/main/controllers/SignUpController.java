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
			Enumeration<String> names = request.getAttributeNames();
			System.out.println("Beginning the insert of new user.");
			database.Account.insertAccount(names.nextElement(), names.nextElement(), names.nextElement(),
					names.nextElement(), (java.sql.Date) new Date(names.nextElement()), names.nextElement());
			System.out.println("Finished inserting user.");
		  }  
}
