package main;

import java.util.*;

public class InputChecker {
	// Check hotelStar
	public static void starCheck(int star) {
		if(star < 0 || star > 5) throw new InputException("Illegal input : hotel star!");
	}
	
	public static void hotelCheck(int hotelID) {
		if(hotelID < 0) throw new InputException("Illegal input : hotel ID!");
	}
	
	public static void requestCheck(int requestID) {
		if(requestID < 0) throw new InputException("Illegal input : request ID!");
	}
	
	// Check starting and ending dates
	public static void datesCheck(String start, String end) {
		String[] startParts = start.split("-");
		String[] endParts = end.split("-");
		if(startParts.length != 3) throw new InputException("Illegal input : start date!");
		if(endParts.length != 3) throw new InputException("Illegal input : end date!");
		String[] part = {"year", "month", "day"};
		int[] days = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		for(int i = 0; i < 3; i++) {
			try {
				int temp = Integer.valueOf(startParts[i]);
				if(temp < 0) throw new InputException("Illegal input : start " + part[i] + "!");
				if(i == 1 && temp > 12) throw new InputException("Illegal input : start month!");
				if(i == 2 && temp > days[Integer.valueOf(startParts[1])])
					throw new InputException("Illegal input : start day!");
			} catch(NumberFormatException e) {
				throw new RuntimeException("Not a number : start " + part[i] + "!");
			} catch(NullPointerException e) {
				throw new RuntimeException("Starting " + part[i] + " cannot be empty!");
			}
			try {
				int temp = Integer.valueOf(endParts[i]);
				if(temp < 0) throw new InputException("Illegal input : end " + part[i] + "!");
				if(i == 1 && temp > 12) throw new InputException("Illegal input : end month!");
				if(i == 2 && temp > days[Integer.valueOf(endParts[1])])
					throw new InputException("Illegal input : end day!");
			} catch(NumberFormatException e) {
				throw new RuntimeException("Not a number : end " + part[i] + "!");
			} catch(NullPointerException e) {
				throw new RuntimeException("Ending " + part[i] + " cannot be empty!");
			}
		}
		if(start.compareTo(end) > 0) throw new RuntimeException("Illegal inputs : the dates are reversed!");
	}
	
	// Check desired rooms
	public static void roomsCheck(Map<Integer, Integer> desiredRooms) {
		boolean hasRoom = false;
		for(Integer rooms : desiredRooms.values()) {
			if(rooms < 0) throw new InputException("Illegal input : number of rooms!");
			if(rooms > 0) hasRoom = true;
		}
		if(!hasRoom) throw new InputException("Do you want to book some rooms or not?");
	}
	
	public static void newCheck
	(String start, String end, Map<Integer, Integer> rooms, String newStart, String newEnd, Map<Integer, Integer> newRooms) {
		boolean changed = false;
		
		// Check dates
		int startDiff = start.compareTo(newStart);
		int endDiff = end.compareTo(newEnd);
		if(startDiff > 0 || endDiff < 0) throw new InputException("You can only shroten the time reserved!");
		else if(startDiff != 0 && endDiff != 0) changed = true;
		
		// Check numbers of rooms
		for(Map.Entry<Integer, String> roomType : Searcher.dic.entrySet()) {
			int oldRoom = rooms.get(roomType.getKey());
			int newRoom = newRooms.get(roomType.getKey());
			if(oldRoom < newRoom)
				throw new InputException("You are trying to book even more " + roomType.getValue() + " rooms!");
			else if(oldRoom != newRoom) changed = true;
		}
		
		if(!changed) throw new InputException("You are not trying to change anything!");
	}
}
