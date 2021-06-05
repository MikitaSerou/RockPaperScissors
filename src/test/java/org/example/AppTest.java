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


//    @Test
//    public void emptyKeyTest() {
//        Game dictMock = mock(Game.class);
//        doThrow(IllegalArgumentException.class).when(dictMock).runTheGame();
//        assertThrows(IllegalArgumentException.class,() -> {App.main(oddArgs);
//        });
//       // dictMock.runTheGame();
//        }

//    @Test
//    public void validArgsInitializationTest() {
//        App.main(new String[]{"Rock", "Paper", "Scissors"});
//        BufferedReader dictMock = mock(BufferedReader.class);
//
//        try {
//            when(dictMock.readLine()).thenReturn("1");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // dictMock.runTheGame();
//        assertEquals("",
//                outputStreamCaptor.toString().trim());
//        assertEquals("kek",
//                outputStreamCaptor.toString().trim());
//    }

    @Test
    public void notUniqueArgsInitializationTest() {
        App.main(notUniqueArgs);
        assertEquals("Entered moves is not unique, please input new program arguments.\n" +
                        "Exit...",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void oddArgsInitializationTest() {
        App.main(oddArgs);
        assertEquals("Odd number of inputted moves, please enter not Odd quantity of unique moves." +
                        " For example: 3,5,7,9...\nExit...",
                outputStreamCaptor.toString().trim());
    }
}

