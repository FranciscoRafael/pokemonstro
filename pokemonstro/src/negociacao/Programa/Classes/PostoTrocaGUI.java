package negociacao.Programa.Classes;

import negociacao.Programa.Interfaces.ITrocaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rafael on 17/05/2015.
 */
public class PostoTrocaGUI extends JFrame implements ITrocaGUI {
    private JList listRequisitos;
    private JList listRecompensa;
    private JButton finalizaButton;
    private JButton sairButton;
    private JPanel rootPanel;

    public PostoTrocaGUI(){
        super("Troca");
    }

    @Override
    public void abrePostoTroca() {
        Troca troca = Troca.getInstancia();
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listRequisitos.setModel(listModel);

        ButtonHandler handler = new ButtonHandler(troca, listModel);
        finalizaButton.addActionListener(handler);
        sairButton.addActionListener(handler);
    }

    //classe interna para tratamento do botao
    public class ButtonHandler implements ActionListener {
        private DefaultListModel<String> listModel;
        private Troca troca;
        public ButtonHandler(Troca troca,DefaultListModel<String> listModel) {
            this.troca = troca;
            this.listModel = listModel;
        }



        //trata evento do botao
        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == finalizaButton) {
                troca.finaliza();
            }
            if (event.getSource() == sairButton) {
                int resposta = troca.cancela();

                if (resposta == JOptionPane.YES_OPTION)
                    dispose();
            }
        }
    }


}
