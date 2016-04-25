package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	
	public String getPost(int id){
		try{
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
