package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/AccountServlet")
public class AccountController extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("Getting users like '" + id + "'");
		ArrayList<String> users = database.Account.getAccountsLike(id);
		JSONArray ar = new JSONArray(users);
		String json = ar.toString();
		request.setAttribute("searchUsers", json);
		
		//request.getRequestDispatcher("/WEB-INF/persons.jsp").forward(request, response);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
}
