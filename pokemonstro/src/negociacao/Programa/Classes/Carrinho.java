package negociacao.Programa.Classes;


import negociacao.Programa.Interfaces.IModificaCompra;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 28/04/2015.
 */
//encapsulamento da classe faz com que outros componentes nao altere o carrinho de compras
class Carrinho extends Transacao implements IModificaCompra {
    private int precoCompra;
    private EnumContexto contexto;
    private static Carrinho instancia = new Carrinho();

    private Carrinho(){
        contexto = EnumContexto.COMPRA;
        precoCompra = 0;
        listaProdutosEstoque = new ListaProduto();
        listaProdutos = new ArrayList<>();
    }

    public static Carrinho getInstancia(){
        return instancia;
    }

    public void setContexto(EnumContexto contexto) {
        this.contexto = contexto;
        if(contexto == EnumContexto.COMPRA) {
            precoCompra = 0;
            listaProdutosEstoque = new ListaProduto();
            listaProdutos = new ArrayList<>();
        }
        else {
            precoCompra = 0;
            listaProdutosEstoque = new ListaProduto();
            listaProdutos = new ArrayList<>();
        }

}


    public static ItemCompra buscaProdutoEmLista(List<ItemCompra> list, Produto busca) {
        for(ItemCompra obj : list) {
            if(obj.getProduto().getTipo().equals(busca.getTipo())) {
                return obj;
            }
        }

        return null;
    }


    @Override
    public void delProduto(Produto produto, int quantidade) {
        List<Produto> estoque = listaProdutosEstoque.getProdutos();
        ItemCompra antigoItem = new ItemCompra(produto,quantidade);
        // Busca o produto no carrinho
        int currIndex = listaProdutos.indexOf(buscaProdutoEmLista(listaProdutos, produto));
        // Busca o produto no estoque
        int indexEstoque = -1;
        for(Produto obj : estoque) {
            if(obj.getTipo().equals(produto.getTipo())) {
                indexEstoque = estoque.indexOf(obj);
                break;
            }
        }
        CarrinhoEventos event;

        // Se nao encontrou, apenas retorna, assumindo que esta tudo bem
        if (currIndex != -1) {
            event = new CarrinhoEventos();
            ItemCompra currItem = listaProdutos.get(currIndex);

            /*
            se voce esta tentando remover mais itens do que se tem no carrinho,
            ajusta a quantidade a ser removida para o total de itens no carrinho
            */
            if (antigoItem.getQtdProduto() > currItem.getQtdProduto()) {
                antigoItem.setQtdProduto(currItem.getQtdProduto());
            }

            //aumenta no estoque a quantidade removida do carrinho
            estoque.get(indexEstoque).adicionaEstoque(antigoItem.getQtdProduto());

            precoCompra -= antigoItem.getProduto().getValor() * antigoItem.getQtdProduto();

            //tira o item do carrinho
            currItem.subtract(antigoItem);

            event.item = currItem;
            event.eventType = CarrinhoEventos.CHANGED_ITEM;

            // Se a quantidade foi para 0, remove da lista
            if (currItem.getQtdProduto() == 0) {
                listaProdutos.remove(currIndex);
                event.eventType = CarrinhoEventos.REMOVED_ITEM;
            }

        }
    }

    @Override
    public void finaliza() {

    }

    @Override
    public int cancela() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja limpar a transa\u00e7\u00e3o?", "Aviso", JOptionPane.YES_NO_OPTION);


        if(dialogResult == JOptionPane.YES_OPTION){
            listaProdutos.clear();
            if (contexto == EnumContexto.COMPRA) {
                precoCompra = 0;
                //listaProdutosEstoque.connect(new ImplINome());
                listaProdutosEstoque = new ListaProduto();
            }
            else{
                precoCompra = 0;
                //listaProdutosEstoque.connect(new ImplINome());
                listaProdutosEstoque = new ListaProduto();
            }
            return 1;
        }
        else{
            return 0;
        }
    }
    @Override
    // new ItemCompra(produto,quantidade)
    public void addProduto(Produto produto, int quantidade) {
        List<Produto> estoque = listaProdutosEstoque.getProdutos();
        int indexEstoque = -1;
        for(Produto obj : estoque) {
            if(obj.getTipo().equals(produto.getTipo())) {
                indexEstoque = estoque.indexOf(obj);
                break;
            }
        }


        if(indexEstoque == -1 || estoque.get(indexEstoque).getQtdEstoque() == 0)
            return;

        Produto produtoEstoque = estoque.get(indexEstoque);

        ItemCompra novoItem = new ItemCompra(produto,quantidade);
        int currIndex = listaProdutos.indexOf(buscaProdutoEmLista(listaProdutos, produto));

        //se o estoque for menor que aquilo que se deseja comprar, compra o estoque inteiro
        //senao basta subtrair a quantidade do estoque
        if(produtoEstoque.getQtdEstoque() < quantidade) {
            novoItem.setQtdProduto(produtoEstoque.getQtdEstoque());
            listaProdutosEstoque.getProdutos().get(indexEstoque).setQtdEstoque(0);
        }
        else
            listaProdutosEstoque.getProdutos().get(indexEstoque).subtraiEstoque(quantidade);

        CarrinhoEventos event = new CarrinhoEventos();

        // Se nao tem um produto do mesmo tipo no carrinho
        if (currIndex == -1) {
            listaProdutos.add(novoItem);

            event.item = novoItem;
            event.eventType = CarrinhoEventos.ADDED_ITEM;
        }
        // Se ja tem um produto do mesmo tipo no carrinho, muda a quantidade
        else {
            ItemCompra currItem = listaProdutos.get(currIndex);
            currItem.add(novoItem);
            event.item = currItem;
            event.eventType = CarrinhoEventos.CHANGED_ITEM;
        }

        precoCompra += novoItem.getProduto().getValor() * novoItem.getQtdProduto();
    }

    public int getPrecoCompra(){
        return this.precoCompra;
    }
}
