package negociacao.Programa.Classes;

/**
 * Created by Rafael on 10/05/2015.
 */
public class CarrinhoEventos {
    // Define the kinds of changes that can take place
    public static final int ADDED_ITEM = 1;
    public static final int REMOVED_ITEM = 2;
    public static final int CHANGED_ITEM = 3;

    // item is the item that is affected
    public ItemCompra item;
    // eventType is the kind of change that has taken
    // place (add/remove/change)
    public int eventType;

    public CarrinhoEventos()
    {
    }

    public CarrinhoEventos(ItemCompra item, int eventType)
    {
        this.item = item;
        this.eventType = eventType;
    }
}
