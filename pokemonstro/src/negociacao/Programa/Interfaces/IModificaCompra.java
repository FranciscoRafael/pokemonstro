package negociacao.Programa.Interfaces;

import negociacao.Programa.Classes.Produto;
import negociacao.Programa.Classes.ItemCompra;
import negociacao.Programa.Exceptions.ProductNotFoundException;

/**
 * Created by Rafael on 28/04/2015.
 */
public interface IModificaCompra {
    void addProduto(Produto produto, int quantidade);

    void delProduto(Produto produto, int quantidade);
}
