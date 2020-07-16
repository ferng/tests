package com.thecrunchycorner.topcoder_translate.arguments;

import java.io.File;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "translate",
        mixinStandardHelpOptions = true,
        description = "Translate text in one file to another language, then write the translation" +
                " to another language",
        version = "Translate 0.0.1")
public class OptParser {
    @Option(names = {"-s", "--source"}, description = "source language")
    private String source = "en";

    @Option(names = {"-t", "--target"}, description = "target language")
    private String target = "es";

    @Option(names = { "-i", "--input" }, description = "file with text to be translated")
    private File input = new File("source.txt");

    @Option(names = { "-o", "--output" }, description = "file to save translated text to")
    private File output = new File("target.txt");

    @Option(names = { "-l", "--languages" }, description = "list available languages")
    private boolean isLanguageHelpRequested = false;

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public File getInput() {
        return input;
    }

    public File getOutput() {
        return output;
    }

    public boolean isLanguageHelpRequested() {
        return isLanguageHelpRequested;
    }
}
