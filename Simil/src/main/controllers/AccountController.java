package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/AccountServlet")
public class AccountController extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = request.getParameter("param");
		String id = request.getParameter("id");
		if(param.equals("info")){
			System.out.println("Getting bio and interests for user " + id);
			String bio = database.Account.getAbout(id);
			ArrayList<String> info = database.Account.getAccountDetails(id);
			ArrayList<String> allInfo = new ArrayList<>();
			allInfo.add(bio);
			allInfo.addAll(info);
			
			JSONArray ar = new JSONArray(allInfo);
			String json = ar.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		else if(param.equals("buddies")){
			System.out.println("Getting buddies for user " + id);
			ArrayList<String> buddies = database.Friend.getAllFriends(id);
			JSONArray ar = new JSONArray(buddies);
			String json = ar.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		else{
			System.out.println("Getting users like '" + id + "'");
			ArrayList<String> users = database.Account.getAccountsLike(id);
			JSONArray ar = new JSONArray(users);
			String json = ar.toString();
			request.setAttribute("searchFor", id);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}
}
