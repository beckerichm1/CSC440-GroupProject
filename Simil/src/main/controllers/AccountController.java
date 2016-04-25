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
public class AccountController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String param = request.getParameter("param");
		String id = request.getParameter("id");
		if (param.equals("info")) {
			System.out.println("Getting bio and interests for user " + id);
			String bio = database.Account.getAbout(id);
			
			ArrayList<ArrayList<String>> infoPlusId= database.Account.getAccountDetails(id);
			ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
			// since Account.getAccountDetails returns ArrayList<ArrayList<String>>,
			// where ArrayList.get(0) is interests, ArrayList.get(1) is panels,
			// and ArrayList.get(2) is panelIDs.
			ArrayList<Object> allInfo = new ArrayList<Object>();
			allInfo.add(bio);
			allInfo.add(infoPlusId.get(0));
			allInfo.add(infoPlusId.get(1));
			allInfo.add(infoPlusId.get(2));

			JSONArray ar = new JSONArray(allInfo);
			String json = ar.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else if (param.equals("buddies")) {
			System.out.println("Getting buddies for user " + id);
			ArrayList<String> buddies = database.Friend.getAllFriends(id);
			JSONArray ar = new JSONArray(buddies);
			String json = ar.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else {
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
