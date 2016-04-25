package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import utility.PHasher;

@SuppressWarnings("serial")
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			if(request.getParameter("param").equals("recover")){
				System.out.println("Recovering");
				String username = request.getParameter("username");
				System.out.println("USERNAME:" + username);
				String email = request.getParameter("email");
				//TODO: Check if username and email exists
				// make new random password
				PHasher hash = new PHasher();
				String newPass = hash.generateRandomPassword();
				String password = hash.hash(newPass);
				// insert the new password
				database.Account.updatePass(username, password);
				// invoke the emailer
				// return the new pass in json for now (since emailer isn't up)
				JSONObject j = new JSONObject("{'password':" + newPass + "}");
				String json = j.toString();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
			else{
				String username = request.getParameter("username");
				String givenPass = request.getParameter("password");
				// Compare the given pw and pw from db using PHasher methods
				PHasher hash = new PHasher(givenPass);
				String storedPass = database.Account.getPass(username);
				if (hash.match(storedPass, givenPass)) {
					String accountType = database.Account.getAccountType(username);
					ArrayList<String> panels = database.Account.getPanels(username);
					// if((storedPass).equals(givenPass)){
					// Get the session - if no session exists create one
					HttpSession session = request.getSession(true);
					// Set some attribute values to the session
					session.setAttribute("username", username);
					session.setAttribute("accountType", accountType);
					session.setAttribute("panels", panels);
					//session.setAttribute("panels", panels);
					// session.setAttribute("password", password);
					//response.setHeader("Access-Control-Allow-Origin", "*");
					//response.setContentType("json");
					//response.setStatus(response.SC_ACCEPTED);
					//response.sendRedirect("/Simil/views/Home/UserHome.jsp");

					System.out.println("I AM DONE HERE");
				}
				else{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
					response.setStatus(400);
					response.setContentType("text/plain");
					response.getWriter().print("NOPE");
					//response.sendRedirect("/Simil");
					//Need to send invalid password message with it

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}