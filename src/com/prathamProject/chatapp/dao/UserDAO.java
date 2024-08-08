package com.prathamProject.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.*;

import com.prathamProject.chatapp.dto.UserDTO;
import com.prathamProject.chatapp.utils.Encryption;

//User CRUD Operations performed here

public class UserDAO {
    public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException,Exception {
        Connection conn =null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        final String SQL ="select userid from users where userID=? and passWord=?";
        try{
             conn = CommonDAO.createConnection();
             pstmt= conn.prepareStatement(SQL);
             pstmt.setString(1,userDTO.getUserid());
            String encryptedPwd=Encryption.passwordEncrypt(new String (userDTO.getPassword()));
            pstmt.setString(2,encryptedPwd);
           rs=pstmt.executeQuery();

           return rs.next(); //

        }
        finally {
            if(rs!=null)
                rs.close();
            if(pstmt!=null)
                pstmt.close();
            if(conn!=null)
                conn.close();
        }
    }
    public int add(UserDTO userDTO) throws ClassNotFoundException,SQLException,Exception {
        System.out.println("rec "+userDTO.getUserid()+" "+userDTO.getPassword());
        Connection connection=null;


        Statement stmt=null;


        connection = CommonDAO.createConnection();

        stmt=connection.createStatement();

        try {
            int record = stmt.executeUpdate("insert into users(userID,passWord) values ('" + userDTO.getUserid() + "','" + Encryption.passwordEncrypt( new String(userDTO.getPassword())) + "')");
            return record;
        }
        finally {
            if (stmt != null)
                stmt.close();
            if (connection != null)
                connection.close();
        }
    }
}
