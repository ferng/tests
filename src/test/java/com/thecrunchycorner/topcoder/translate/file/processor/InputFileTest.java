package com.thecrunchycorner.topcoder.translate.file.processor;

import java.io.StringReader;
import org.junit.Assert;
import org.junit.Test;

public class InputFileTest {

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
