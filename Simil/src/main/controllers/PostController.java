package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

@SuppressWarnings("serial")
@WebServlet("/PostServlet")
public class PostController  extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String param = request.getParameter("param");
		String json = "";
		if(param.equals("post")){
			String[] post = database.PanelPost.getPost(id);
			JSONArray ar = new JSONArray(post);
			json = ar.toString();
		}
		else if(param.equals("posts")){
			ArrayList<String[]> posts = database.PanelPost.getPanelPosts(id);
			JSONArray ar = new JSONArray(posts);
			json = ar.toString();
		}
		else if(param.equals("comments")){
			ArrayList<String[]> comments = database.PanelPost.getComments(id);
			JSONArray ar = new JSONArray(comments);
			json = ar.toString();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute("username");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String newDate = dateFormat.format(date);
		System.out.println(newDate);
		
		database.PanelPost.insertPost(userName, id, name, title, content, newDate);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/plain");
		response.sendRedirect("/Simil/views/Panels/Panel.jsp?id=" + id + "&name=" + name);
	}
}
