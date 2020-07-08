package com.thecrunchycorner.topcoder_translate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import picocli.CommandLine;

public class AppCliHelpTest
{
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
    public void shoouldDisplayInvalidOptionAndHelpWithSingleInvalidOption()
    {
        String[] args = {"-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" +
                "Usage: translate [-hV] [-i=<input>] [-o=<output>] [-s=<source>] [-t=<target>]\n" +
                "Translate text in one file to another language, then write the translation to\n" +
                "another language\n" +
                "  -h, --help              Show this help message and exit.\n" +
                "  -i, --input=<input>     file with text to be translated\n" +
                "  -o, --output=<output>   file to save translated text to\n" +
                "  -s, --source=<source>   source language\n" +
                "  -t, --target=<target>   target language\n" +
                "  -V, --version           Print version information and exit.\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shoouldDisplayInvalidOptionAndHelpWithEmbeddedInvalidOption()
    {
        String[] args = {"-s", "en", "-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" +
                "Usage: translate [-hV] [-i=<input>] [-o=<output>] [-s=<source>] [-t=<target>]\n" +
                "Translate text in one file to another language, then write the translation to\n" +
                "another language\n" +
                "  -h, --help              Show this help message and exit.\n" +
                "  -i, --input=<input>     file with text to be translated\n" +
                "  -o, --output=<output>   file to save translated text to\n" +
                "  -s, --source=<source>   source language\n" +
                "  -t, --target=<target>   target language\n" +
                "  -V, --version           Print version information and exit.\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shoouldDisplayHelpWithEmbeddedInvalidOptionWithHelpOption()
    {
        String[] args = {"-s", "en", "-h"};
        App.main(args);

        assertEquals(
                "Usage: translate [-hV] [-i=<input>] [-o=<output>] [-s=<source>] [-t=<target>]\n" +
                "Translate text in one file to another language, then write the translation to\n" +
                "another language\n" +
                "  -h, --help              Show this help message and exit.\n" +
                "  -i, --input=<input>     file with text to be translated\n" +
                "  -o, --output=<output>   file to save translated text to\n" +
                "  -s, --source=<source>   source language\n" +
                "  -t, --target=<target>   target language\n" +
                "  -V, --version           Print version information and exit.\n", out.toString());
        assertEquals("", err.toString());
    }
}
