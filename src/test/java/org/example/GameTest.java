package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameTest {

    private final String[] moves = {"rock", "paper", "scissors", "lizard", "Spock"};
    private final String shaKey = "0f156beae6ac2d949c0ee52058c5a8b9";
    private final UserTurnChecker userTurnChecker = new UserTurnChecker(moves);
    private final Game game = new Game(moves,shaKey,userTurnChecker);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    void runTheGame() {
    }

    @Test
    void doPlayerMove() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        Game gameWithMock = new Game(moves,shaKey,userTurnCheckerMock);

       when(userTurnCheckerMock.readUserInput()).thenReturn("0");
        gameWithMock.doPlayerMove();
    //    gameSpy.runTheGame();
        assertEquals("Exit...", outputStreamCaptor.toString().trim());
    }

    @Test
    void printMenuTest() {
     game.printMenu();
        assertEquals("Available moves: \n" +
                "1 - rock\n" +
                "2 - paper\n" +
                "3 - scissors\n" +
                "4 - lizard\n" +
                "5 - Spock\n" +
                "\"0\" - exit\n" +
                "Enter your move:", outputStreamCaptor.toString().trim());
    }

    @Test
    void getMoves() {
    }

    @Test
    void printExitMessage() {
    }
}