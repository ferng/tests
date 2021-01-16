package com.thecrunchycorner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AppTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void fullProcessing() {
        assertEquals(1, 1);
        String[] args = {""};
        App.main(args);
        assertEquals("[5, 1, 3]", outputStreamCaptor.toString().trim());
    }
}
