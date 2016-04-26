package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import utility.SimilConnection;

public class PanelPost {

	public static boolean insertPost(String user, String id, String name, String title, String content, String date) {
		try {
			// TODO: Check to see if panel exists before inserting, or catch the exception...
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO Post "
					+ "(userName, panelID, panelName, title, time, postContent)"
					+ " VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user);
			stmt.setString(2, id);
			stmt.setString(3, name);
			stmt.setString(4, title);
			stmt.setString(5, date);
			stmt.setString(6, content);
			stmt.execute();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
	public static ArrayList<String[]> getPanelPosts(String id){
		ArrayList<String[]> posts = new ArrayList<>();
		try{
			Connection conn = SimilConnection.connect();
			String query = "select * from post where panelID = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				String[] post = new String[5];
				post[0] = "" + rs.getInt("postID");
				post[1] = rs.getString("userName");
				post[2] = rs.getString("title");
				post[3] = rs.getString("time");
				post[4] = rs.getString("postContent");
				posts.add(post);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return posts;
	}
	public static String[] getPost(String id){
		String[] post = new String[6];
		try{
			Connection conn = SimilConnection.connect();
			String query = "select * from post where postID = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			post[0] = rs.getString("userName");
			post[1] = "" + rs.getInt("panelID");
			post[2] = rs.getString("panelName");
			post[3] = rs.getString("title");
			post[4] = rs.getString("time");
			post[5] = rs.getString("postContent");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return post;
	}

	public static ArrayList<String[]> getComments(String id) {
		ArrayList<String[]> comments = new ArrayList<>();
		String[] comment = new String[4];
		try{
			Connection conn = SimilConnection.connect();
			String query = "select * from comment where postID = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				comment[0] = "" + rs.getInt("commentID");
				comment[1] = rs.getString("userName");
				comment[2] = rs.getString("time");
				comment[3] = rs.getString("commentContent");
				comments.add(comment);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return comments;
	}
}
