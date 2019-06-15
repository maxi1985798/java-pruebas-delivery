package com.educacionit.delivery;


import org.apache.commons.codec.binary.Base64;

public class Test {


    public static void main (String[] args){

        String originalInput = "qwerty";
        Base64 base64 = new Base64();
        String encodedString = new String(base64.encode(originalInput.getBytes()));
        System.out.println (encodedString);

        String decodedString = new String(base64.decode(encodedString.getBytes()));
        System.out.println (decodedString);
    }
}
