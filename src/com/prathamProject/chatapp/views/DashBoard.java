package com.prathamProject.chatapp.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DashBoard extends JFrame {

    private JPanel contentPane;


    public DashBoard(String message) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(100, 100, 1161, 746);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu chatMenu = new JMenu("Chat");
        menuBar.add(chatMenu);

        JMenuItem startChat = new JMenuItem("Start Chat");
        startChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ClientChatScreen();
                } catch (UnknownHostException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        chatMenu.add(startChat);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        setTitle(message);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(DashBoard.class.getResource("/Images/Image5.png")));
        contentPane.add(lblNewLabel, BorderLayout.CENTER);
    }

}
