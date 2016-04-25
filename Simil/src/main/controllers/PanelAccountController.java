package controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/PanelAccountServlet")
public class PanelAccountController extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session= request.getSession();
		System.out.println("Beginning to join panels.");
		//String[] array = request.getParameterValues("panels");
		String panelID = request.getParameter("panel");
		if(request.getParameter("action").equals("join")){
			database.Panel_Account.insertPanelAccounts((String) session.getAttribute("username"), panelID);
		}
		else if(request.getParameter("action").equals("leave")){
			database.Panel_Account.deletePanel_Account(panelID, (String) session.getAttribute("username"));
		}
		ArrayList<String> panels = database.Account.getPanels((String) session.getAttribute("username"));
		session.setAttribute("panels", panels);


		// Insert to Panel_Account 
		System.out.println("Finished joining panels.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("Getting panelaccounts with id: " + id);
		ArrayList<String> users = database.Panel_Account.getAllAccountsFromPanel(id);
		JSONArray ar = new JSONArray(users);
		String json = ar.toString();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
