package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.ShaUtil;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Slf4j
public class ComputerPlayer {



    public String getMove(String[] moves){
        Random random = new Random();
        String randomMove = moves[random.nextInt(moves.length)];
        System.out.println("Computer move: " + randomMove);
        try {
            System.out.println(ShaUtil.getEncodedMove(randomMove));
        } catch (NoSuchAlgorithmException | InvalidKeyException exception) {
            log.error("Exception in SHA algorithm", exception);
        }
return randomMove;
    }

}
