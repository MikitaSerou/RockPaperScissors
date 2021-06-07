package org.example.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MovesValidator {

    public static boolean checkMovesCount(String[] moves) {
        if (moves.length < 3) {
            System.out.println("---!!!---Not enough moves, please enter nol less than 3 unique program arguments." +
                    " For example: \"dagger bow sword\" or more---!!!---");
            return false;
        }
        if (moves.length % 2 == 0) {
            System.out.println("---!!!---Odd number of inputted moves, please enter not Odd quantity of unique moves." +
                    " For example: 3,5,7,9...---!!!---");
            return false;
        }
        return true;
    }

    public static boolean checkMovesUnique(String[] moves) {
        ArrayList<String> argsAsList = (new ArrayList<>(Arrays.asList(moves)));
        if (!argsAsList.stream().distinct().collect(Collectors.toList()).equals(argsAsList)) {
            System.out.println("---!!!---Entered moves is not unique, please input new program arguments.---!!!---");
            return false;
        }
        return true;
    }
}
