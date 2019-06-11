package main;

public class TooLateException extends RuntimeException {
	public TooLateException() {
		super("Too late to delete/modify this request!");
	}
	public TooLateException(String msg) {
		super(msg);
	}
}