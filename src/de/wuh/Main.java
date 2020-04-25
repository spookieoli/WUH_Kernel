package de.wuh;

import de.wuh.frames.MainWindow;
import de.wuh.listener.HardwareListener;

public class Main {
    // Main Startingpoint of the Kernel
    public static void main(String[] args) {
        MainWindow w = new MainWindow("Test", new HardwareListener());
    }
}
