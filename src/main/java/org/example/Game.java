package org.example;

import org.example.utils.ShaUtil;
import org.example.utils.UserMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {

    private static Game instance;
    private final String[] moves;
    private final String shaKey;
    private final UserMenu userMenu;
    private Integer userMoveIndex;
    private int computerMoveIndex;

    private Game(String[] moves) {
        this.moves = moves;
        shaKey = ShaUtil.generateAndGetKey()/*.toUpperCase()*/;
        userMenu = new UserMenu(moves);
    }

    public static Game getInstance(String[] moves) {
        if (instance == null) {
            instance = new Game(moves);
        }
        return instance;
    }

    public void runTheGame() {
        doComputerMove();
        userMenu.printMenu();
        userMoveIndex = userMenu.getUserChoiceIndex(new BufferedReader(new InputStreamReader(System.in)));
        if (userMoveIndex == null) {
            exit();
        } else {
            System.out.println("Your move: " + moves[userMoveIndex]);
            showResultInfo();
        }
    }

    private void doComputerMove() {
        computerMoveIndex = new Random().nextInt(moves.length);
        System.out.println("HMAC: " + ShaUtil.getEncodedMove(shaKey, moves[computerMoveIndex]).toUpperCase());
    }

    private void showResultInfo() {
        System.out.println("Computer move: " + moves[computerMoveIndex]);
        checkWinner(userMoveIndex, computerMoveIndex);
        System.out.println("HMAC key: " + shaKey);
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

    public void exit() {
        System.out.println("Exit...");
    }
}
