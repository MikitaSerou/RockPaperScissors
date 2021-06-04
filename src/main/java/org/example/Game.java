package org.example;

import org.example.utils.ShaUtil;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Game {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Game.class);
    private static Game instance;
    private String[] moves;
    private final ComputerPlayer computerPlayer;
    private final String shaKey;

    private Game(String[] moves) {
        this.moves = moves;
        shaKey = ShaUtil.generateAndGetKey().toUpperCase();
        computerPlayer = new ComputerPlayer();
    }

    public static Game getInstance(String[] moves) {
        if (instance == null) {
            instance = new Game(moves);
        }
        return instance;
    }

    public void startTheGame() {
        int computerMoveIndex = computerPlayer.getMoveIndex(moves);
        try {
            System.out.println("HMAC: " + ShaUtil.getEncodedMove(shaKey, moves[computerMoveIndex]).toUpperCase());
        } catch (NoSuchAlgorithmException | InvalidKeyException exception) {
            exception.printStackTrace();
        }
        printAvailableMoves();
        System.out.println("Enter your move: ");
        Integer playerMoveIndex = getPlayerMoveIndex();
        if (playerMoveIndex == null){
            System.out.println("Exit... ");
            return;
        }
        System.out.println("Computer move: " + moves[computerMoveIndex]);
        checkWinner(playerMoveIndex, computerMoveIndex);
        System.out.println("HMAC key: " + shaKey);
    }

    public void printAvailableMoves() {
        System.out.println("Available moves: ");
        for (int i = 1; i <= moves.length; i++) {
            System.out.println(i + " - " + moves[i - 1]);
        }
        System.out.println("0 - exit");
    }

    public Integer getPlayerMoveIndex() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer indexOfPlayerMove = null;
        try {
            String playerInput = reader.readLine();
            if (playerInput.equals("0")) {
                return null;
            }
            System.out.println("Your move: " + moves[Integer.parseInt(playerInput) - 1]);
            indexOfPlayerMove = Integer.parseInt(playerInput) - 1;
        } catch (IOException ioException) {
            log.error("IO exception", ioException);
            return getPlayerMoveIndex();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException indexException) {
            log.error("Incorrect input", indexException);
            System.err.println("Not available index of the move," +
                    " please enter correct index from the list of available moves." +
                    " Available only numbers in moves count length: \"1-" + moves.length + "\"");
            return getPlayerMoveIndex();
        }
        //      log.info("Index of player input in args[] array: " + indexOfPlayerMove);
        return indexOfPlayerMove;
    }

    public void checkWinner(int playerMoveIndex, int computerMoveIndex){
        //TODO переименовать:
        int arrLengthHalf = (moves.length-1)/2;
        log.info(String.valueOf(arrLengthHalf));
        log.info("p: " +String.valueOf(playerMoveIndex));
        log.info("c: " +String.valueOf(computerMoveIndex));
        log.info(String.valueOf(playerMoveIndex - computerMoveIndex));
        if (playerMoveIndex == computerMoveIndex){
            System.out.println("Spare");
            return;
        }
        if(playerMoveIndex - computerMoveIndex < -arrLengthHalf || ((playerMoveIndex - computerMoveIndex) > 0 && (playerMoveIndex - computerMoveIndex) <= arrLengthHalf)){
            System.out.println("Player wins!");
        }else {
            System.out.println("Comp wins");
        }

    }

    public String[] getMoves() {
        return this.moves;
    }

    public ComputerPlayer getComputerPlayer() {
        return this.computerPlayer;
    }


    public String getShaKey() {
        return this.shaKey;
    }

    public void setMoves(String[] moves) {
        this.moves = moves;
    }

}
