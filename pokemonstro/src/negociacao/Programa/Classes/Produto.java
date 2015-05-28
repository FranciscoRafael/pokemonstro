package negociacao.Programa.Classes;

/**
 * Created by Rafael on 28/04/2015.
 */
public class Produto {
    private EnumProdutos tipo;
    private int valor;
    private int qtdEstoque;

    public Produto (){}

    public Produto(EnumProdutos tipo,int valor,int qtdEstoque){
        this.tipo = tipo;
        this.valor = valor;
        this. qtdEstoque = qtdEstoque;
    }

    public EnumProdutos getTipo() {
        return tipo;
    }

    public void setTipo(EnumProdutos tipo) {
        this.tipo = tipo;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    public void subtraiEstoque(int quantidade) {
        this.qtdEstoque -= quantidade;
    }
    public void adicionaEstoque(int quantidade) { this.qtdEstoque += quantidade; }


}
