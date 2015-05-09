package model.exceptions;

/*lancada quando ha uma tentativa de criar entidade ja existente*/
public class PreexistingEntityException extends ControlledException {
	private static final long serialVersionUID = 1L;
	public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
