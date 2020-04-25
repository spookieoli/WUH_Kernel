package de.wuh.frames;

import de.wuh.listener.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame {
    // private Vars
    private Listener listener;

    public MainWindow(String Title, Listener listener){
        // Call Superclas Contrsuctor
        super(Title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set to full Screen
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);;
        this.setUndecorated(true);

        // Set visible true
        this.setVisible(true);

        // Get listener Var
        this.listener = listener;

        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Hide the Cursor
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        this.getContentPane().setCursor(blankCursor);

        // Keylistener - to end the Programm
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Runtime.getRuntime().exit(0);
                }
            }
        });
    }
}
