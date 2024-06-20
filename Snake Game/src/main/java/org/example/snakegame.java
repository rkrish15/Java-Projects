package org.example;

import javax.swing.JFrame;

public class snakegame extends JFrame {
    public snakegame() {
        add(new gamepanel());
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new snakegame();
    }
}
