package com.prathamProject.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Hashing the PassWords

public interface Encryption {
    public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException {
        String encryptedPassword;
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      messageDigest.update(plainPassword.getBytes());
      byte[] encrypt = messageDigest.digest();
 StringBuffer sb =new StringBuffer();
      for(byte b : encrypt)
          sb.append(b);
     encryptedPassword=sb.toString();
        System.out.println("Encrypted password "+encryptedPassword);

        return encryptedPassword;
    }
}
