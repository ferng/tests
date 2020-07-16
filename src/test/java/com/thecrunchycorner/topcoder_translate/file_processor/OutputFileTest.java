package com.thecrunchycorner.topcoder_translate.file_processor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.prefs.BackingStoreException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OutputFileTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


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
