package negociacao.Programa.Classes;

import negociacao.Programa.Interfaces.IEfetuaTransacao;

import java.util.List;
//import java.util.Observable;

/**
 * Created by Rafael on 11/05/2015.
 */
public abstract class Transacao implements IEfetuaTransacao {
    protected List<ItemCompra> listaProdutos;
    protected ListaProduto listaProdutosEstoque;

    @Override
    abstract public void finaliza();

    @Override
    abstract public int cancela();

    public List<ItemCompra> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<ItemCompra> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ListaProduto getListaProdutosEstoque() {
        return listaProdutosEstoque;
    }

    public void setListaProdutosEstoque(ListaProduto listaProdutosEstoque) {
        this.listaProdutosEstoque = listaProdutosEstoque;
    }
}
