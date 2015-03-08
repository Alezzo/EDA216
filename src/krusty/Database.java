package krusty;

import javafx.collections.ObservableList;
import krusty.models.Pallet;
import sun.misc.Perf;

import java.lang.String;
import java.sql.*;
import java.util.ArrayList;

/**
 * Database is a class that specifies the interface to the movie database. Uses
 * JDBC and the MySQL Connector/J driver.
 */
public class Database {
	/**
	 * The database connection.
	 */
	private Connection conn;

	/**
	 * Create the database interface object. Connection to the database is
	 * performed later.
	 */
	public Database() {
		conn = null;
	}

	/**
	 * Open a connection to the database, using the specified user name and
	 * password.
	 * 
	 * @param userName
	 *            The user name.
	 * @param password
	 *            The user's password.
	 * @return true if the connection succeeded, false if the supplied user name
	 *         and password were not recognized. Returns false also if the JDBC
	 *         driver isn't found.
	 */
	public boolean openConnection(String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://puccini.cs.lth.se/" + userName, userName,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Close the connection to the database.
	 */
	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		}
		conn = null;
	}

	/**
	 * Check if the connection to the database has been established
	 * 
	 * @return true if the connection has been established
	 */
	public boolean isConnected() {
		return conn != null;
	}

	/* --- insert own code here --- */

	public String[] getCookieNames()
	{

		String sql = "select cookieName " +
				     "from Cookie ";

		ArrayList<String> cookies = new ArrayList<String>();

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next())
			{
				cookies.add(rs.getString("cookieName"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cookies.toArray(new String[cookies.size()]);
	}

	public Pallet[] getAllPallets() {

		// TODO: Implement a proper query
		// Remember to insert some testData in the database first!
		return new Pallet[] {new Pallet(1, "Cookie", 1, "2015-03-08", "2015-03-28", "i sjukstugan", false), new Pallet(2, "BadCookie", 2, "2015-03-08","2015-04-28", "i sjukstugan", true)};

	}
	
	
	public ArrayList<Pallet> getPalletsForCookie(String cookieName, String fromDate, String toDate) {

		String sql = "select palletId, cookieName, orderId, productionDate, deliveryDate, location, isBlocked " +
				"from Pallet " +
				"where cookieName = ?";

		ArrayList<Pallet> pallets = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cookieName);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				pallets.add(new Pallet(rs.getInt("palletId"), rs.getString("cookieName"), rs.getInt("orderId"), rs.getString("productionDate"), rs.getString("deliveryDate"), rs.getString("location"), rs.getBoolean("isBlocked")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pallets;

	}
}
