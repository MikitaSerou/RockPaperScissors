package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {

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

    String[] oddArgs = new String[]{"paper", "scissors", "lizard", "Spock"};
    String[] notUniqueArgs = new String[]{"paper", "scissors", "lizard", "Spock", "paper"};
    String[] notUniqueOddArgs = new String[]{"paper", "scissors", "lizard", "paper" };

//    @Test
//    public void notUniqueArgsInitializationTest() {
//        App.main(notUniqueArgs);
//        assertEquals("Entered moves is not unique, please input new program arguments.\n" +
//                        "Exit...",
//                outputStreamCaptor.toString().trim());
//    }
//
//    @Test
//    public void oddArgsInitializationTest() {
//        App.main(oddArgs);
//        assertEquals("Odd number of inputted moves, please enter not Odd quantity of unique moves." +
//                        " For example: 3,5,7,9...\nExit...",
//                outputStreamCaptor.toString().trim());
//    }
//    @Test
//    public void notUniqueOddArgsInitializationTest() {
//        App.main(notUniqueOddArgs);
//        assertEquals("""
//                        Odd number of inputted moves, please enter not Odd quantity of unique moves. For example: 3,5,7,9...
//                        Entered moves is not unique, please input new program arguments.
//                        Exit...""",
//                outputStreamCaptor.toString().trim());
//    }
}

