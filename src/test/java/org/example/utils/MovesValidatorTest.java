package org.example.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class MovesValidatorTest {

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
    public void validMovesCountTest() {
        assertTrue(MovesValidator.checkMovesCount(new String[]{"rock", "paper", "scissors", "lizard", "Spock"}));
    }

    @Test
    public void oddMovesCountTest() {
        assertFalse(MovesValidator.checkMovesCount(new String[]{"rock", "paper", "scissors", "lizard"}));
        assertEquals("---!!!---Odd number of inputted moves, please enter not Odd quantity of unique moves." +
                " For example: 3,5,7,9...---!!!---", outputStreamCaptor.toString().trim());
    }

    @Test
    public void lessThen3MovesCountTest() {
        assertFalse(MovesValidator.checkMovesCount(new String[]{"rock", "paper"}));
        assertEquals("---!!!---Not enough moves, please enter nol less than 3 unique program arguments." +
                " For example: \"dagger bow sword\" or more---!!!---", outputStreamCaptor.toString().trim());
    }

    @Test
    public void emptyMovesArrayTest() {
        assertFalse(MovesValidator.checkMovesCount(new String[]{}));
        assertEquals("---!!!---Not enough moves, please enter nol less than 3 unique program arguments. " +
                "For example: \"dagger bow sword\" or more---!!!---", outputStreamCaptor.toString().trim());
    }

    @Test
    public void uniqueMovesTest() {
        assertTrue(MovesValidator.checkMovesUnique(new String[]{"A", "B", "C"}));
    }

    @Test
    public void notUniqueMovesTest() {
        assertFalse(MovesValidator.checkMovesUnique(new String[]{"A", "B", "C", "B", "D"}));
        assertEquals("---!!!---Entered moves is not unique, please input new program arguments.---!!!---",
                outputStreamCaptor.toString().trim());
    }
}
