package de.wuh.frames;

import de.wuh.listener.Listener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainWindow extends JFrame {
    // private Vars
    private Listener listener;
    private DLabel label;

    public MainWindow(String Title, Listener listener){
        // Call Superclas Contrsuctor
        super(Title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get listener Var
        this.listener = listener;

        try{
            this.label = new DLabel(ImageIO.read(new File("picture1.png")), this.getWidth(), this.getHeight());
            this.add(label);
        }catch(Exception e){
            e.getStackTrace();
        }

        // Set to full Screen
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);;
        this.setUndecorated(true);

        // Transparent 16 x 16 pixel cursor image. So this doesnt disturb Customer
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Hide the Cursor
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank");

        // Set the blank cursor to the JFrame.
        this.getContentPane().setCursor(blankCursor);
        this.setVisible(true);

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
