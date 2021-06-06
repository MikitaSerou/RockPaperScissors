package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserTurnChecker {
    private final String[] moves;

    private final  BufferedReader reader;

    public UserTurnChecker(String[] moves) {
        this.moves = moves;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public UserTurnChecker(String[] moves, BufferedReader reader) {
        this.moves = moves;
        this.reader = reader;
    }

    public String readUserInput() {
        String inputString = "0";
        try {
            inputString = reader.readLine();
        } catch (IOException e) {
            printInvalidInputMessage();
            System.out.println("Input/Output exception");
            e.printStackTrace();
        }
        return inputString;
    }

    public Integer getUserChoiceIndex(String s) {
        try {
            return Integer.parseInt(s) - 1;
        } catch (NumberFormatException e) {
            printInvalidInputMessage();
        }
        return null;
    }

    public boolean isAvailableIndex(Integer userInputIndex) {
        if (userInputIndex != null &&
                userInputIndex <= moves.length && userInputIndex >= 0) {
            return true;
        } else {
            printOutOfBoundsInputMessage();
            return false;
        }
    }

    private void printInvalidInputMessage() {
        System.out.println("Enter the number corresponding to the desired move.");
    }

    private void printOutOfBoundsInputMessage() {
        System.out.println("Available moves (1-" + moves.length + ")." +
                " Please enter only numbers from below list, or \"0\" for exit from program.");
    }
}
