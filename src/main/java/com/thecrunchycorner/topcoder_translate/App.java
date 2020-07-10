package com.thecrunchycorner.topcoder_translate;

import com.thecrunchycorner.topcoder_translate.arguments.LangParser;
import com.thecrunchycorner.topcoder_translate.arguments.OptParser;
import com.thecrunchycorner.topcoder_translate.arguments.ParserResult;
import com.thecrunchycorner.topcoder_translate.file_processor.InputFile;
import com.thecrunchycorner.topcoder_translate.file_processor.OutputFile;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import picocli.CommandLine;

public class App {
    private static final String SECTION_KEY_HEADER_HEADING = "headerHeading";
    private static final OptParser OPT_PARSER = new OptParser();
    private static final CommandLine CMD = new CommandLine(OPT_PARSER);
    private static final boolean SUCCESS = true;
    private static final boolean FAILURE = false;
    private static final ParserResult SUCCESS_RESULT = new ParserResult(SUCCESS, "");
    private static ParserResult result = SUCCESS_RESULT;
    private static final String LANGUAGE_HELP =
            "Available languages: " + LangParser.getLanguages().toString() + "\n";
    private static final int EXIT_CODE_SUCCESS = 0;
    private static final int EXIT_CODE_FAILURE = 1;
    private static final String DELIMITER = "///";

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
        } else
        {
            parseLanguageOptions();
            try {
                String sourceText =
                        InputFile.getSourceText(new FileReader(OPT_PARSER.getInput()), DELIMITER);
                String targetText = sourceText;
                OutputFile.writeTargetText(new FileWriter(OPT_PARSER.getOutput()), DELIMITER,
                        targetText);

                System.out.println("Translation complete");
                System.exit(EXIT_CODE_SUCCESS);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.exit(EXIT_CODE_FAILURE);
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
            String errorMsg = String.format("Unknown %1s: %2s\n", parameterLabel,
                    CMD.getUnmatchedArguments());
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }

    private static ParserResult validateSourceLanguage() {
        if (!LangParser.isValidLanguage(OPT_PARSER.getSource())) {
            String errorMsg = String.format("Unknown source language[%1s ]\n%2s",
                    OPT_PARSER.getSource(), LANGUAGE_HELP);
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }

    private static ParserResult validateTargetLanguage() {
        if (!LangParser.isValidLanguage(OPT_PARSER.getTarget())) {
            String errorMsg = String.format("Unknown target language[%1s ]\n%2s",
                    OPT_PARSER.getTarget(), LANGUAGE_HELP);
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }
}
