package org.example;

import org.example.utils.MovesValidator;
import org.example.utils.ShaUtil;


public class App {

    public static void main(String[] args) {
        if (MovesValidator.checkMovesCount(args) & MovesValidator.checkMovesUnique(args)) {
            try {
                Game game = new Game(args,ShaUtil.generateAndGetKey(), new UserTurnChecker(args));
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
