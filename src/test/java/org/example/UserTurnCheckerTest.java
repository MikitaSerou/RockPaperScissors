package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserTurnCheckerTest {

    BufferedReader readerMock = mock(BufferedReader.class);
    private final String[] moves = {"rock", "paper", "scissors", "lizard", "Spock"};
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
    void readUserInput0Test() throws IOException {
        UserTurnChecker checker = new UserTurnChecker(moves, readerMock);
        when(readerMock.readLine()).thenReturn("0");
        assertEquals("0", checker.readUserInput());
    }

    @Test
    void readUserInputOneOfMovesListNumbersTest() throws IOException {
        UserTurnChecker checker = new UserTurnChecker(moves, readerMock);
        String randomNumberInput =String.valueOf(new Random().nextInt(moves.length+1));
        when(readerMock.readLine()).thenReturn(randomNumberInput);
        assertEquals(randomNumberInput, checker.readUserInput());
    }

    @Test
    void readUserInputGibberishTest() throws IOException {
        UserTurnChecker checker = new UserTurnChecker(moves, readerMock);
        when(readerMock.readLine()).thenReturn("sadfsf");
        assertEquals("sadfsf", checker.readUserInput());
    }

    @Test
    void inputExceptionTest() throws IOException {
        UserTurnChecker checker = new UserTurnChecker(moves, readerMock);
        when(readerMock.readLine()).thenThrow(IOException.class);
        assertEquals("0", checker.readUserInput());
        assertEquals("Enter the number corresponding to the desired move.\n" +
                "Input/Output exception", outputStreamCaptor.toString().trim());
    }

    @Test
    void getNullIndexWhenUserInputIsGibberish() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertNull(checker.getUserChoiceIndex("sgd"));
    }

    @Test
    void get0IndexWhenUserInputIs1() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertEquals(0, checker.getUserChoiceIndex("1"));
    }

    @Test
    void get0IndexWhenUserInputIsArrayLenght() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertEquals(moves.length-1, checker.getUserChoiceIndex(String.valueOf(moves.length)));
    }

    @Test
    void checkNullIndexAsAvailableTest() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertFalse(checker.isAvailableIndex(null));
    }

    @Test
    void checkMaxArrayIndexAsAvailableTest() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertTrue(checker.isAvailableIndex(moves.length-1));
    }

    @Test
    void check0AsAvailableIndexTest() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertTrue(checker.isAvailableIndex(moves.length-1));
    }

    @Test
    void checkRandomArrayIndexAsAvailableTest() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertTrue(checker.isAvailableIndex(new Random().nextInt(moves.length)));
    }

    @Test
    void checkMiddleArrayIndexAsAvailableTest() {
        UserTurnChecker checker = new UserTurnChecker(moves);
        assertTrue(checker.isAvailableIndex(moves.length/2));
    }
}