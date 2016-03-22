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
@WebServlet("/PanelServlet")
public class PanelController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Beginning the retrieval of panels.");
		ArrayList<String[]> allPanels = database.Panel.getAllPanels();
		ArrayList<String> tempUserPanels = database.Panel.getUserPanels(
				(String) request.getSession().getAttribute("username"));
		
		ArrayList<String[]> panels = new ArrayList<>();
		for(int i = 0; i < allPanels.size(); i++){
			if(!tempUserPanels.contains(allPanels.get(i)[0]))
				panels.add(allPanels.get(i));
		}
		// Turn the 2d array into a JSONArray of JSONArrays
		JSONArray ar = new JSONArray();
		for (int i = 0; i < panels.size(); i++) {
			JSONArray a = new JSONArray(panels.get(i));
			ar.put(a);
		}
		String json = ar.toString();
		System.out.println(json);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		System.out.println("Finished getting panels.");
	}
}
