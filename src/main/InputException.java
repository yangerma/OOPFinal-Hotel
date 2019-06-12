package main;

public class InputException extends RuntimeException {
	public InputException() {
		super("Illegal input!");
	}
	public InputException(String msg) {
		super(msg);
	}
}
