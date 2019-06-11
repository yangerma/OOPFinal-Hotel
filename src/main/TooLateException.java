package main;

public class TooLateException extends Exception {
	public TooLateException() {
		super("Too late to delete/modify this request!");
	}
	public TooLateException(String msg) {
		super(msg);
	}
}
