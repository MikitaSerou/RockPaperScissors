package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
            instance.printAvailableMoves();
        }
        return instance;
    }

    public void printAvailableMoves() {
        System.out.println("Available moves: ");
        for (int i = 1; i <= moves.length; i++) {
            System.out.println(i + " - " + moves[i - 1]);
        }
        System.out.println("Enter your move: ");
    }
}
