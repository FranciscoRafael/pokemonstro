package negociacao.Programa.Classes;

import negociacao.Programa.Interfaces.INome;

import java.util.*;

/**
 * Created by Rafael on 29/04/2015.
 */
public class ListaProduto {
    private List<Produto> produtos;

    public void connect(INome nome) {

    }
    public ListaProduto(){
        produtos = new ArrayList<>();

        Produto A = new Produto(EnumProdutos.ProdutoA, 10, 100);
        Produto B = new Produto(EnumProdutos.ProdutoB, 10, 100);
        Produto C = new Produto(EnumProdutos.ProdutoC, 10, 100);

        produtos.add(A);
        produtos.add(B);
        produtos.add(C);
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
}
