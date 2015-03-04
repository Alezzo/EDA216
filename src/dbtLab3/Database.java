package dbtLab3;

import sun.misc.Perf;

import java.lang.String;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

	public String getUserByUsername(String username)
	{
		String sql = "select username " +
				"from Users " +
				"where username = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();

			if (!rs.next())
			{
				return null;
			}

			return rs.getString("username");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getAllMovies() {

		String sql = "select name " +
				"from Movies ";

		ArrayList<String> movies = new ArrayList<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next())
			{
				movies.add(rs.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movies;
	}

	public ArrayList<Date> getAllPerformancesForMovie(String movieName) {

		String sql = "select performanceDate " +
				"from performances " +
				"where movieName = ?";

		ArrayList<Date> performances = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, movieName);

			ResultSet rs = ps.executeQuery();

			while (rs.next())
			{
				performances.add(rs.getDate("performanceDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return performances;

	}

    public Performance getInfoForPerformance(String movieName, String performanceDate) {

        String sql = "select movieName, performanceDate, theatherName, availableSeats " +
                "from performances " +
                "where movieName = ? and performanceDate = ?";

        PreparedStatement ps = null;
        Performance performance = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, movieName);
            ps.setString(2, performanceDate);

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                performance = new Performance(rs.getString("movieName"), rs.getString("performanceDate"),
                        rs.getString("theatherName"), rs.getInt("availableSeats"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e2) {
                // ... can do nothing if things go wrong here
            }
        }

        return performance;
    }

	public boolean bookPerformance(String movieName, Date date, String username) {

		String selectSQL = "select availableSeats, id " +
				"from Performances " +
				"where movieName = ? and performanceDate = ? " +
				"for update";

		String insertSQL = "insert into Reservations " +
				"values (NULL, ?, ?)";

		String updateSQL = "update Performances " +
				"set availableSeats = availableSeats-1 " +
				"where movieName = ? and performanceDate = ?";

		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false); // Begin transaction

			ps = conn.prepareStatement(selectSQL);
			ps.setString(1, movieName);
			ps.setDate(2, date);

			ResultSet rs = ps.executeQuery();

			if (!rs.next())
			{
				conn.rollback();
				return false;
			}

			int availableSeats = rs.getInt("availableSeats");
			int performanceId = rs.getInt("id");

			System.out.println(availableSeats);

			if (availableSeats <= 0) {
				conn.rollback();
				return false;
			}

			ps = conn.prepareStatement(insertSQL);
			ps.setInt(1, performanceId);
			ps.setString(2, username);
			ps.executeUpdate();

			ps = conn.prepareStatement(updateSQL) ;
			ps.setString(1, movieName);
			ps.setDate(2, date);
			ps.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);

			return true;


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return false;
	}
}
