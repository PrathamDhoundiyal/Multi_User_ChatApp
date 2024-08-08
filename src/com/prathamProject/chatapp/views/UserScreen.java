package com.prathamProject.chatapp.views;

import com.prathamProject.chatapp.dao.UserDAO;
import com.prathamProject.chatapp.dto.UserDTO;
import com.prathamProject.chatapp.utils.UserInfo;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class UserScreen extends JFrame {
    private JTextField useridtxt;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        UserScreen window = new UserScreen();
    }

    UserDAO userDAO = new UserDAO();

     private void doLogin(){
         String userid = useridtxt.getText();
         char[] password = passwordField.getPassword();
         UserDTO userDTO = new UserDTO(userid, password);
         try {
             String message = "";
             if (userDAO.isLogin(userDTO)){
                 message = "Welcome " + userid;
                 UserInfo.USER_NAME=userid;
             JOptionPane.showMessageDialog(this, message);
             setVisible(false);
             dispose();
             DashBoard dashBoard = new DashBoard(message);
             dashBoard.setVisible(true);}
              else{
                 message = "Invalid userID or passWord";
                 JOptionPane.showMessageDialog(this, message);
             }
         }
         catch(ClassNotFoundException e){
             e.printStackTrace();
         }
         catch(SQLException e){
             System.out.println("SQL error");
             e.printStackTrace();
         }
         catch(Exception e){
             e.printStackTrace();
         }
     }
    private void registor() {
        String userid = useridtxt.getText();
        //String password=passwordField.getText();
        char[] password = passwordField.getPassword();
         UserDTO userDTO = new UserDTO(userid, password);

        try {
            int result = userDAO.add(userDTO);
            if(result>0)
                 //System.out.println("Record added....");
                JOptionPane.showMessageDialog(this,"Record Added");
            else
                JOptionPane.showMessageDialog(this,"Registor Fail");
        }
        catch (ClassNotFoundException | SQLException ex){
            System.out.println("DB issue....");
            ex.printStackTrace();
        }
        catch(Exception ex){
            System.out.println("Some Generic Exception....");
            ex.printStackTrace();
        }
        System.out.println("User ID " + userid+" Password "+password);
    }
    public UserScreen() {
        setResizable(false);
        setTitle("LOGIN");
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(326, 36, 237, 58);
        getContentPane().add(lblNewLabel);

        useridtxt = new JTextField();
        useridtxt.setBounds(306, 159, 381, 34);
        getContentPane().add(useridtxt);
        useridtxt.setColumns(10);

        JLabel useridlbl = new JLabel("UserID");
        useridlbl.setFont(new Font("Tahoma", Font.BOLD, 22));
        useridlbl.setBounds(117, 159, 127, 34);
        getContentPane().add(useridlbl);

        JLabel pwlbl = new JLabel("Password");
        pwlbl.setFont(new Font("Tahoma", Font.BOLD, 22));
        pwlbl.setBounds(117, 234, 127, 34);
        getContentPane().add(pwlbl);

        passwordField = new JPasswordField();
        passwordField.setBounds(306, 234, 381, 35);
        getContentPane().add(passwordField);

        JButton loginbt = new JButton("Login");
        loginbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });
        loginbt.setFont(new Font("Tahoma", Font.BOLD, 20));
        loginbt.setBounds(208, 333, 160, 42);
        getContentPane().add(loginbt);

        JButton registorbt = new JButton("Registor");
        registorbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registor();
            }
        });
        registorbt.setFont(new Font("Tahoma", Font.BOLD, 20));
        registorbt.setBounds(445, 333, 189, 42);
        getContentPane().add(registorbt);
        setSize(833,440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

