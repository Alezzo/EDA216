package krusty;

import krusty.models.Pallet;
import sun.misc.Perf;

import java.lang.String;
import java.sql.*;
import java.time.LocalDate;
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

	public ArrayList<Pallet> getAllPallets() {

		return getPalletsForCookie(null, null, null);

	}


	public ArrayList<Pallet> getPalletsForCookie(String cookieName, LocalDate fromDate, LocalDate toDate) {

		String sql = "select palletId, cookieName, orderId, productionDate, deliveryDate, location, isBlocked " +
				"from Pallet";

		String andWhere = "where";

		if (cookieName != null) {
			sql += " " + andWhere + " cookieName = ?";
			andWhere = "and";
		}
		if (fromDate != null) {
			sql += " " + andWhere + " productionDate > ?";
			andWhere = "and";
		}
		if (toDate != null) {
			sql += " " + andWhere + " deliveryDate < ?";
		}

		ArrayList<Pallet> pallets = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			int i = 1;

			if (cookieName != null) {
				ps.setString(i++, cookieName);
			}
			if (fromDate != null) {
				ps.setString(i++, fromDate.toString());
			}
			if (toDate != null) {
				ps.setString(i++, toDate.toString());
			}

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

    public boolean createNewPallet(String cookieName, LocalDate productionDate, String location) {
        String insertSQL = "insert into Pallet" +
                "values (NULL, ?, NULL, ?, ?, 0, NULL)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(insertSQL);

            int i = 1;

            if (cookieName != null) {
                ps.setString(i++, cookieName);
            }
            if (productionDate != null) {
                ps.setString(i++, productionDate.toString());
            }
            if (location != null) {
                ps.setString(i++, location);
            }

            System.out.println(cookieName + " " + productionDate + " " + location);
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                // ... can do nothing if things go wrong here
            }
        }
        return false;
    }
}
