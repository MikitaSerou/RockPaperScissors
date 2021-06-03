package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
public class ShaUtil {

    public static String generateAndGetKey(){ //TODO норм вроде
        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException exception) {
            log.error("No Algorithm", exception);
        }
        byte[] values = new byte[16];
        if (random != null) {
            random.nextBytes(values);
        }
        return Hex.encodeHexString(values);
    }

    public static String getEncodedMove(String move) throws NoSuchAlgorithmException, InvalidKeyException {
        String key = generateAndGetKey();
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        sha256_HMAC.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        byte[] result = sha256_HMAC.doFinal(move.getBytes());
        return Hex.encodeHexString(result);
    }

}
