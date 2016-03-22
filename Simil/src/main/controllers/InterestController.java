package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

@SuppressWarnings("serial")
@WebServlet("/InterestController")
public class InterestController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Beginning the retrieval of interests.");
		
		// Get all interests available
		ArrayList<String> allInterests = database.Interest.getAllInterests();
		
		
		// Get interests the user already has
		String tempUserInterests = database.Interest.getUserInterests(
				(String) request.getSession().getAttribute("username"));
		// Parse the user interests into an arraylist
		List<String> userInterests = new ArrayList<String>(Arrays.asList(tempUserInterests.split("_")));
		ArrayList<String> interests = new ArrayList<>();
		// Get an arraylist of all interests the user doesn't yet have.
		for(int i = 0; i < allInterests.size(); i++){
			if(!userInterests.contains(allInterests.get(i)))
				interests.add(allInterests.get(i));
		}
		
		// Store these interests in a JSONArray object to be returned to the view.
		JSONArray ar = new JSONArray(interests);
		String json = ar.toString();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		System.out.println("Finished getting interests.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Beginning to add interests.");
		String[] array = request.getParameterValues("interests[]");
		database.Interest.addInterests((String) request.getSession().getAttribute("username"), array);
		System.out.println("Finished adding interests.");
	}
}
