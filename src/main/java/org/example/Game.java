package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Getter
@Setter
public class Game {

    private static Game instance;
    private String[] moves;

    public Game(String[] moves) {
        this.moves = moves;
    }

    public static Game getInstance(String[] moves) {
        if (instance == null) {
            instance = new Game(moves);
        }
        return instance;
    }

    public void startTheGame() {
        printAvailableMoves();
        System.out.println("Enter your move: ");


//        Random random = new SecureRandom();
//        byte[] key = new byte[16];
//        //random.nextBytes(key);
//        System.out.println(random);
        try {
            Encoder.generate();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //TODO app's move is here, before player move
        getPlayerMove();


    }


    public void printAvailableMoves() {
        System.out.println("Available moves: ");
        for (int i = 1; i <= moves.length; i++) {
            System.out.println(i + " - " + moves[i - 1]);
        }
        System.out.println("0  - exit");
    }

    public Integer getPlayerMove() {
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
            System.err.println("Input failed...");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException indexException) {
            log.error("Incorrect input", indexException);
            System.err.println("Not available index of the move," +
                    " please enter correct index from the list of available moves." +
                    " Available only numbers in moves count length: \"1-" + moves.length + "\"");
        }
        log.info("Index of player input in args[] array: " + indexOfPlayerMove);
        return indexOfPlayerMove;
    }
}
