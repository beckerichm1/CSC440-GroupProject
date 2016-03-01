package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Panel {

	public static ArrayList<String[]> getAllPanels() {
		ArrayList<String[]> panels = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		System.out.println("Attempting to make a connection...");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(url, user, pass);
			System.out.println("Connection has been made.");
			Statement stmt = (Statement) conn.createStatement();
			String showPanels = "SELECT * FROM Panel;";
			ResultSet rs = stmt.executeQuery(showPanels);
			while (rs.next()) {
				String[] panel = new String[2];
				String name = rs.getString("panelName");
				String desc = rs.getString("panelDesc");
				panel[0] = name;
				panel[1] = desc;
				panels.add(panel);
			}
			System.out.println("Finished getting panels.");
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			System.out.println(ex);
		}
		return panels;
	}
	
	// panel(ID, panelName, desc, related, moderators, creator)
	public boolean insertPanel(String panelName, String panelDescription, 
			String relatedPanels, String panelModerators, String panelCreator){
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "INSERT INTO Panel "
					+ "(panelName, panelDescription, relatedPanels, panelModerators, panelCreator)"
					+ " VALUES(?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, panelName);
			stmt.setString(2, panelDescription);
			stmt.setString(3, relatedPanels);
			stmt.setString(4, panelModerators);
			stmt.setString(5, panelCreator);
			stmt.execute(query);
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	// TODO: Pass this all values, or the ID?
	public boolean deleteLocation(){
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "DELETE FROM Panel WHERE ....";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.execute(query);
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
}
