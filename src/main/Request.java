package main;
import java.util.*;

public class Request {
	private int userID;
	private int requestID;
	private int hotelID;
	private String start;
	private String end;
	private Map<Integer, Integer> rooms;
	private int totalPrice;
	
	public Request(int userID, int requestID, int hotelID, String start, String end,
			Map<Integer, Integer> rooms, int totalPrice) {
		this.userID = userID;
		this.requestID = requestID;
		this.hotelID = hotelID;
		this.start = new String(start);
		this.end = new String(end);
		this.rooms = rooms;
		this.totalPrice = totalPrice;
	}

	public String toString() {
		String ret = "userID = " + userID + ", requestID = " + requestID + ", hotelID = " + hotelID +
				", start = " + start + ", end = " + end + "\n";
		for(Map.Entry<Integer, Integer> entry : rooms.entrySet()) {
			ret += Searcher.dic.get(entry.getKey()) + " : " + entry.getValue() + "\n";
		}
		ret += "totalPrice = " + totalPrice;
		return ret;
	}
}
