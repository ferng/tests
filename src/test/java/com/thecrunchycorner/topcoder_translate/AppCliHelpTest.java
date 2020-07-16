package com.thecrunchycorner.topcoder_translate;

import static com.thecrunchycorner.topcoder_translate.TestHelper.getMsg;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.thecrunchycorner.topcoder_translate.file_processor.InputFile;
import com.thecrunchycorner.topcoder_translate.file_processor.OutputFile;
import com.thecrunchycorner.topcoder_translate.services.Translation;
import com.thecrunchycorner.topcoder_translate.services.response.Sentences;
import com.thecrunchycorner.topcoder_translate.services.response.TranslationText;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.thecrunchycorner.topcoder_translate.*")
public class AppCliHelpTest {
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUpStreams() throws IOException {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
        mockStatic(InputFile.class);
        when(InputFile.getSourceText(any())).thenReturn("source text");
        mockStatic(Translation.class);
        when(Translation.translate(anyString(), anyString(), anyString(), anyString())).thenReturn("translated text");
        mockStatic(OutputFile.class);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldDisplayInvalidOptionAndHelpWithSingleInvalidOption() {
        exit.expectSystemExitWithStatus(0);
        String[] args = {"-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" + getMsg() + "Translation process complete\n",
                out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void shouldDisplayInvalidOptionAndHelpWithEmbeddedInvalidOption() {
        exit.expectSystemExitWithStatus(0);
        String[] args = {"-s", "en", "-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" + getMsg() + "Translation process complete\n", out.toString());
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
