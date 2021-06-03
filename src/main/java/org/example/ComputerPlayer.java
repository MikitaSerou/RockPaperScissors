package org.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ComputerPlayer {

    public Integer getMoveIndex(String[] moves) {
        Integer randomMoveIndex = new Random().nextInt(moves.length);
        String randomMove = moves[randomMoveIndex];
        return randomMoveIndex;
    }

}
