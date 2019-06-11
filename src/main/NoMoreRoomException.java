package main;

public class NoMoreRoomException extends Exception {
	public NoMoreRoomException () {
		super("Not enough rooms available!");
	}
	public NoMoreRoomException (String msg) {
		super(msg);
	}
}
