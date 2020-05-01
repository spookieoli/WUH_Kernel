package de.wuh.ProcessStarter;

import java.io.*;

public class ProcessStarter {
    private ProcessBuilder pb;
    private Process process;

    public ProcessStarter(){
        this.pb = new ProcessBuilder("python3", "main.py");
        this.pb.directory(new File("./middle/WUH_middle_tier"));
    }

    // Wait for Python to start up
    public void waitFor(){
        try {
            // Start the Process and wait
            (this.process = pb.start()).waitFor();

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
