
package database;

import java.sql.*;

import utility.SimilConnection;

public class Location {

	// location(locationID, city, state, zip, country) United States
	public boolean insertLocation(String city, String state, String zip, String country){
		if(existingLocation(city, state, zip, country))
			return true;
		try{
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO Location (city, state, zip, country) VALUES(?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, state);
			stmt.setString(3, zip);
			stmt.setString(4, country);
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
	public boolean deleteLocation(String city, String state, String zip, String country){
		try{
			Connection conn = SimilConnection.connect();
			String query = "DELETE FROM Location WHERE ....";
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
	
	public boolean existingLocation(String city, String state, String zip, String country){
		try{
			Connection conn = SimilConnection.connect();
			String query = "SELECT EXISTS(SELECT 1 FROM location WHERE"
					+ "city = ? AND state = ? AND zip = ? AND country = ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, state);
			stmt.setString(3, zip);
			stmt.setString(4, country);
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			if(rs.getInt(1) == 1){
				conn.close();
				return true;
			}
			conn.close();
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
}
