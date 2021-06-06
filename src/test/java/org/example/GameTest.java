package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameTest {

    private final String[] moves = {"rock", "paper", "scissors", "lizard", "Spock"};
    private final String shaKey = "0f156beae6ac2d949c0ee52058c5a8b9";
    private final UserTurnChecker userTurnChecker = new UserTurnChecker(moves);
    private final Game game = new Game(moves, shaKey, userTurnChecker);

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
    void doPlayerExitChoice() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn("0");
        gameWithMock.doPlayerMove();
        assertTrue(gameWithMock.isUserCompleteTurn());
        assertNull(gameWithMock.getUserMoveIndex());
    }

    @Test
    void doPlayer1Move() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        String userMockInput = "1";
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn(userMockInput);
        when(userTurnCheckerMock.getUserChoiceIndex(userMockInput)).thenReturn(0);
        Integer index = userTurnCheckerMock.getUserChoiceIndex(userMockInput);
        when(userTurnCheckerMock.isAvailableIndex(index)).thenReturn(true);

        gameWithMock.doPlayerMove();
        assertEquals(0,gameWithMock.getUserMoveIndex());
        assertTrue(gameWithMock.isUserCompleteTurn());
    }

    @Test
    void doPlayerMaxMove() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        String userMockInput = String.valueOf(moves.length);
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn(userMockInput);
        when(userTurnCheckerMock.getUserChoiceIndex(userMockInput)).thenReturn(moves.length-1);
        Integer index = userTurnCheckerMock.getUserChoiceIndex(userMockInput);
        when(userTurnCheckerMock.isAvailableIndex(index)).thenReturn(true);

        gameWithMock.doPlayerMove();
        assertEquals(moves.length-1,gameWithMock.getUserMoveIndex());
        assertTrue(gameWithMock.isUserCompleteTurn());
    }

    @Test
    void doPlayerRandomMove() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        Integer randomIndex = new Random().nextInt(moves.length);
        String userMockInput = String.valueOf(randomIndex+1);
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn(userMockInput);
        when(userTurnCheckerMock.getUserChoiceIndex(userMockInput)).thenReturn(randomIndex);
        when(userTurnCheckerMock.isAvailableIndex(randomIndex)).thenReturn(true);

        gameWithMock.doPlayerMove();
        assertEquals(randomIndex,gameWithMock.getUserMoveIndex());
        assertTrue(gameWithMock.isUserCompleteTurn());
    }

    @Test
    void printMenuTest() {
        game.printMenu();
        assertEquals("""
                Available moves:\s
                1 - rock
                2 - paper
                3 - scissors
                4 - lizard
                5 - Spock
                0 - exit
                Enter your move:""", outputStreamCaptor.toString().trim());
    }

    @Test
    void getMoves() {
    }

    @Test
    void printExitMessage() {
    }
}