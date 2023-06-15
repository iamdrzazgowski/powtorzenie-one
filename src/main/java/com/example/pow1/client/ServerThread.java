package com.example.pow1.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerThread extends Thread{
    private Socket serverSocket;
    private PrintWriter writer;

    public ServerThread(String serverAddress, int serverPort) {
        try {
            serverSocket = new Socket(serverAddress, serverPort);
            writer = new PrintWriter((serverSocket.getOutputStream()),true);
            System.out.println("Connected to server: "+serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(double x, double y, double radius) {
        String message = x + "," + y + "," + radius;
        writer.println(message);
        System.out.println("Sent message to server: "+message);
    }
}
