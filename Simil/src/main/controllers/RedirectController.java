package controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/RedirectController")
public class RedirectController extends HttpServlet {
	@SuppressWarnings("static-access")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Set response content type
		response.setContentType("text/html");

		// New location to be redirected
		String site = new String("/Simil/index.jsp");

		response.setStatus(response.SC_FORBIDDEN);
		response.setHeader("Location", site);

	}
}
