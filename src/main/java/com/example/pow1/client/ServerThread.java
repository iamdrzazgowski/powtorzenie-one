package com.example.pow1.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerThread {
    private Socket serverSocket;
    private PrintWriter output;

    public ServerThread(String serverAddress, int serverPort) {
        try {
            serverSocket = new Socket(serverAddress, serverPort);
            output = new PrintWriter(serverSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(int x, int y, int radius) {
        String message = x + "," + y + "," + radius;
        output.println(message);
    }
}
