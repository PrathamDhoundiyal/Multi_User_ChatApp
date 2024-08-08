package com.prathamProject.chatapp.views;



import com.prathamProject.chatapp.network.Client;
import com.prathamProject.chatapp.utils.UserInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

public class ClientChatScreen extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea ;
    private Client client;

    public static void main(String[] args) {

        try {
            ClientChatScreen frame = new ClientChatScreen();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void sendIt() {
        String message =textField.getText();
        try {
            client.sendMessage(UserInfo.USER_NAME+"-"+message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ClientChatScreen() throws IOException {
        textArea = new JTextArea();
        client = new Client(textArea);
        setTitle("Lets chat");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 799, 425);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 6, 760, 313);
        contentPane.add(scrollPane);

        textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textArea.setBounds(10, 24, 753, 280);
        scrollPane.setViewportView(textArea);



        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField.setBounds(10, 338, 602, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton sendIt = new JButton("Send Message");
        sendIt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendIt();
            }
        });
        sendIt.setFont(new Font("Tahoma", Font.BOLD, 12));
        sendIt.setBounds(634, 347, 121, 27);
        contentPane.add(sendIt);

        setVisible(true);
    }
}
