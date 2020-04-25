package de.wuh.listener;

public class HardwareListener implements Listener{

    // Return 0 if there was no Sensorcontact - otherwise return 1
    @Override
    public int getReadyState() {
        while(true){
            try{
                Thread.sleep(60000);
            }catch( InterruptedException ie){
                ie.printStackTrace();
            }
            return 1; // For test purposes
        }

    }
}
