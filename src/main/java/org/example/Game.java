package org.example;

import org.example.utils.ShaUtil;

import java.util.Random;

public class Game {

    private final String[] moves;
    private final String shaKey;
    private final UserTurnChecker userTurnChecker;
    private Integer userMoveIndex;
    private int computerMoveIndex;
    private boolean userCompleteTurn;

    public Game(String[] moves, String shaKey, UserTurnChecker userTurnChecker) {
        this.moves = moves;
        this.shaKey = shaKey;
        this.userTurnChecker = userTurnChecker;
    }

    public void runTheGame() {
        doComputerMove();
        while (!userCompleteTurn) {
            doPlayerMove();
        }
        if (userMoveIndex != null) {
            showResultInfo();
        }
        printExitMessage();
    }

    public void doPlayerMove() {
        printMenu();
        String userInput = userTurnChecker.readUserInput();
        if (userInput.equals("0")) {
            userCompleteTurn = true;
            return;
        }
        userMoveIndex = userTurnChecker.getUserChoiceIndex(userInput);
        if (userTurnChecker.isAvailableIndex(userMoveIndex)) {
            System.out.println("Your move: " + moves[userMoveIndex]);
            userCompleteTurn = true;
        }
    }

    private void doComputerMove() {
        computerMoveIndex = new Random().nextInt(moves.length);
        System.out.println("HMAC: " + ShaUtil.getEncodedMove(shaKey, moves[computerMoveIndex]).toUpperCase());
    }

    public void printMenu() {
        System.out.println("Available moves: ");
        for (int i = 1; i <= moves.length; i++) {
            System.out.println(i + " - " + moves[i - 1]);
        }
        System.out.println("0 - exit");
        System.out.println("Enter your move: ");
    }

    private void showResultInfo() {
        System.out.println("Computer move: " + moves[computerMoveIndex]);
        checkWinner(userMoveIndex, computerMoveIndex);
        System.out.println("HMAC key: " + shaKey.toUpperCase());
    }

    private void checkWinner(int playerMoveIndex, int computerMoveIndex) {
        int countOfPossibleWinners = (moves.length - 1) / 2;
        if (playerMoveIndex == computerMoveIndex) {
            System.out.println("Spare");
            return;
        }
        if (playerMoveIndex - computerMoveIndex > countOfPossibleWinners
                || playerMoveIndex - computerMoveIndex < 0
                && playerMoveIndex - computerMoveIndex >= -countOfPossibleWinners) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Computer wins");
        }
    }

    public void setUserMoveIndex(Integer userMoveIndex) {
        this.userMoveIndex = userMoveIndex;
    }

    public void printExitMessage() {
        System.out.println("Exit...");
    }

    public String[] getMoves() {
        return moves;
    }

    public String getShaKey() {
        return shaKey;
    }

    public UserTurnChecker getUserTurnChecker() {
        return userTurnChecker;
    }

    public Integer getUserMoveIndex() {
        return userMoveIndex;
    }

    public int getComputerMoveIndex() {
        return computerMoveIndex;
    }

    public boolean isUserCompleteTurn() {
        return userCompleteTurn;
    }
}
