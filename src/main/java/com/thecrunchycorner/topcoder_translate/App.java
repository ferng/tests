package com.thecrunchycorner.topcoder_translate;

import com.thecrunchycorner.topcoder_translate.arguments.LangParser;
import com.thecrunchycorner.topcoder_translate.arguments.OptParser;
import com.thecrunchycorner.topcoder_translate.arguments.ParserResult;
import picocli.CommandLine;

public class App {
    private static final String SECTION_KEY_HEADER_HEADING = "headerHeading";
    private static final OptParser optParser = new OptParser();
    private static final CommandLine cmd = new CommandLine(optParser);
    private static final boolean SUCCESS = true;
    private static final boolean FAILURE = false;
    private static final ParserResult SUCCESS_RESULT = new ParserResult(SUCCESS, "");
    private static ParserResult result = SUCCESS_RESULT;

    public static void main(String[] args) {
        cmd.getHelpSectionMap().remove(SECTION_KEY_HEADER_HEADING);
        cmd.setStopAtUnmatched(true);
        cmd.parseArgs(args);

        if (cmd.isUsageHelpRequested()) {
            cmd.usage(System.out);
        } else if (cmd.isVersionHelpRequested()) {
            cmd.printVersionHelp(System.out);
        } else if (optParser.isLanguageHelpRequested()) {
            cmd.getHelpSectionMap().put(SECTION_KEY_HEADER_HEADING,
                    help -> help.createHeading(getLanguageHelp()));
            cmd.usage(System.out);
        } else
        {
            result = validateOptions();

            if (result.isSuccess()) {
                result = validateSourceLanguage();
            }

            if (result.isSuccess()) {
                result = validateTargetLanguage();
            }

            if (!result.isSuccess()) {
                cmd.getHelpSectionMap().put(SECTION_KEY_HEADER_HEADING,
                        help -> help.createHeading(result.getMessage()));
                cmd.usage(System.out);
            }
        }
        return;


//        System.out.println(optParser.getSource());
//        System.out.println(optParser.getTarget());
//        System.out.println(optParser.getInput());
//        System.out.println(optParser.getOutput());
    }


    private static ParserResult validateOptions() {
        if (cmd.getUnmatchedArguments().size() > 0) {
            String parameterLabel = "parameter";
            if (cmd.getUnmatchedArguments().size() > 1) {
                parameterLabel = "parameters";
            }
            String errorMsg = String.format("Unknown %1s: %2s\n", parameterLabel,
                    cmd.getUnmatchedArguments());
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }

    private static String getLanguageHelp() {
        StringBuilder builder = new StringBuilder();
        builder.append("Available languages: ");
        builder.append(LangParser.getLanguages().toString());
        builder.append("\n");

        return builder.toString();
    }

    private static ParserResult validateSourceLanguage() {
        if (!LangParser.isValidLanguage(optParser.getSource())) {
            String errorMsg = String.format("Unknown source language[%1s ]\n%2s",
                    optParser.getSource(), getLanguageHelp());
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }

    private static ParserResult validateTargetLanguage() {
        if (!LangParser.isValidLanguage(optParser.getTarget())) {
            String errorMsg = String.format("Unknown target language[%1s ]\n%2s",
                    optParser.getTarget(), getLanguageHelp());
            return new ParserResult(FAILURE, errorMsg);
        }
        return SUCCESS_RESULT;
    }
}
