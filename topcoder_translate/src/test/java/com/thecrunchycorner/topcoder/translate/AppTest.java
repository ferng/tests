package com.thecrunchycorner.topcoder.translate;

import static com.thecrunchycorner.topcoder.translate.TestHelper.getMsg;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.thecrunchycorner.topcoder.translate.file.processor.InputFile;
import com.thecrunchycorner.topcoder.translate.file.processor.OutputFile;
import com.thecrunchycorner.topcoder.translate.services.TranslationService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.thecrunchycorner.topcoder.translate.*")
public class AppTest {
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;
    final ByteArrayOutputStream out = new ByteArrayOutputStream();
    final ByteArrayOutputStream err = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() throws IOException {
        out.reset();
        err.reset();
        System.setOut(new PrintStream(out, true, "UTF-8"));
        System.setErr(new PrintStream(err, true, "UTF-8"));
        mockStatic(InputFile.class);
        when(InputFile.getSourceText(any())).thenReturn("source text");
        mockStatic(TranslationService.class);
        when(TranslationService.translate(anyString(), anyString(), anyString(), anyString())).thenReturn("translated text");
        mockStatic(OutputFile.class);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void shouldDisplayInvalidOptionAndHelpWithSingleInvalidOption() throws Exception {
        String[] args = {"-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" + getMsg() + "Translation process complete\n",
                out.toString("UTF-8"));
        assertEquals("", err.toString("UTF-8"));
    }

    @Test
    public void shouldDisplayInvalidOptionAndHelpWithEmbeddedInvalidOption() throws Exception {
        String[] args = {"-s", "en", "-x"};
        App.main(args);

        assertEquals("Unknown parameter: [-x]\n" + getMsg() + "Translation process complete\n",
                out.toString("UTF-8"));
        assertEquals("", err.toString("UTF-8"));
    }

    @Test
    public void shouldDisplayHelpSingleHelpOption() throws Exception {
        String[] args = {"-h"};
        App.main(args);

        assertEquals(getMsg(), out.toString("UTF-8"));
        assertEquals("", err.toString("UTF-8"));
    }

    @Test
    public void shouldDisplayHelpWithEmbeddedHelp() throws Exception {
        String[] args = {"-s", "en", "-h"};
        App.main(args);

        assertEquals(getMsg(), out.toString("UTF-8"));
        assertEquals("", err.toString("UTF-8"));
    }

    @Test
    public void shouldDisplayVersionNumber() throws Exception {
        String[] args = {"-V"};
        App.main(args);

        assertEquals("Translate 0.0.1\n", out.toString("UTF-8"));
        assertEquals("", err.toString("UTF-8"));
    }
}
