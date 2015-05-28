package negociacao.Programa.Classes;

import negociacao.Programa.Interfaces.ILojaGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafael on 11/05/2015.
 */
public class GUILoja extends JFrame implements ILojaGUI{
    private JList carrinhoCompraJList;
    private JButton addProdutoA;
    private JButton delProdutoA;
    private JButton addProdutoB;
    private JButton delProdutoB;
    private JButton addProdutoC;
    private JButton delProdutoC;
    private JPanel rootPanel;
    private JLabel total;
    private JButton finaliza;
    private JButton cancela;
    private JTabbedPane JTabbedPanel;
    private JPanel compraJPanel;
    private JButton addVendaProdutoA;
    private JButton delVendaProdutoA;
    private JButton addVendaProdutoB;
    private JButton delVendaProdutoB;
    private JButton addVendaProdutoC;
    private JButton delVendaProdutoC;
    private JButton finalizarButton;
    private JButton cancelarButton;
    private JList carrinhoVendaJList;
    private JList estoqueJList;
    private JPanel vendaJPanel;
    private JLabel totalVenda;

    public void abreLoja(){
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ButtonHandlerCompra handlerCompra = new ButtonHandlerCompra();
        ButtonHandlerVenda handlerVenda = new ButtonHandlerVenda();
        TabSelectionHandler tab = new TabSelectionHandler();

        addProdutoA.addActionListener(handlerCompra);
        addProdutoB.addActionListener(handlerCompra);
        addProdutoC.addActionListener(handlerCompra);
        delProdutoA.addActionListener(handlerCompra);
        delProdutoB.addActionListener(handlerCompra);
        delProdutoC.addActionListener(handlerCompra);
        finaliza.addActionListener(handlerCompra);
        cancela.addActionListener(handlerCompra);
        addVendaProdutoA.addActionListener(handlerVenda);
        delVendaProdutoA.addActionListener(handlerVenda);
        addVendaProdutoB.addActionListener(handlerVenda);
        delVendaProdutoB.addActionListener(handlerVenda);
        addVendaProdutoC.addActionListener(handlerVenda);
        delVendaProdutoC.addActionListener(handlerVenda);
        finalizarButton.addActionListener(handlerVenda);
        cancelarButton.addActionListener(handlerVenda);

        JTabbedPanel.addChangeListener(tab);
    }

    public GUILoja() {
        super("Loja");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public class TabSelectionHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            Carrinho carrinho = Carrinho.getInstancia();

            if (JTabbedPanel.getSelectedIndex() == 1){
                carrinho.comecaVenda();
                ButtonHandlerVenda atualiza = new ButtonHandlerVenda();
                atualiza.atualizaCarrinho();
            }
            else{
                carrinho.comecaCompra();
                ButtonHandlerCompra atualiza = new ButtonHandlerCompra();
                atualiza.atualizaCarrinho();
            }
        }
    }



    //classe interna para tratamento dos botoes de compra
    public class ButtonHandlerCompra implements ActionListener {
        private DefaultListModel<String> listModel= new DefaultListModel<>();
        private Carrinho compra = Carrinho.getInstancia();

        public void atualizaCarrinho(){
            int i = 0;
            listModel.removeAllElements();
            for (ItemCompra obj : Carrinho.getInstancia().getListaProdutos())
                listModel.addElement(obj.getProduto().getTipo() + " qtd:" + String.valueOf(obj.getQtdProduto()));

            carrinhoCompraJList.setModel(listModel);
            total.setText("Total da Compra: " + Carrinho.getInstancia().getPrecoCompra());
        }

        //trata evento do botao
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == addProdutoA) {
                Produto A = new Produto(EnumProdutos.ProdutoA, 10, 1);
                compra.addProduto(A, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == addProdutoB) {
                Produto B = new Produto(EnumProdutos.ProdutoB, 10, 1);
                compra.addProduto(B, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == addProdutoC) {
                Produto C = new Produto(EnumProdutos.ProdutoC, 10, 1);
                compra.addProduto(C, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == delProdutoA) {
                Produto A = new Produto(EnumProdutos.ProdutoA, 10, 1);
                compra.delProduto(A, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == delProdutoB) {
                Produto B = new Produto(EnumProdutos.ProdutoB, 10, 1);
                compra.delProduto(B, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == delProdutoC) {
                Produto C = new Produto(EnumProdutos.ProdutoC, 10, 1);
                compra.delProduto(C, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == finaliza) {

            }
            if (event.getSource() == cancela) {
                compra.cancela();
                atualizaCarrinho();
            }

        }
    }

    //classe interna para tratamento dos botoes de venda
    public class ButtonHandlerVenda implements ActionListener {
        private DefaultListModel<String> listModelJogador = new DefaultListModel<>();
        private DefaultListModel<String> listModelVendido = new DefaultListModel<>();
        private Carrinho compra = Carrinho.getInstancia();

        public void atualizaCarrinho(){
            int i = 0;
            listModelJogador.removeAllElements();
            listModelVendido.removeAllElements();

            for (ItemCompra obj : Carrinho.getInstancia().getListaProdutos())
                listModelJogador.addElement(obj.getProduto().getTipo() + " qtd:" + String.valueOf(obj.getQtdProduto()));
            for (Produto obj : Carrinho.getInstancia().getListaProdutosEstoque().getProdutos()){
                listModelVendido.addElement(obj.getTipo() + " qtd:" + String.valueOf(obj.getQtdEstoque()));
            }

            estoqueJList.setModel(listModelVendido);
            carrinhoVendaJList.setModel(listModelJogador);

            totalVenda.setText("Total a ganhar: " + Carrinho.getInstancia().getPrecoCompra());
        }

        //trata evento do botao
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == addVendaProdutoA) {
                Produto A = new Produto(EnumProdutos.ProdutoA, 10, 1);
                compra.addProduto(A, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == addVendaProdutoB) {
                Produto B = new Produto(EnumProdutos.ProdutoB, 10, 1);
                compra.addProduto(B, 1);
                atualizaCarrinho();

            }
            if (event.getSource() == addVendaProdutoC) {
                Produto C = new Produto(EnumProdutos.ProdutoC, 10, 1);
                compra.addProduto(C, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == delVendaProdutoC) {
                Produto C = new Produto(EnumProdutos.ProdutoC, 10, 1);
                compra.delProduto(C, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == delVendaProdutoB) {
                Produto B = new Produto(EnumProdutos.ProdutoB, 10, 1);
                compra.delProduto(B, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == delVendaProdutoA) {
                Produto A = new Produto(EnumProdutos.ProdutoA, 10, 1);
                compra.delProduto(A, 1);
                atualizaCarrinho();
            }
            if (event.getSource() == finalizarButton) {

            }
            if (event.getSource() == cancelarButton) {
                compra.cancela();
                atualizaCarrinho();
            }

        }
    }
}



