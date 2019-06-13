package main;

import java.util.*;
import java.time.*;
import java.time.format.*;

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
		try {
			LocalDate before = LocalDate.parse(start);
			LocalDate after = LocalDate.parse(end);
			if(before.isEqual(after)) 
				throw new InputException("Illegal inputs : you cannot check in and check out at the same day!");
			if(before.isAfter(after)) throw new InputException("Illegal inputs : the dates are reversed!");
		} catch(DateTimeParseException e) {
			throw new InputException("Illegal input : date!");
		}
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
		LocalDate before = LocalDate.parse(start);
		LocalDate after = LocalDate.parse(end);
		LocalDate newBefore = LocalDate.parse(newStart);
		LocalDate newAfter = LocalDate.parse(newEnd);
		if(newBefore.isBefore(before) || newAfter.isAfter(after))
			throw new InputException("Illegal input : you can only shroten the time reserved!");
		else if(!newBefore.isEqual(before) || !newAfter.isEqual(after)) changed = true;
		
		// Check numbers of rooms
		for(Map.Entry<Integer, String> roomType : Searcher.dic.entrySet()) {
			int oldRoom = rooms.get(roomType.getKey());
			int newRoom = newRooms.get(roomType.getKey());
			if(oldRoom < newRoom) 
				throw new InputException("Illegal input : you are trying to book more " + roomType.getValue() + " rooms!");
			else if(oldRoom != newRoom) changed = true;
		}
		
		if(!changed) throw new InputException("You are not trying to change anything!");
	}
}
