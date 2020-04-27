package de.wuh.listener;

import de.wuh.Predictor;
import de.wuh.frames.DLabel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PyProcessListener extends Thread  {

    private DLabel label;
    private Image[] images;
    private Image[] smileys;
    private Image logo;
    private int step = 0;

    //Constructor
    public PyProcessListener(DLabel label){
        this.label = label;

        // Get all Pictures for the steps
        loadPictures();
    }

    // Load all Pictures into a Imagearray
    private void loadPictures(){
        this.images = new Image[9];
        this.smileys = new Image[2];

        // Steps
        for (int i = 0; i < 9; i++){
            try{
                this.images[i] = ImageIO.read(new File("pics/Bild" + (i + 1) + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        // Smileys
        for (int i = 0; i < 2; i++){
            try{
                this.smileys[i] = ImageIO.read(new File("pics/Smiley" + (i + 1) + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        // Logo
        try{
            this.logo = ImageIO.read(new File("pics/Logo.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // TODO: im TIRED!!!! REFACTOR!!!!
    @Override
    public void run() {
        super.run();
        // First Step!
        label.repaint(this.images[this.step]);
        while(this.step <= 9) {
            // Check ok?

            if (new Predictor(this.step).getReturn().equals(String.valueOf(this.step))) {
                label.repaint(this.smileys[1]); // Happy Smiley if alright!
                this.step++; // Prepare next Step!

                // Show Happy Smiley 0.7 Secs!
                try{
                    Thread.sleep(700);
                }catch(InterruptedException e){
                    e.getStackTrace();
                }
                if (this.step < 9)
                    label.repaint(this.images[this.step]); // <-- Render Next Step!
            }else{
                label.repaint(this.smileys[0]); // Sad Smiley! Repeat last step!
                // Show Sad Smiley 0.7 Secs!
                try{
                    Thread.sleep(700);
                }catch(InterruptedException e){
                    e.getStackTrace();
                }
                label.repaint(this.images[this.step]); // <-- Render OLD Step!
            }
        }
        label.repaint(this.logo);
        // Todo: Monitor off - NOT POSSIBLE ON TRAINING MACHINE -
    }
}
