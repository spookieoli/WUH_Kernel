package de.wuh.net;

import java.io.*;
import java.net.*;
import java.net.InetAddress;

public class UDP_Service {
    DatagramSocket socket;
    InetAddress adress;
    int port, foreignport;

    // Constructor
    public UDP_Service(int port, int foreignport){
        this.port = port;
        this.foreignport = foreignport;

        // Create UDP Socket
        try{
            this.socket = new DatagramSocket(port);
        }catch(SocketException e){
            System.out.println("Create Socket Fails!");
            e.printStackTrace();
        }

        // Get localhost ip Adress
        try {
            this.adress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    // Send and receive
    public String sendReceive(int step){
        byte[] received = new byte[1024];
        String returnValue = "-1";

        // Send the Data to the Prediction-Daemon
        try {
            String str = String.valueOf(step);
            socket.send(new DatagramPacket(str.getBytes(), str.getBytes().length, adress, this.foreignport));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "-1";
        }

        // OK - get Signal back!
        try {
            DatagramPacket dg = new DatagramPacket(received, received.length);
            socket.receive(dg);
            try{
                returnValue = new String(dg.getData());
            }catch (NumberFormatException e){
                e.getStackTrace();
                return "-1";
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "-1";
        }
        return returnValue;
    }
    // Schliesse Socket sauber
    public void close(){
        this.socket.close();
    }
}
