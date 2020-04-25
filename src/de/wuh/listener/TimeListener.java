package de.wuh.listener;

import de.wuh.frames.DLabel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TimeListener extends Thread implements Listener  {

    private DLabel label;
    private Image[] images;
    private int step = 0;

    //Constructor
    public TimeListener(DLabel label){
        this.label = label;

        // Get all Pictures for the steps
        loadPictures();
    }

    // Count 4 Secs
    @Override
    public synchronized int getReadyState() {
        while(true){
            try{
                Thread.sleep(4000);
            }catch( InterruptedException ie){
                ie.printStackTrace();
            }
            return 1; // For test purposes
        }
    }

    // Load all Pictures into a Imagearray
    private void loadPictures(){
        this.images = new Image[3];

        for (int i = 0; i < 3; i++){
            try{
                this.images[i] = ImageIO.read(new File("pics/Bild" + (i + 1) + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        while(this.step < 4) {
            if (getReadyState() == 1) {
                label.repaint(this.images[this.step++]);
                if (this.step == 3) {
                    this.step = 0;
                }
            }
        }
    }
}
