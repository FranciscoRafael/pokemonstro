package model.exceptions;

/*lancada quanto ha uma tentativa de usar entidade inexistente*/
public class NonexistentEntityException extends ControlledException {
	private static final long serialVersionUID = 1L;
	public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
