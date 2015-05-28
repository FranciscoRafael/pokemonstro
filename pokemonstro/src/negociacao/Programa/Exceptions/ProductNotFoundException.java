package negociacao.Programa.Exceptions;

/**
 * Created by Rafael on 10/05/2015.
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(){}
    public ProductNotFoundException(String msg){
        super(msg);
    }
}