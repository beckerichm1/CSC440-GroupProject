
package database;

import java.sql.*;

public class Location {

	// location(locationID, city, state, zip, country) United States
	public boolean insertLocation(String city, String state, String zip, String country){
		if(existingLocation(city, state, zip, country))
			return true;
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			Class.forName("com.mysql.jdbc.Driver");
			String query = "INSERT INTO Location (city, state, zip, country) VALUES(?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, state);
			stmt.setString(3, zip);
			stmt.setString(4, country);
			stmt.execute(query);
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	// TODO: Pass this all values, or the ID?
	public boolean deleteLocation(String city, String state, String zip, String country){
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			String query = "DELETE FROM Location WHERE ....";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.execute(query);
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	public boolean existingLocation(String city, String state, String zip, String country){
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try(Connection conn = DriverManager.getConnection(url, user, pass)){
			//SELECT EXISTS(SELECT 1 FROM location WHERE locationID = 5)
			String query = "SELECT EXISTS(SELECT 1 FROM location WHERE"
					+ "city = ? AND state = ? AND zip = ? AND country = ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, city);
			stmt.setString(2, state);
			stmt.setString(3, zip);
			stmt.setString(4, country);
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			if(rs.getInt(1) == 1)
				return true;
		}
		catch(Exception ex){
			System.out.println(ex);
			return false;
		}
		return true;
	}
}
