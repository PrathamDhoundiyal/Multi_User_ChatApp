package com.prathamProject.chatapp.network;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientWorker extends Thread {
    private InputStream in;
    private JTextArea textArea;

    public ClientWorker(InputStream in, JTextArea textArea) {
        this.in = in;
        this.textArea = textArea;
    }
    @Override
    public void run() {
        BufferedReader br = new BufferedReader( new InputStreamReader(in));
        String line;
        try {
            while (true) {
                line = br.readLine(); // needs /n to stop
                System.out.println("Line Read"+line);
                textArea.setText(textArea.getText()+ line+"\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}