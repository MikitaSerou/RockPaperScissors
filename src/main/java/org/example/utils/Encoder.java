package org.example.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encoder {

    public static void generate() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] values = new byte[16];
        random.nextBytes(values);

        StringBuilder sb = new StringBuilder();
        for (byte b : values) {
            sb.append(String.format("%02x", b));
        }
        System.out.print("Key: ");
        System.out.println(sb.toString());
    }

}
