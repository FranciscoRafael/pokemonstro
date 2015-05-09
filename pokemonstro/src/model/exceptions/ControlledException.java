package model.exceptions;

/*classe que representa excessoes tratadas
 *e com mensagens personalizadas para o usuario*/
public class ControlledException extends Exception {
	private static final long serialVersionUID = 1L;
	public ControlledException(String message, Throwable cause) {
		super(message, cause);
	}
	public ControlledException(String message){
		super(message);
	}
}
