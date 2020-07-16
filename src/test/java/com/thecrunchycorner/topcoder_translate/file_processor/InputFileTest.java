package com.thecrunchycorner.topcoder_translate.file_processor;

import java.io.StringReader;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InputFileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldReturnFormattedTextReadFromFile() {
        String sourceText = "once upon. a time there. were three.\n";
        StringReader source = new StringReader(sourceText);

        String expected = "once upon. a time there. were three.";

        String actual = InputFile.getSourceText(source);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldEmptyStringIfEmptyFile() {
        String sourceText = "";
        StringReader source = new StringReader(sourceText);

        String expected = "";

        String actual = InputFile.getSourceText(source);

        Assert.assertEquals(expected, actual);
    }

}
