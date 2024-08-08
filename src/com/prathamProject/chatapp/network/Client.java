package com.prathamProject.chatapp.network;

import com.prathamProject.chatapp.utils.ConfigReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    Socket socket;
    OutputStream out;
    InputStream in;
    ClientWorker worker;
    JTextArea textArea;

    public Client(JTextArea textArea) throws IOException, UnknownHostException {
        int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
        socket = new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
        out = socket.getOutputStream();
        in = socket.getInputStream();
        this.textArea = textArea;
        readMessages();
    }

    public void sendMessage(String message) throws IOException {
        message=message+"\n";
        out.write(message.getBytes());
    }

    void readMessages() {
        worker = new ClientWorker(in, textArea);
        worker.start();
    }

}

