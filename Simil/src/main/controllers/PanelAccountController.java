package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PanelAccountController {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Beginning to join panels.");
		String[] array = request.getParameterValues("panels");
		// Insert to Panel_Account 
		database.Panel_Account.insertPanelAccounts((String) request.getSession().getAttribute("username"), array);
		System.out.println("Finished joining panels.");
	}
}
