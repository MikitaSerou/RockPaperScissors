package org.example.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserMenuTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String[] moves = new String[]{"rock", "paper", "scissors", "lizard", "Spock"};
    private final UserMenu menu = new UserMenu(moves);
    BufferedReader readerMock = mock(BufferedReader.class);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    public void printMenuTest() {
        menu.printMenu();
        assertEquals("Available moves: \n" +
                        "1 - rock\n" +
                        "2 - paper\n" +
                        "3 - scissors\n" +
                        "4 - lizard\n" +
                        "5 - Spock\n" +
                        "\"0\" - exit\n" +
                        "Enter your move:",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void getNullWhenInput0Test() throws IOException {
        UserMenu menu = new UserMenu(moves);
        when(readerMock.readLine()).thenReturn("0");
        assertEquals(null, menu.getUserChoiceIndex(readerMock));
    }

    @Test
    void getFirstElementTest() throws IOException {
        UserMenu menu = new UserMenu(moves);
        when(readerMock.readLine()).thenReturn("1");
        assertEquals(0, menu.getUserChoiceIndex(readerMock));
    }

    @Test
    void getMaxElementTest() throws IOException {
        UserMenu menu = new UserMenu(moves);
        when(readerMock.readLine()).thenReturn(String.valueOf(moves.length));
        assertEquals(moves.length - 1, menu.getUserChoiceIndex(readerMock));
    }

    @Test
    void f() throws IOException {
//        UserMenu menu = new UserMenu(moves); //TODO разобраться
//        when(readerMock.readLine()).thenReturn("dsf");
//        //menu.getUserChoiceIndex(readerMock);
//        when(readerMock.readLine()).thenReturn("0");
//        menu.getUserChoiceIndex(readerMock);
//        assertEquals(moves.length-1, menu.getUserChoiceIndex(readerMock));
    }

    @Test
    void input0Test() throws IOException {
        when(readerMock.readLine()).thenReturn("0");
        assertEquals("0", menu.readUserInput(readerMock));
    }

    @Test
    void inputGibberishTest() throws IOException {
        when(readerMock.readLine()).thenReturn("dasfww");
        assertEquals("dasfww", menu.readUserInput(readerMock));
    }
}