package com.example.pow1.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread{
    private Socket clientSocket;
    private Server server;
    private PrintWriter writer;

    public ClientThread(Socket socket, Server server) {
        this.server = server;
        this.clientSocket = socket;
    }

    public void run(){
        try{
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(),true);
            String message;
            while((message = inputStream.readLine())!=null){
                server.broadcast(this,message);
                System.out.println("Received message from client: "+ message);
            }
            inputStream.close();
            writer.close();
            clientSocket.close();
            System.out.println("Closed\n");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
    }
}
