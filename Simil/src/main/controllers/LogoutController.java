package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println(session.toString());
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return; // <--- Here.
	}
}