package negociacao.Programa.Classes;

import negociacao.Programa.Interfaces.IEfetuaTransacao;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Rafael on 10/05/2015.
 */
class Troca extends Transacao implements IEfetuaTransacao{
    private static Troca instancia = new Troca();

    private Troca(){
        //produtos requeridos
        listaProdutosEstoque = new ListaProduto();
        //produtos ganhos
        listaProdutos = new ArrayList<>();
    }

    public static Troca getInstancia(){
        return instancia;
    }

    public boolean analisaPreRequisito(){

        listaProdutosEstoque.getProdutos();
        return true;
    }
    @Override
    public void finaliza() {

    }

    @Override
    public int cancela() {
        return JOptionPane.showConfirmDialog(null, "Realmente deseja sair?", "Aviso", JOptionPane.YES_NO_OPTION);
    }

}
