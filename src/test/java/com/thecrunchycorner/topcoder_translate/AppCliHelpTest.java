package com.thecrunchycorner.topcoder_translate;

import static com.thecrunchycorner.topcoder_translate.TestHelper.getMsg;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppCliHelpTest {
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldDisplayInvalidOptionAndHelpWithSingleInvalidOption() {
        String[] args = {"-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" + getMsg(), out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shouldDisplayInvalidOptionAndHelpWithEmbeddedInvalidOption() {
        String[] args = {"-s", "en", "-x"};
        App.main(args);
        assertEquals("Unknown parameter: [-x]\n" + getMsg(), out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shouldDisplayHelpWithEmbeddedInvalidOptionWithHelpOption() {
        String[] args = {"-s", "en", "-h"};
        App.main(args);

        assertEquals(getMsg(), out.toString());
        assertEquals("", err.toString());
    }
}
