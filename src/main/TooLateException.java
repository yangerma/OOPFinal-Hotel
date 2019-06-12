package main;

public class TooLateException extends RuntimeException {
	public TooLateException() {
		super("It is Too late!");
	}
	public TooLateException(String msg) {
		super(msg);
	}
}