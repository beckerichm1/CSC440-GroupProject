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
		System.out.println("Getting panel details");
		if(param.equals("one")){
			System.out.println("Getting id: ");
			String id = request.getParameter("id");
			System.out.println(id);
			panels = database.Panel.getPanelDetail(id);
			// put the panel into a json object and send it through
			JSONObject obj = new JSONObject(panels.get(0));
			String json = obj.toString();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
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
		System.out.println("Beginning to insert new panel.");
		String param = request.getParameter("name");
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
	
}
