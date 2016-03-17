package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utility.SimilConnection;

public class Panel {

	public static ArrayList<String[]> getAllPanels() {
		ArrayList<String[]> panels = new ArrayList<>();
		try{
			Connection conn = SimilConnection.connect();
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
			conn.close();
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			System.out.println(ex);
		}
		return panels;
	}
	
	// panel(ID, panelName, desc, related, moderators, creator)
	public boolean insertPanel(String panelName, String panelDescription, 
			String relatedPanels, String panelModerators, String panelCreator){
		try{
			Connection conn = SimilConnection.connect();
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
			conn.close();
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	// TODO: Pass this all values, or the ID?
	public boolean deleteLocation(){
		try{
			Connection conn = SimilConnection.connect();
			String query = "DELETE FROM Panel WHERE ....";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.execute(query);
			conn.close();
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
}
