package com.prathamProject.chatapp.dto;

//Class used to Transfer Data
//Screen se leke (Swing Screen)
//We hand over the data to DAO

public class UserDTO {
    private String userid;
    private char[] password;

    public UserDTO(String userid, char[] password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public char[] getPassword() {
        return password;
    }

}
