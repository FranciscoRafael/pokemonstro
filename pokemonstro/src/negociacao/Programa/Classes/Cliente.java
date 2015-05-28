package negociacao.Programa.Classes;
import negociacao.Programa.Interfaces.ILojaGUI;

import javax.swing.*;

/**
 * Created by Rafael on 11/05/2015.
 */
public class Cliente {
    public static void main(String[] args) {
        //Custom button text
        Object[] options = {"Loja","Posto Troca"};

        int dialogResult = JOptionPane.showOptionDialog(null, "Loja ou Posto Troca?", "Aviso",  JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                options,
                options[0]);

        if(dialogResult == JOptionPane.YES_OPTION) {
            ILojaGUI loja = new GUILoja();
            loja.abreLoja();
        }
        else {
            PostoTrocaGUI postoTroca = new PostoTrocaGUI();
            postoTroca.abrePostoTroca();
        }
    }
}
