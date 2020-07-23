package com.thecrunchycorner.topcoder.translate.file.processor;

import java.io.StringWriter;
import org.junit.Assert;
import org.junit.Test;

public class OutputFileTest {

    @Test
    public void shouldWriteFormattedTextToFile() {
        String sourceText = "once upon. a time there. were three.";
        StringWriter target = new StringWriter();

        String expected = "once upon. a time there. were three.";

        OutputFile.writeTargetText(target, sourceText);

        String actual = target.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateBlankFileWhenNoText() {
        String sourceText = "";
        StringWriter target = new StringWriter();

        String expected = "";

        OutputFile.writeTargetText(target, sourceText);

        String actual = target.toString();
        Assert.assertEquals(expected, actual);
    }
}
