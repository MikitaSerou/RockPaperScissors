package org.example;

import org.example.utils.MovesValidator;



public class App {

    public static void main(String[] args) {
        if (MovesValidator.checkMovesCount(args) & MovesValidator.checkMovesUnique(args)) {
            Game game = Game.getInstance(args);
            try {
                game.runTheGame();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                System.out.println("Key init error...");
            }
        }else {
            System.out.println("Exit...");
        }
    }
}
