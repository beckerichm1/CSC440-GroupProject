package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
		ArrayList<String> interests = database.Interest.getAllInterests();

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
		ArrayList<String> interests = new ArrayList<>();

		database.Interest.addInterests((String) request.getSession().getAttribute("username"), array);
		System.out.println("Finished adding interests.");
	}
}
