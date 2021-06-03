package org.example;


import org.example.utils.MovesValidator;

public class App {
    public static void main(String[] args) {

        MovesValidator argsValidator = MovesValidator.getInstance(args);


        System.err.println(argsValidator.checkMoves()); //TODO убрать
        if (argsValidator.checkMoves()){
            Game game = Game.getInstance(args);
        }
    }
}
