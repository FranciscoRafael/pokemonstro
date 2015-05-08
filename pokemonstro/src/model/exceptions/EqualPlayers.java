package model.exceptions;

public class EqualPlayers extends Exception {
	private static final long serialVersionUID = 1L;
	public EqualPlayers(String message, Throwable cause) {
		super(message,cause);
	}
	public EqualPlayers(String message){
		super(message);
	}
}
