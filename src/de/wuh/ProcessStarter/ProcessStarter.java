package de.wuh.ProcessStarter;

import java.io.*;

public class ProcessStarter {
    private ProcessBuilder pb1, pb2;
    private Process process1, process2;

    public ProcessStarter(){
        this.pb1 = new ProcessBuilder("python3", "main.py");
        this.pb1.directory(new File("./middle/WUH_middle_tier"));
        this.pb2 = new ProcessBuilder("pgrep", "python3");
    }

    // Check for Python already running
    private boolean checkPyRunning(){
        boolean returnValue = false;
        try {
            this.process2 = this.pb2.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.process2.getInputStream()));
            try{
                Integer.parseInt(reader.readLine());
                returnValue = true; // TRUE if Process returns PID
            }catch(NumberFormatException e){
                returnValue = false;
            }finally {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    // Wait for Python to start up
    public void waitFor(){
        if (checkPyRunning()) {
            try {
                // Start the Process and wait
                (this.process1 = pb1.start()).waitFor();

                // Start listening on the named Pipe
                BufferedReader reader = new BufferedReader(new FileReader(new File("./middle/WUH_middle_tier/pipe/modelup")));

                // When there is something in the pipe go on
                reader.readLine();
                reader.close();
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
