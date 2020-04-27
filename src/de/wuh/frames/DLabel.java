package de.wuh.frames;

// Imports
import javax.swing.*;
import java.awt.*;

public class DLabel extends JLabel {
    private Image im;

    // Constructor
    public DLabel(Image im, int w, int h){
        this.im = im;
        this.setPreferredSize(new Dimension(w, h));
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(this.im, 0, 0, this.getWidth(), this.getHeight(),this);
    }

    // Repaint if New Image is Provided - MUST be Thread Save
    public synchronized void repaint(Image im){
        this.im = im;
        super.repaint();
    }
}
