package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

@SuppressWarnings("serial")
@WebServlet("/UserHomeServlet")
public class UserHomeController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("Beginning the retrieval of user home info.");
		ArrayList<String> accountInfo = database.Account
				.getAccountDetails((String) request.getSession().getAttribute("username"));

		// The first entry in accountInfo is a string of the user's interests,
		// underscore delimited
		// The rest of the entries are the user's panels
		// Parse the interests and put them into a jsonarray, then send back a
		// 2d jsonarray
		//System.out.println(accountInfo);
		JSONArray interests;
		String[] interestsArray = {};
		JSONArray panels;
		//System.out.println("SIZE" + accountInfo.size());
		//System.out.println("AC INF: " + accountInfo);
		if (accountInfo.size() <= 0){
			interests = new JSONArray();
			panels = new JSONArray();
		}
		else{
			interestsArray = accountInfo.get(0).split("_");	
			interests = new JSONArray(interestsArray);
			panels = new JSONArray(accountInfo.subList(1, accountInfo.size()));
		}
		

		// Turn the 2d array into a JSONArray of JSONArrays

		JSONArray ar = new JSONArray();
		ar.put(interests);
		
		ar.put(panels);

		String json = ar.toString();
		// System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		//System.out.println("Finished getting account info.");
	}
}
