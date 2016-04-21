package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import utility.SimilConnection;

public class Panel {

	public static ArrayList<String[]> getAllPanels() {
		ArrayList<String[]> panels = new ArrayList<>();
		try {
			Connection conn = SimilConnection.connect();
			Statement stmt = (Statement) conn.createStatement();
			String showPanels = "SELECT * FROM Panel;";
			ResultSet rs = stmt.executeQuery(showPanels);
			while (rs.next()) {
				String[] panel = new String[3];
				String id = rs.getString("panelID");
				String name = rs.getString("panelName");
				String desc = rs.getString("panelDesc");
				panel[0] = id;
				panel[1] = name;
				panel[2] = desc;
				panels.add(panel);
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return panels;
	}

	// panel(ID, panelName, desc, related, moderators, creator)
	public static boolean insertPanel(String panelName, String panelDescription, 
			String relatedPanels, String panelModerators, String panelCreator) {
		try {
			// TODO: Check to see if panel exists before inserting, or catch the exception...
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO Panel "
					+ "(panelName, panelDesc, relatedPanels, panelModerators, panelCreator)"
					+ " VALUES(?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, panelName);
			stmt.setString(2, panelDescription);
			stmt.setString(3, relatedPanels);
			stmt.setString(4, panelModerators);
			stmt.setString(5, panelCreator);
			stmt.execute();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	// TODO: Pass this all values, or the ID?
	// TODO: Either delete all Panel_Accounts as well, or delete and update/cascade dependencies
	public static boolean deletePanel(String id) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "DELETE FROM Panel WHERE panelID=?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(id));
			System.out.println(stmt);
			stmt.execute();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public static ArrayList<String> getUserPanels(String userName) {
		ArrayList<String> panels = new ArrayList<>();
		try {
			String query = "";
			Connection conn = SimilConnection.connect();
			// get panels
			query = "select panelName from Panel p JOIN Panel_Account pa where "
					+ "pa.panelID = p.panelID AND pa.userName = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			String panel;
			while (rs.next()) {
				panel = rs.getString("panelName");
				if(!panel.equals("null"))
					panels.add(panel);
			}
			conn.close();
		}catch (Exception ex) {
			System.out.println(ex);
		}
		return panels;
	}

	public static ArrayList<String[]> getNonUserPanels(String userName){
		String query = "SELECT A.panelID, panelName, panelDesc FROM( select pa.panelID  from panel_account pa "
				+ "WHERE pa.userName = ?) AS A RIGHT JOIN(select * from panel p)"
				+ "AS B ON A.panelID = B.panelID WHERE A.panelID is null;";
		ArrayList<String[]> panels = new ArrayList<>();
		try{
			Connection conn = SimilConnection.connect();
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String[] panel = new String[3];
				String id = rs.getString("panelID");
				String name = rs.getString("panelName");
				String desc = rs.getString("panelDesc");
				panel[0] = id;
				panel[1] = name;
				panel[2] = desc;
				panels.add(panel);
			}
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			//System.out.println(ex);
		}
		return panels;
	}

	public static ArrayList<String[]> getPanelDetail(String id) {
		ArrayList<String[]> panels = new ArrayList<>();
		try {
			Connection conn = SimilConnection.connect();
			// Added the 'or panelName = ?' to end to support querying either ID or name
			String showPanels = "SELECT * FROM Panel WHERE panelID = ? OR panelName = ?;";
			PreparedStatement stmt = conn.prepareStatement(showPanels);
			stmt.setString(1, id);
			stmt.setString(2, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String[] panel = new String[5];
				String name = rs.getString("panelName");
				String desc = rs.getString("panelDesc");
				String related = rs.getString("relatedPanels");
				String mods = rs.getString("panelModerators");
				panel[0] = id;
				panel[1] = name;
				panel[2] = desc;
				panel[3] = related;
				panel[4] = mods;
				panels.add(panel);
				System.out.println("PanelID = " + panel[0]);
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return panels;
	}

	public static ArrayList<String[]> getPanelsLike(String id) {
		ArrayList<String[]> panels = new ArrayList<>();
		try{
			Connection conn = SimilConnection.connect();
			String query = "SELECT panelName FROM Panel where panelName LIKE ? OR panelDesc LIKE ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id + "%");
			stmt.setString(2, id + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String[] name = {rs.getString("panelName")};
				panels.add(name);
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return panels;
	}
}
