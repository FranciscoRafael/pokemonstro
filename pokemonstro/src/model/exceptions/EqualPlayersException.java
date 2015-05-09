package model.exceptions;

/*excecao lancada quando existem dois players com mesmo nome*/
public class EqualPlayersException extends ControlledException {
	private static final long serialVersionUID = 1L;
	public EqualPlayersException(String message, Throwable cause) {
		super(message,cause);
	}
	public EqualPlayersException(String message){
		super(message);
	}
}
