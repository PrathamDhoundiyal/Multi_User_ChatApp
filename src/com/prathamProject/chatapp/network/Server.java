package com.prathamProject.chatapp.network;

import com.prathamProject.chatapp.utils.ConfigReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    /* Multiple Clients*/
    ServerSocket serverSocket;
    ArrayList<ServerWorker> workers = new ArrayList<>(); // Contains all the client sockets

    public Server() throws IOException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
        serverSocket = new ServerSocket(PORT);
        System.out.println("Server Start and Waiting for the Client to join");
        handleClientRequest();
    }

    // Multiple Client Handshaking
    public void handleClientRequest() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept(); // this is handShaking
            // Per Client per Thread
            ServerWorker serverWorker = new ServerWorker(clientSocket, this); // Creating a new Thread/Worker
            workers.add(serverWorker);
            serverWorker.start();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}

