package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

@WebServlet("/InterestServlet")
public class InterestServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		  {
			System.out.println("Beginning the retrieval of interests.");
			ArrayList<String> interests = database.Interest.getAllInterests();
			
			JSONArray ar = new JSONArray(interests);
			String json = ar.toString();
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			//System.out.println(json);
			//System.out.println(json.toString());
			System.out.println("Finished getting interests.");
		  }  
}
