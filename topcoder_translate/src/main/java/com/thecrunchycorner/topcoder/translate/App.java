package com.thecrunchycorner.topcoder.translate;

import com.thecrunchycorner.topcoder.translate.arguments.LangParser;
import com.thecrunchycorner.topcoder.translate.arguments.OptParser;
import com.thecrunchycorner.topcoder.translate.arguments.ParserResult;
import com.thecrunchycorner.topcoder.translate.file.processor.InputFile;
import com.thecrunchycorner.topcoder.translate.file.processor.OutputFile;
import com.thecrunchycorner.topcoder.translate.services.TranslationService;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import picocli.CommandLine;

public class App {
    // Argument parser
    private static final String SECTION_KEY_HEADER_HEADING = "headerHeading";
    private static final OptParser OPT_PARSER = new OptParser();
    private static final CommandLine CMD = new CommandLine(OPT_PARSER);

    // Application
    private static final boolean SUCCESS = true;
    private static final boolean FAILURE = false;
    private static final ParserResult SUCCESS_RESULT = new ParserResult(SUCCESS, "");
    private static ParserResult result = SUCCESS_RESULT;

    // In production these would be in a config file or db somewhere
    private static final String LANGUAGE_HELP =
            "Available languages: " + LangParser.getLanguages().toString() + "\n";
    private static final String ENDPOINT = "https://translate.google.com/translate_a/single?";


    public static void main(String[] args) {
        CMD.getHelpSectionMap().remove(SECTION_KEY_HEADER_HEADING);
        CMD.setStopAtUnmatched(true);
        CMD.parseArgs(args);

        if (CMD.isUsageHelpRequested()) {
            CMD.usage(System.out);
        } else if (CMD.isVersionHelpRequested()) {
            CMD.printVersionHelp(System.out);
        } else if (OPT_PARSER.isLanguageHelpRequested()) {
            CMD.getHelpSectionMap().put(SECTION_KEY_HEADER_HEADING,
                    help -> help.createHeading(LANGUAGE_HELP));
            CMD.usage(System.out);
        } else {
            parseLanguageOptions();
            try {
                InputStreamReader reader =
                        new InputStreamReader(new FileInputStream(OPT_PARSER.getInput()),
                                StandardCharsets.UTF_8);
                String sourceText = InputFile.getSourceText(reader);
                reader.close();
                String targetText = TranslationService.translate(ENDPOINT, sourceText,
                        OPT_PARSER.getSource(), OPT_PARSER.getTarget());
                OutputStreamWriter writer =
                        new OutputStreamWriter(new FileOutputStream(OPT_PARSER.getOutput()),
                                StandardCharsets.UTF_8);
                OutputFile.writeTargetText(writer, targetText);
                writer.close();
                System.out.println("Translation process complete");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void parseLanguageOptions() {
        result = validateOptions();

        if (result.isSuccess()) {
            result = validateSourceLanguage();
        }

        if (result.isSuccess()) {
            result = validateTargetLanguage();
        }

        if (!result.isSuccess()) {
            CMD.getHelpSectionMap().put(SECTION_KEY_HEADER_HEADING,
                    help -> help.createHeading(result.getMessage()));
            CMD.usage(System.out);
        }
    }


    private static ParserResult validateOptions() {
        if (CMD.getUnmatchedArguments().size() > 0) {
            String parameterLabel = "parameter";
            if (CMD.getUnmatchedArguments().size() > 1) {
                parameterLabel = "parameters";
            }
            String errorMsg = String.format("Unknown %1s: %2s%n", parameterLabel,
                    CMD.getUnmatchedArguments());
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }

    private static ParserResult validateSourceLanguage() {
        if (!LangParser.isValidLanguage(OPT_PARSER.getSource())) {
            String errorMsg = String.format("Unknown source language[%1s ]%n%2s",
                    OPT_PARSER.getSource(), LANGUAGE_HELP);
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }

    private static ParserResult validateTargetLanguage() {
        if (!LangParser.isValidLanguage(OPT_PARSER.getTarget())) {
            String errorMsg = String.format("Unknown target language[%1s ]%n%2s",
                    OPT_PARSER.getTarget(), LANGUAGE_HELP);
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }
}
