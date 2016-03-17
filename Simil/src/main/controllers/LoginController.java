package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.PHasher;

@SuppressWarnings("serial")
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String givenPass = request.getParameter("password");

			// Compare the given pw and pw from db using PHasher methods
			PHasher hash = new PHasher(givenPass);
			String storedPass = database.Account.getPass(username);
			// String givenPass = hash.encrypt(hash.getSalt());
			
			if (storedPass.equals(givenPass) /*hash.match(storedPass, givenPass)*/) {
				String accountType = database.Account.getAccountType(username);
				// if((storedPass).equals(givenPass)){
				// Get the session - if no session exists create one
				HttpSession session = request.getSession(true);
				// Set some attribute values to the session
				session.setAttribute("username", username);
				session.setAttribute("accountType", accountType);
				// session.setAttribute("password", password);
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