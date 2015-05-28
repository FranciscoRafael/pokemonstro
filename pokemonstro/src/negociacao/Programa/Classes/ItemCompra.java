package negociacao.Programa.Classes;

/**
 * Created by Rafael on 28/04/2015.
 */
public class ItemCompra {
    private int qtdProduto;
    private Produto produto;

    public ItemCompra(Produto produto, int qtdProduto){
        this.produto = produto;
        this.qtdProduto = qtdProduto;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQtdProduto(int quantidade) {
        this.qtdProduto = quantidade;
    }

    public void add(ItemCompra outroItem)
    {
        this.qtdProduto = this.qtdProduto + outroItem.qtdProduto;
    }

    public void subtract(ItemCompra outroItem)
    {
        this.qtdProduto = this.qtdProduto - outroItem.qtdProduto;
    }
}
