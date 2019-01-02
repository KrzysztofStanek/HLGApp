package com.example.krzysztofstanek.hlgappmobile;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Password {

    public static String hash(String pass_plain_text)throws Exception{
        //String pass = "asdfa13rxhfghdq1"+String.valueOf(pass_plain_text.length()+15)+pass_plain_text+"ib1gnssaxf1"+String.valueOf(pass_plain_text.length());
        String pass = pass_plain_text;
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(pass.getBytes(),0,pass.length());
        String hash = new BigInteger(1,m.digest()).toString(16);
       //hash = "af11"+hash+"1gas31dajn";
        return hash;
    }


}