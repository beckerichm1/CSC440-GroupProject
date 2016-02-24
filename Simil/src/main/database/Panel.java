package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Panel {

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
