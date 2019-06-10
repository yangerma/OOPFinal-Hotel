package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class HotelSearcher {
	// Look into rooms database to check if a hotel have enough rooms to be booked
	public static int checkHotel
	(Connection connection, int hotelID, String start, String end, int rSingle, int rDouble, int rQuad) {
		int totalPrice = 0;
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs;
			String command = "SELECT * FROM requests WHERE "
					+ "Date(startDate) <= \"" + start + "\" AND Date(endDate) >= \"" + end + "\"";
			rs = statement.executeQuery(command);
			int single_booked = 0;
			int double_booked = 0;
			int quad_booked = 0;
			while(rs.next()) {
				single_booked += rs.getInt("single");
				double_booked += rs.getInt("double");
				quad_booked += rs.getInt("quad");
			}

			command = "SELECT * FROM rooms WHERE hotelID = " + hotelID + " ";
			if(rSingle != 0) {
				rs = statement.executeQuery(command + "AND roomType = 1");
				if (rs.getInt("number") - single_booked < rSingle)
					return -1;
				totalPrice += rs.getInt("roomPrice") * rSingle;
			}
			if(rDouble != 0) {
				rs = statement.executeQuery(command + "AND roomType = 2");
				if (rs.getInt("number") - double_booked < rDouble)
					return -1;
				totalPrice += rs.getInt("roomPrice") * rDouble;
			}
			if(rQuad != 0) {
				rs = statement.executeQuery(command + "AND roomType = 4");
				if (rs.getInt("number") - quad_booked < rQuad)
					return -1;
				totalPrice += rs.getInt("roomPrice") * rQuad;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return totalPrice;
	}

	// Search in hotels database to look for hotels with certain star rate
	public static ArrayList<Hotel> search
	(int star, String start, String end, int rSingle, int rDouble, int rQuad) {
		Connection connection = startConnection();
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rs;
			if (star == 0) {
				rs = statement.executeQuery("SELECT * FROM hotels");
			} else {
				String command = "SELECT * FROM hotels WHERE hotelStar = " + star;
				rs = statement.executeQuery(command);
			}
			while (rs.next()) {
				int id = rs.getInt("hotelID");
				int price = checkHotel(connection, id, start, end, rSingle, rDouble, rQuad);
				if (price != -1) {
					int hotelStar = rs.getInt("hotelStar");
					String locality = rs.getString("locality");
					String address = rs.getString("address");
					list.add(new Hotel(id, hotelStar, locality, address, price));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			closeConnection(connection);
		}
		Collections.sort(list, Hotel.cmpPrice);
		return list;
	}
	
	public static Connection startConnection() {
		Connection connection = null;
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:hotelList.db");
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		return connection;
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// connection close failed.
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		for (Hotel temp : search(5, "2011-01-01", "2011-01-02", 0, 20, 25)) {
			System.out.println(temp);
		}
	}
}
