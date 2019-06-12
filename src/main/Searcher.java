package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

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
		
		// Check if inputs are legal
		InputChecker.starCheck(star);
		InputChecker.datesCheck(start, end);
		InputChecker.roomsCheck(desiredRooms);
		
		// Start looking into database
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet rs;
			if (star == 0) {
				rs = statement.executeQuery("SELECT * FROM hotels");
			} else {
				String command = "SELECT * FROM hotels WHERE hotelStar = ?";
				PreparedStatement pstmt = connection.prepareStatement(command);
				pstmt.setInt(1, star);
				rs = pstmt.executeQuery();
			}
			// Show the results
			while (rs.next()) {
				int id = rs.getInt("hotelID");
				int valid = checkHotel(id, start, end, desiredRooms);
				if (valid > 0) {
					int hotelStar = rs.getInt("hotelStar");
					String locality = rs.getString("locality");
					String address = rs.getString("address");
					list.add(new Hotel(id, hotelStar, locality, address, computePrice(id, start, end, desiredRooms)));
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
		
		// Check if inputs are legal
		InputChecker.hotelCheck(hotelID);
		InputChecker.datesCheck(start, end);
		InputChecker.roomsCheck(desiredRooms);
		if(LocalDate.parse(getToday()).isAfter(LocalDate.parse(start))) throw new TooLateException("Too late to book rooms!");
		
		int requestID = 0;
		try {
			// Check if the hotel exists first
			ResultSet rs;
			String command = "SELECT * FROM hotels WHERE hotelID = ?";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setInt(1, hotelID);
			rs = pstmt.executeQuery();
			if(!rs.next()) throw new NoMoreRoomException("The hotel doesn't exist!");
			
			// Check if there are rooms available
			int valid = checkHotel(hotelID, start, end, desiredRooms);
			if(valid < 0) throw new NoMoreRoomException("Not enough " + dic.get(-1 * valid) + " rooms available!");
			
			// Make request if rooms are available
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			command = "SELECT MAX(requestID) FROM requests";
			rs = statement.executeQuery(command);
			if(rs.next()) {
				requestID = rs.getInt(1) + 1;
			}
			else {
				requestID = 0;
			}
			int price = computePrice(hotelID, start, end, desiredRooms);
			command = "INSERT INTO requests VALUES (?, ?, ?, ?, ?, ";
			int len = desiredRooms.size();
			for(int i = 0; i < len; i++) {
				command += "?, ";
			}
			command += "?)";
			pstmt = connection.prepareStatement(command);
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
		return null;
	}
	
	// Try to delete a certain request
	// MAIN FUNCTION of project feature : 3.1 delete requests
	public static boolean deleteRequest(int userID, int requestID) {
		Connection connection = startConnection();
		
		// Check if inputs are legal
		InputChecker.requestCheck(requestID);
		
		try {
			// Check if the request exists
			ResultSet rs = findRequest(userID, requestID);
			if(!rs.next()) throw new RuntimeException("Wrong userID/requestID, no matching request found");
			String start = rs.getString("startDate");
			
			// Check if it's too late to delete request
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			if(LocalDate.parse(getToday()).isAfter(LocalDate.parse(start)))
				throw new TooLateException("Too late to delete request!");
			
			// No problem deleting the request now
			String command = "DELETE FROM requests WHERE userID = ? AND requestID = ?";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, requestID);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e){
			System.err.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return false;
	}
	
	// Try to modify request
	// MAIN FUNCTION of project feature : 3.2 modify requests
	public static boolean modifyRequest
	(int userID, int requestID, String newStart, String newEnd, Map<Integer, Integer> newRooms) {
		Connection connection = startConnection();
		
		// Check if inputs are legal
		InputChecker.requestCheck(requestID);
		InputChecker.datesCheck(newStart, newEnd);
		InputChecker.roomsCheck(newRooms);
		
		try {
			// Check if the request exists and fetch old request data
			ResultSet rs = findRequest(userID, requestID);
			int hotelID = rs.getInt("hotelID");
			String start = rs.getString("startDate");
			String end = rs.getString("endDate");
			Map<Integer, Integer> rooms = new HashMap<Integer, Integer>();
			for(Map.Entry<Integer, String> roomType : dic.entrySet()) {
				rooms.put(roomType.getKey(), rs.getInt(roomType.getValue()));
			}
			
			// Check if it's too late to modify request and check if the new request is valid
			if(LocalDate.parse(getToday()).isAfter(LocalDate.parse(start)))
				throw new TooLateException("Too late to modify request!");
			InputChecker.newCheck(start, end, rooms, newStart, newEnd, newRooms);
			
			// Update the request with new information
			int price = computePrice(hotelID, newStart, newEnd, newRooms);
			String command = "UPDATE requests SET startDate = ?, endDate = ?, ";
			for(String roomType : dic.values()) {
				command += roomType + " = ?, ";
			}
			command += "price = ? WHERE requestID = ?";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setString(1, newStart);
			pstmt.setString(2, newEnd);
			int temp = 3;
			for(Integer number : newRooms.values()) {
				pstmt.setInt(temp, number);
				temp += 1;
			}
			pstmt.setInt(temp, price);
			pstmt.setInt(temp + 1, requestID);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e){
			System.err.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return false;
	}
	
	// Search for a request with requestID
	// MAIN FUNCTION of project feature : 4. look for a certain request
	public static Request searchRequest(int userID, int requestID) {
		Connection connection = startConnection();
		
		// Check if inputs are legal
		InputChecker.requestCheck(requestID);
		
		// Start looking into database
		try {
			ResultSet rs = findRequest(userID, requestID);
			if(!rs.next()) throw new RuntimeException("Wrong userID/requestID, no matching request found");
			int hotelID = rs.getInt("hotelID");
			String start = rs.getString("startDate");
			String end = rs.getString("endDate");
			Map<Integer, Integer> rooms = new HashMap<Integer, Integer>();
			for(Map.Entry<Integer, String> entry : dic.entrySet()) {
				rooms.put(entry.getKey(), rs.getInt(entry.getValue()));
			}
			int price = rs.getInt("price");
			return new Request(userID, requestID, hotelID, start, end, rooms, price);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			closeConnection();
		}
		return null;
	}
	
	// Get today's date
	// USED IN all functions that needs to check if the time is too late
	private static String getToday() {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			ResultSet now = statement.executeQuery("SELECT Date('now')");
			if(!now.next()) throw new RuntimeException("Something wierd happened.");
			return now.getString(1);
		} catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// Calculate price for the request
	// USED IN all functions that needs to get price
	private static int computePrice(int hotelID, String start, String end, Map<Integer, Integer> desiredRooms) {
		int totalPrice = 0;
		try {
			// Get the total numbers of rooms in the hotel
			ResultSet rs;
			String command = "SELECT * FROM rooms WHERE hotelID = ? AND roomType = ?";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setInt(1, hotelID);
			for(Map.Entry<Integer, Integer> entry : desiredRooms.entrySet()) {
				if(entry.getValue() != 0) {
					pstmt.setInt(2, entry.getKey());
					rs = pstmt.executeQuery();
					totalPrice += rs.getInt("roomPrice") * entry.getValue();
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return totalPrice * Math.toIntExact(Request.countDays(start, end));
	}
	
	// Look into rooms database to check if a hotel have enough rooms to be booked
	// USED IN project features : 1. look for available hotels 2. make reservations
	private static int checkHotel (int hotelID, String start, String end, Map<Integer, Integer> desiredRooms) {
		int totalPrice = 0;
		Map<Integer, Integer> allRooms = new HashMap<Integer, Integer>();
		try {
			// Get the total numbers of rooms in the hotel
			ResultSet rs;
			String command = "SELECT * FROM rooms WHERE hotelID = ? AND roomType = ?";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setInt(1, hotelID);
			for(Map.Entry<Integer, Integer> entry : desiredRooms.entrySet()) {
				if(entry.getValue() != 0) {
					pstmt.setInt(2, entry.getKey());
					rs = pstmt.executeQuery();
					allRooms.put(entry.getKey(), rs.getInt("number"));
				}
			}
			// Look up rooms that are already booked in this hotel in requests table
			LocalDate before = LocalDate.parse(start);
			LocalDate after = LocalDate.parse(end);
			for(LocalDate i = before; i.isBefore(after); i = i.plusDays(1)) {
				Map<Integer, Integer> remainRooms = allRooms;
				command = "SELECT * FROM requests WHERE hotelID = ? AND ? BETWEEN startDate AND endDate";
				pstmt = connection.prepareStatement(command);
				pstmt.setInt(1, hotelID);
				pstmt.setString(2, i.toString());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					for(Integer roomType : remainRooms.keySet()) {
						remainRooms.merge(roomType, (-1) * rs.getInt(dic.get(roomType)), Integer::sum);
					}
				}
				for(Map.Entry<Integer, Integer> roomsLeft : remainRooms.entrySet()) {
					if(roomsLeft.getValue() < desiredRooms.get(roomsLeft.getKey())) return -1 * roomsLeft.getKey();
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return 1;
	}
	
	// Search in requests table to look for the request
	// USED IN project features : 3. delete/modify requests, 4. look for a certain request
	private static ResultSet findRequest(int userID, int requestID) {
		try {
			ResultSet rs;
			String command = "SELECT * FROM requests WHERE userID = ? AND requestID = ?";
			PreparedStatement pstmt = connection.prepareStatement(command);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, requestID);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// Demo code snippet
	public static void main(String[] args) {
		// Initialize the rooms desired into a map<Integer, Integer>
		Map<Integer, Integer> desiredRooms = new HashMap<Integer, Integer>();
		desiredRooms.put(1, 0);
		desiredRooms.put(2, 20);
		desiredRooms.put(4, 25);
		
		// Demo for project feature : 1. look for available hotels
		System.out.println("Feature 1 demo");
		for (Hotel temp : searchHotel(5, "2011-01-01", "2011-01-02", desiredRooms)) {
			System.out.println(temp);
		}
		
		// Demo for project feature : 2. make reservations
		System.out.println("\nFeature 2 demo");
		System.out.println(makeRequest(0, 770, "2020-01-01", "2020-01-03", desiredRooms));
		System.out.println(makeRequest(0, 1117, "2020-02-04", "2020-02-06", desiredRooms));
		System.out.println(makeRequest(1, 1176, "2019-10-03", "2019-10-10", desiredRooms));
		
		// Demo for project feature : 3.1 delete requests
		System.out.println("\nFeature 3 demo");
		if(deleteRequest(0, 1)) System.out.println("Successfully deleted request (0, 1)");
		if(deleteRequest(0, 2)) System.out.println("Successfully deleted request (0, 2)");
		
		// Demo for project feature : 3.2 modify requests
		if(modifyRequest(1, 3, "2019-10-05", "2019-10-10", desiredRooms))
			System.out.println("Successfully modified request (1, 3)");
		
		// Demo for project feature : 4. look for a certain request
		System.out.println("\nFeature 4 demo");
		Request ret = searchRequest(1, 3);
		if(ret != null) System.out.println("Search result :\n" + ret.toString());
		
		// Cleaning up
		deleteRequest(1, 3);
	}
}