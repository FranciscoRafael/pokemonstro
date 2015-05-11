package app;

import java.awt.event.KeyListener;

import javax.swing.*;

import controller.Controller;
import controller.Keyboard;
import view.View;

public class Main extends JFrame{
    private Main() {
        this.setTitle("Pokemonstro");
        
        View panel = new View();
        Controller controller = new Controller(panel);
        KeyListener keyb = new Keyboard();
        panel.addKeyListener(keyb);
        
        // Size of the frame.
        this.setSize(View.Width, View.Height);
        // Puts frame to center of the screen.
        this.setLocationRelativeTo(null);
        // So that frame cannot be resizable by the user.
        this.setResizable(false);
        // Exit the application when user close frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Creates the instance of the View.java that extends the Canvas.java and puts it on the frame.
        this.setContentPane(panel);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
