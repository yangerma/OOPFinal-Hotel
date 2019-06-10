package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelSearcher {
	// Look into rooms database to check if a hotel have certain rooms to be booked
	public static int checkHotel(Connection connection, int hotelID, int rSingle, int rDouble, int rQuad) {
		int totalPrice = 0;
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			String command = "SELECT * FROM rooms WHERE hotelID = " + hotelID + " ";
			ResultSet rs = statement.executeQuery(command + "AND roomType = 1");
			if (rs.getInt("number") < rSingle)
				return -1;
			totalPrice += rs.getInt("roomPrice") * rSingle;
			rs = statement.executeQuery(command + "AND roomType = 2");
			if (rs.getInt("number") < rDouble)
				return -1;
			totalPrice += rs.getInt("roomPrice") * rDouble;
			rs = statement.executeQuery(command + "AND roomType = 4");
			if (rs.getInt("number") < rQuad)
				return -1;
			totalPrice += rs.getInt("roomPrice") * rQuad;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return totalPrice;
	}

	// Search in hotels database to look for hotels with certain star rate
	public static ArrayList<Hotel> search(int star, int rSingle, int rDouble, int rQuad) {
		Connection connection = null;
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:hotelList.db");
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
				int price = checkHotel(connection, id, rSingle, rDouble, rQuad);
				if (price != -1) {
					int hotelStar = rs.getInt("hotelStar");
					String locality = rs.getString("locality");
					String address = rs.getString("address");
					list.add(new Hotel(id, hotelStar, locality, address, price));
				}
			}
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e.getMessage());
			}
		}
		return list;
	}

	public static void main(String[] args) {
		for (Hotel temp : search(5, 0, 13, 25)) {
			System.out.println(temp);
		}
	}
}
