package org.example;

import org.slf4j.Logger;

import java.util.Random;

public class ComputerPlayer {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ComputerPlayer.class);

    public Integer getMoveIndex(String[] moves) {
        Integer randomMoveIndex = new Random().nextInt(moves.length);
        String randomMove = moves[randomMoveIndex];
        return randomMoveIndex;
    }

}
