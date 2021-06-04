package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UIUtil {

    private final String[] moves;

    public UIUtil(String[] moves) {
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
    
    public Integer getUserChoiceIndex() {
        printMenu();
        String input = readUserInput();
        Integer userInputIndex = getCheckedInputAsNumber(input);
        if (input.equals("0")) {
            return null;
        }
        if (userInputIndex == null){
           return getUserChoiceIndex();
        }
        return userInputIndex-1;
    }

    private String readUserInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            getUserChoiceIndex();
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

    public Integer isAvailableMove(Integer userInputIndex) {
        if (userInputIndex <= moves.length && userInputIndex >= 0){
            return userInputIndex;
        }
        printInvalidInputMessage();
        return null;
    }

    private void printInvalidInputMessage(){
        System.err.println("Not available index of the move, please enter correct index from the list of " +
                "available moves (1-"+ moves.length+"). Available only numbers from above list" +
                ", or \"0\" for exit from program.");
    }
}
