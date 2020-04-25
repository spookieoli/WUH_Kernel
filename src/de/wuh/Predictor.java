package de.wuh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Predictor {

    private int returnCode;
    private ProcessBuilder pb;
    private Process process;

    public Predictor(int returnCode){
        this.returnCode = returnCode;
        this.pb = new ProcessBuilder();
    }

    // Get the Return Value of python and give it back to the Caller
    public String getReturn(){
        // Execute Command and Wait for returnCode
        return doCommand();
    }

    // Exceute the Command to python
    private String doCommand(){
        // Init for safety :)
        Process process = null;
        StringBuilder output = new StringBuilder();
        String line = null;
        try{
            process = this.pb.command("python3", "py/predictor.py",  ""+ this.returnCode +"").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            line = reader.readLine();
            System.out.println("Step: " + line);
            process.waitFor();
        }catch(IOException e){
            e.getStackTrace();
        }catch(InterruptedException i){
            i.getStackTrace();
        }
        return line;
    }
}
