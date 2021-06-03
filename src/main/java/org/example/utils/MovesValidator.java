package org.example.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public final class MovesValidator {

    private static MovesValidator instance;
    private final String[] moves;


    private MovesValidator(String[] moves) {
        this.moves = moves;
    }

    public static MovesValidator getInstance(String[] moves) {
        if (instance == null) {
            instance = new MovesValidator(moves);
        }
        return instance;
    }

    public boolean checkMoves() {
        if (moves.length < 3) {
            System.out.println("Not enough moves, please enter more than 3.");
            return false;
        }
        if (moves.length % 2 == 0) {
            System.out.println("Odd number of inputted moves, please enter not Odd quantity of moves.");
            return false;
        }
        return checkMovesUnique();
    }

    private boolean checkMovesUnique() {
        log.info("Check moves for unique...");
        ArrayList<String> argsAsList = (new ArrayList<>(Arrays.asList(moves)));
        if (!argsAsList.stream().distinct().collect(Collectors.toList()).equals(argsAsList)) {
            System.out.println("Entered moves is not unique, please input new args.");
            return false;
        }
        return true;
    }
}
