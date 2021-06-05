package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class UserMenu {

    private final String[] moves;

    public UserMenu(String[] moves) {
        this.moves = moves;
    }

    public void printMenu() {
        System.out.println("Available moves: ");
        for (int i = 1; i <= moves.length; i++) {
            System.out.println(i + " - " + moves[i - 1]);
        }
        System.out.println("\"0\" - exit");
        System.out.println("Enter your move: ");
    }

    public Integer getUserChoiceIndex(BufferedReader reader) {
        String input = readUserInput(reader);
        if (input.equals("0")) {
            return null;
        }
        Integer userInputIndex = getCheckedInputAsNumber(input);
        if (userInputIndex == null) {
            printMenu();
            return getUserChoiceIndex(reader);
        }
        return userInputIndex - 1;
    }

    public String readUserInput(BufferedReader reader) {
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            readUserInput(reader);
        }
        return input;
    }

    public Integer getCheckedInputAsNumber(String input) {
        int userInputIndex;
        try {
            userInputIndex = Integer.parseInt(input);
        } catch (NumberFormatException indexException) {
            printInvalidInputMessage();
            return null;
        }
        return isAvailableMove(userInputIndex);
    }

    private Integer isAvailableMove(Integer userInputIndex) {
        if (userInputIndex <= moves.length && userInputIndex >= 0) {
            return userInputIndex;
        }
        printInvalidInputMessage();
        return null;
    }

    private void printInvalidInputMessage() {
        System.err.println("Not available index of the move, please enter correct index from the list of " +
                "available moves (1-" + moves.length + "). Available only numbers from above list" +
                ", or \"0\" for exit from program.");
    }
}
