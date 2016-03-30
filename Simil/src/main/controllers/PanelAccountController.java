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
@WebServlet("/PanelAccountServlet")
public class PanelAccountController extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Beginning to join panels.");
		String[] array = request.getParameterValues("panels");
		// Insert to Panel_Account 
		database.Panel_Account.insertPanelAccounts((String) request.getSession().getAttribute("username"), array);
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
