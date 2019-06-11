package main;

public class NoMoreRoomException extends Exception {
	String ret;
	public NoMoreRoomException (String roomType) {
		ret = "Not enough " + roomType + " room left!";
	}
	public String toString() {
		return ret;
	}
}
