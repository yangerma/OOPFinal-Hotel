package main;

public class NoMoreRoomException extends RuntimeException {
	public NoMoreRoomException () {
		super("Not enough rooms to be booked!");
	}
	public NoMoreRoomException (String msg) {
		super(msg);
	}
}
