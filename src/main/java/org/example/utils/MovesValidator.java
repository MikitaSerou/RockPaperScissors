package org.example.utils;


import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class MovesValidator {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MovesValidator.class);
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
        log.info("Check program arguments...");
        if (moves.length < 3) {
            log.error("Not enough arguments!");
            System.out.println("Not enough moves, please enter nol less than 3 program arguments." +
                    " For example: (dagger bow sword) or more.");
            return false;
        }
        if (moves.length % 2 == 0) {
            log.error("Not odd count of arguments!");
            System.out.println("Odd number of inputted moves, please enter not Odd quantity of moves." +
                    " For example: 3,5,7,9...");
            return false;
        }
        return checkMovesUnique();
    }

    private boolean checkMovesUnique() {
        log.info("Check moves for unique...");
        ArrayList<String> argsAsList = (new ArrayList<>(Arrays.asList(moves)));
        if (!argsAsList.stream().distinct().collect(Collectors.toList()).equals(argsAsList)) {
            System.out.println("Entered moves is not unique, please input new program arguments.");
            return false;
        }
        return true;
    }
}
