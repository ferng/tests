package com.thecrunchycorner.topcoder_translate;

import static com.thecrunchycorner.topcoder_translate.TestHelper.getMsg;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppCliLanguageTest
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
    public void shouldDisplayLanguageHelpwith_l_option()
    {
        String[] args = {"-l"};
        App.main(args);

        assertEquals("Available languages: [en, english, is, icelandic, ja, japanese, es, spanish]\n" + getMsg(), out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shouldDisplayLanguageHelpwith_languages_option()
    {
        String[] args = {"--languages"};
        App.main(args);

        assertEquals("Available languages: [en, english, is, icelandic, ja, japanese, es, spanish]\n" + getMsg(), out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shouldDisplayLanguageHelpwithInvalidSourceLanguage()
    {
        String[] args = {"-s kl"};
        App.main(args);

        assertEquals("Unknown source language[ kl ]\nAvailable languages: [en, english, is, " +
                "icelandic, ja, japanese, es, spanish]\n" + getMsg(), out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shouldDisplayLanguageHelpwithInvalidTargetLanguage()
    {
        String[] args = {"-t fj"};
        App.main(args);

        assertEquals("Unknown target language[ fj ]\nAvailable languages: [en, english, is, " +
                "icelandic, ja, japanese, es, spanish]\n" + getMsg(), out.toString());
        assertEquals("", err.toString());
    }
}
