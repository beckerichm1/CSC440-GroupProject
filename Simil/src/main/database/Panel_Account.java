package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import utility.SimilConnection;

public class Panel_Account {
	// panel_account(panelID, user, dateJoined, contrib, upvote)
	public boolean insertPanel_Account(int panelID, String userName, String dateJoined/*, int contribution, int upVote*/) {
		try {
			int contribution = 0;
			int upVote = 0;
			Connection conn = SimilConnection.connect();
			Class.forName("com.mysql.jdbc.Driver");
			String query = "INSERT INTO Panel_Account VALUES(?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, panelID);
			stmt.setString(2, userName);
			stmt.setString(3, dateJoined);
			stmt.setInt(4, contribution);
			stmt.setInt(5, upVote);
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public boolean deletePanel_Account(int panelID, String userName) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "DELETE FROM panel_account where panelID = ? AND userName = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, panelID);
			stmt.setString(2, userName);
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	// TODO: This isn't optimized, it returns all columns.
	// TODO: Would ideally return what we need, just panels perhaps.
	public ArrayList<Integer> getAllPanelsFromAccount(String userName) {
		ArrayList<Integer> panels = new ArrayList<>();
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT panelID FROM panel_account WHERE userName = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				panels.add(rs.getInt("panelID"));
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return panels;
	}

	public static ArrayList<String> getAllAccountsFromPanel(String panelID) {
		ArrayList<String> accounts = new ArrayList<>();
		// Check if panelID passed is ID or name
		if(panelID.matches("-?\\d+(\\.\\d+)?")){
			panelID = database.Panel.getPanelDetail(panelID).get(0)[0];
		}
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT userName FROM panel_account WHERE panelID = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, panelID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				accounts.add(rs.getString("userName"));
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return accounts;
	}

	public static boolean insertPanelAccounts(String username, String[] array) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		try {
			Connection conn = SimilConnection.connect();
			for (int i = 0; i < array.length; i++) {
				String query = "INSERT INTO Panel_Account (panelID, userName, dateJoined)" 
								+ " VALUES (?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, array[i]);
				stmt.setString(2, username);
				stmt.setString(3, dateFormat.format(cal.getTime()));
				stmt.executeUpdate(query);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
}
