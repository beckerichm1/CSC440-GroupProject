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
		// Get the param for all panels
		String param = request.getParameter("param");
		ArrayList<String[]> panels = new ArrayList<>();
		ArrayList<String[]> ids = new ArrayList<>();
		System.out.println("Getting panel details");
		if(param.equals("one")){
			String id = request.getParameter("id");
			panels = database.Panel.getPanelDetail(id);
			// put the panel into a json object and send it through
			JSONArray ar = new JSONArray(panels.get(0));
			String json = ar.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			System.out.println("Returning from getting Panel Details");
			return;
		}
		else if(param.equals("search")){
			panels = database.Panel.getPanelsLike(request.getParameter("id"));
		}
		else if(param.equals("mods")){
			String id = request.getParameter("id");
			String moderators = database.Panel.getModerators(id);
			JSONArray ar = new JSONArray(new String[]{moderators});
			String json = ar.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
			System.out.println("Returning from getting Panel moderators " + json);
			return;
		}
		else if(!param.equals("all"))
			panels = database.Panel.getNonUserPanels((String) request.getSession().getAttribute("username"));
		else
			panels = database.Panel.getAllPanels();
		// Turn the 2d array into a JSONArray of JSONArrays
		JSONArray ar = new JSONArray();
		for (int i = 0; i < panels.size(); i++) {
			JSONArray a = new JSONArray(panels.get(i));
			ar.put(a);
		}
		String json = ar.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postType = request.getParameter("postType");
		
		if(postType.equals("add")){
			String param = request.getParameter("name");
			System.out.println("Beginning to insert new panel.");
		
			String[] array;
			if(param == null || param == ""){
				array = request.getParameterValues("panels[]");
				database.Panel_Account.insertPanelAccounts(
					(String) request.getSession().getAttribute("username"), array);
			}
			else{
				String name = request.getParameter("name");
				String desc = request.getParameter("description");
				String rel = request.getParameter("related");
				String mods = request.getParameter("moderators");
				String creator = request.getParameter((String) request.getSession().getAttribute("username"));
				database.Panel.insertPanel(name, desc, rel, mods, creator);
			}
		}
		else if(postType.equals("addMod")){
			System.out.println("setting moderators");
			String id = request.getParameter("id");
			String mods = request.getParameter("moderators");
			System.out.println("setting moderators with id " + id + "and mods " + mods);
			database.Panel.addMod(id, mods);
		}
		else if(postType.equals("delete")){
			String id = request.getParameter("id");
			System.out.println("Beginning to insert new panel.");
			database.Panel.deletePanel(id);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/plain");
			response.sendRedirect("/Simil");
		}
	}
	
}
