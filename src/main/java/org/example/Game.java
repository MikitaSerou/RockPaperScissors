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
    }

    private void doPlayerMove() {
        printMenu();
        String userInput = userTurnChecker.readUserInput();
        Integer indexForCheck = userTurnChecker.getUserChoiceIndex(userInput);
        if (userInput.equals("0")) {
            endTheGame();
        } else if (userTurnChecker.isAvailableIndex(indexForCheck)) {
            setUserMoveIndex(indexForCheck);
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
        int i = playerMoveIndex - computerMoveIndex;
        if ( i < - countOfPossibleWinners
        || i > 0 & i<= countOfPossibleWinners) {
            System.out.println("You win!");
        } else {
            System.out.println("Computer wins");
        }
    }

    public void setUserMoveIndex(Integer userMoveIndex) {
        this.userMoveIndex = userMoveIndex;
        System.out.println("Your move: " + moves[userMoveIndex]);
        userCompleteTurn = true;
    }

    public void endTheGame() {
        System.out.println("Exit...");
        userCompleteTurn = true;
    }

    public Integer getUserMoveIndex() {
        return userMoveIndex;
    }

    public boolean isUserCompleteTurn() {
        return userCompleteTurn;
    }
}
