package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilityFunctions {
    static public String encryptDecrypt(String inputString)
    {
        //key to encrypt and decrypt
        char xorKey = 'P';

        String outputString = "";
        int len = inputString.length();

        // perform XOR operation of key
        for (int i = 0; i < len; i++)
        {
            outputString = outputString + (char) (inputString.charAt(i) ^ xorKey);
        }

        System.out.println(outputString);
        return outputString;
    }

    
}
