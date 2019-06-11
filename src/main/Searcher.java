package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Searcher {
	// A map from the amount of people a room can hold to room type
	public static Map<Integer, String> dic = new HashMap<Integer, String>(){{
		put(1, "single");
		put(2, "double");
		put(4, "quad");
	}};
	private static Connection connection;
	
	// Functions for starting and closing connection to database
	private static Connection startConnection() {
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
	private static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// connection close failed.
			System.err.println(e.getMessage());
		}
	}

	// Search in hotels database to look for hotels with certain star rate
	// MAIN FUNCTION of project feature : 1. look for available hotels
	public static ArrayList<Hotel> searchHotel
	(int star, String start, String end, Map<Integer, Integer> desiredRooms) {
		Connection connection = startConnection();
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rs;
			if (star == 0) {
				rs = statement.executeQuery("SELECT * FROM hotels");
			} else {
				String command = "SELECT * FROM hotels WHERE hotelStar = ?";
				PreparedStatement pstmt = connection.prepareStatement(command);
				pstmt.setInt(1, star);
				rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				int id = rs.getInt("hotelID");
				int price = checkHotel(id, start, end, desiredRooms);
				if (price > 0) {
					int hotelStar = rs.getInt("hotelStar");
					String locality = rs.getString("locality");
					String address = rs.getString("address");
					list.add(new Hotel(id, hotelStar, locality, address, price));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			closeConnection();
		}
		Collections.sort(list, Hotel.cmpPrice);
		return list;
	}
	
	// Try to make reservation
	// MAIN FUNCTION of project feature : 2. make reservations
	public static Request makeRequest
	(int userID, int hotelID, String start, String end, Map<Integer, Integer> desiredRooms) {
		Connection connection = startConnection();
		int price = 0;
		int requestID = 0;
		
		// Check if there are rooms available first
		try {
			price = checkHotel(hotelID, start, end, desiredRooms);
			if(price < 0) throw new NoMoreRoomException(dic.get(-1 * price));
			// Make the request if all desired rooms are available
			try {
				Statement statement = connection.createStatement();
				statement.setQueryTimeout(30);
				ResultSet rs;
				String command = "SELECT MAX(requestID) FROM requests";
				rs = statement.executeQuery(command);
				if(rs.next()) {
					requestID = rs.getInt(1) + 1;
				}
				else {
					requestID = 0;
				}
				command = "INSERT INTO requests VALUES (?, ?, ?, ?, ?, ";
				int len = desiredRooms.size();
				for(int i = 0; i < len; i++) {
					command += "?, ";
				}
				command += "?)";
				PreparedStatement pstmt = connection.prepareStatement(command);
				pstmt.setInt(1, userID);
				pstmt.setInt(2, requestID);
				pstmt.setInt(3, hotelID);
				pstmt.setString(4, start);
				pstmt.setString(5, end);
				int temp = 6;
				for(Integer number : desiredRooms.values()) {
					pstmt.setInt(temp, number);
					temp += 1;
				}
				pstmt.setInt(temp, price);
				pstmt.executeUpdate();
				Request result = new Request(userID, requestID, hotelID, start, end, desiredRooms, price);
				return result;
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				closeConnection();
			}
		} catch(NoMoreRoomException e) {
			System.err.println(e);
		}
		return null;
	}
	
	// Look into rooms database to check if a hotel have enough rooms to be booked
	// USED IN project features : 1. look for available hotels 2. make reservations
	private static int checkHotel (int hotelID, String start, String end, Map<Integer, Integer> desiredRooms) {
		int totalPrice = 0;
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs;
			String command = "SELECT * FROM requests WHERE NOT ((Date(startDate) < ? "
					+ "AND Date(endDate) < ?) OR (Date(startDate) > ? AND Date(endDate) < ?))";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setString(1, start);
			pstmt.setString(2, start);
			pstmt.setString(3, end);
			pstmt.setString(4, end);
			rs = pstmt.executeQuery();
			Map<Integer, Integer> booked = new HashMap<Integer, Integer>();
			for(Integer roomType : desiredRooms.keySet()) {
				booked.put(roomType, 0);
			}
			while(rs.next()) {
				for(Integer roomType : desiredRooms.keySet()) {
					booked.merge(roomType, rs.getInt(dic.get(roomType)), Integer::sum);
				}
			}
			
			command = "SELECT * FROM rooms WHERE hotelID = ? AND roomType = ?";
			pstmt = connection.prepareStatement(command);
			pstmt.setInt(1, hotelID);
			for(Map.Entry<Integer, Integer> entry : desiredRooms.entrySet()) {
				if(entry.getValue() != 0) {
					pstmt.setInt(2, entry.getKey());
					rs = pstmt.executeQuery();
					if(rs.getInt("number") - booked.get(entry.getKey()) < entry.getValue()) return -1 * entry.getKey();
					totalPrice += rs.getInt("roomPrice") * entry.getValue();
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return totalPrice;
	}

	public static void main(String[] args) {
		Map<Integer, Integer> desiredRooms = new HashMap<Integer, Integer>();
		desiredRooms.put(1, 0);
		desiredRooms.put(2, 20);
		desiredRooms.put(4, 25);
		for (Hotel temp : searchHotel(5, "2011-01-01", "2011-01-02", desiredRooms)) {
			System.out.println(temp);
		}
		System.out.println(makeRequest(0, 770, "2011-01-01", "2011-01-03", desiredRooms));
		System.out.println(makeRequest(0, 1117, "2013-02-04", "2013-02-06", desiredRooms));
		System.out.println(makeRequest(0, 1117, "2013-01-08", "2014-02-05", desiredRooms));
	}
}
