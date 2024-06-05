package com.example.demo02.tool;

import java.util.Base64;

public class BASE64 {

    public static String encode(String data) {
        String encodedString = Base64.getEncoder().encodeToString(data.getBytes());
        return encodedString;
    }
    
    public static String decode(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
