package de.wuh.frames;

import javax.swing.*;
import java.awt.*;

public class DLabel extends JLabel {
    private Image im;
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
}
