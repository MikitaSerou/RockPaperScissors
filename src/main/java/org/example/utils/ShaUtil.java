package org.example.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class ShaUtil {

    public static String generateAndGetKey() {
        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException exception) {
            System.err.println("Key generation failed");
            exception.printStackTrace();
        }
        byte[] values = new byte[16];
        if (random != null) {
            random.nextBytes(values);
        }
        return Hex.encodeHexString(values);
    }

    public static String getEncodedMove(String key, String move){
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            assert sha256_HMAC != null;
            sha256_HMAC.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] result = sha256_HMAC.doFinal(move.getBytes());
        return Hex.encodeHexString(result);
    }

}
