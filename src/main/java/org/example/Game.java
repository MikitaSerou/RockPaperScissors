package org.example;

import org.example.utils.ShaUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {

    private static Game instance;
    private final String[] moves;
    private final String shaKey;

    private Game(String[] moves) {
        this.moves = moves;
        shaKey = ShaUtil.generateAndGetKey().toUpperCase();
    }

    public static Game getInstance(String[] moves) {
        if (instance == null) {
            instance = new Game(moves);
        }
        return instance;
    }

    public void runTheGame() {
        int computerMoveIndex = new Random().nextInt(moves.length);
        System.out.println("HMAC: " + ShaUtil.getEncodedMove(shaKey, moves[computerMoveIndex]).toUpperCase());
        printAvailableMoves();
        System.out.println("Enter your move: ");
        Integer playerMoveIndex = getPlayerMoveIndex();
        if (playerMoveIndex == null) {
            System.out.println("Exit... ");
            return;
        }
        System.out.println("Computer move: " + moves[computerMoveIndex]);
        checkWinner(playerMoveIndex, computerMoveIndex);
        System.out.println("HMAC key: " + shaKey);
    }

    private void printAvailableMoves() {
        System.out.println("Available moves: ");
        for (int i = 1; i <= moves.length; i++) {
            System.out.println(i + " - " + moves[i - 1]);
        }
        System.out.println("0 - exit");
    }

    private Integer getPlayerMoveIndex() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int indexOfPlayerMove;
        try {
            String playerInput = reader.readLine();
            if (playerInput.equals("0")) {
                return null;
            }
            indexOfPlayerMove = Integer.parseInt(playerInput) - 1;
            System.out.println("Your move: " + moves[indexOfPlayerMove]);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return getPlayerMoveIndex();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException indexException) {
            System.err.println("Not available index of the move, please enter correct index from the list of " +
                    "available moves. Available only numbers in moves count length: \"1-" + moves.length + "\"" +
                    ", or \"0\" for exit from program.");
            return getPlayerMoveIndex();
        }
        return indexOfPlayerMove;
    }

    private void checkWinner(int playerMoveIndex, int computerMoveIndex) {
        int countOfPossibleWinners = (moves.length - 1) / 2;
        if (playerMoveIndex == computerMoveIndex) {
            System.out.println("Spare");
            return;
        }
        if (playerMoveIndex - computerMoveIndex < -countOfPossibleWinners ||
                playerMoveIndex - computerMoveIndex > 0 && playerMoveIndex - computerMoveIndex <= countOfPossibleWinners) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Computer wins");
        }
    }
}
