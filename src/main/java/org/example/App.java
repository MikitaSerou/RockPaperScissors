package org.example;

import org.example.utils.MovesValidator;


public class App {

    public static void main(String[] args) {
        if (MovesValidator.checkMoves(args)) {
            Game game = Game.getInstance(args);
            game.runTheGame();
        }else {
            System.err.println("Exit...");
        }
    }
}
