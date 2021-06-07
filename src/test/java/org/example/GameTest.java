package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {

    private final String[] moves = {"rock", "paper", "scissors", "lizard", "Spock"};
    private final String shaKey = "0f156beae6ac2d949c0ee52058c5a8b9";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    void rUnTheGameWhenPlayerExitChoice() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn("0");
        gameWithMock.runTheGame();
        assertTrue(gameWithMock.isUserCompleteTurn());
        assertNull(gameWithMock.getUserMoveIndex());
    }

    @Test
    void rUnTheGameWhenPlayerDo1Move() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        String userMockInput = "1";
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn(userMockInput);
        when(userTurnCheckerMock.getUserChoiceIndex(userMockInput)).thenReturn(0);
        Integer index = userTurnCheckerMock.getUserChoiceIndex(userMockInput);
        when(userTurnCheckerMock.isAvailableIndex(index)).thenReturn(true);

        gameWithMock.runTheGame();
        assertEquals(0,gameWithMock.getUserMoveIndex());
        assertTrue(gameWithMock.isUserCompleteTurn());
    }

    @Test
    void rUnTheGameWhenPlayerDoMaxMove() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        String userMockInput = String.valueOf(moves.length);
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn(userMockInput);
        when(userTurnCheckerMock.getUserChoiceIndex(userMockInput)).thenReturn(moves.length-1);
        Integer index = userTurnCheckerMock.getUserChoiceIndex(userMockInput);
        when(userTurnCheckerMock.isAvailableIndex(index)).thenReturn(true);

        gameWithMock.runTheGame();
        assertEquals(moves.length-1,gameWithMock.getUserMoveIndex());
        assertTrue(gameWithMock.isUserCompleteTurn());
    }

    @Test
    void rUnTheGameWhenPlayerDoRandomMove() {
        UserTurnChecker userTurnCheckerMock = mock(UserTurnChecker.class);
        Integer randomIndex = new Random().nextInt(moves.length);
        String userMockInput = String.valueOf(randomIndex+1);
        Game gameWithMock = new Game(moves, shaKey, userTurnCheckerMock);
        when(userTurnCheckerMock.readUserInput()).thenReturn(userMockInput);
        when(userTurnCheckerMock.getUserChoiceIndex(userMockInput)).thenReturn(randomIndex);
        when(userTurnCheckerMock.isAvailableIndex(randomIndex)).thenReturn(true);

        gameWithMock.runTheGame();
        assertEquals(randomIndex,gameWithMock.getUserMoveIndex());
        assertTrue(gameWithMock.isUserCompleteTurn());
    }

}