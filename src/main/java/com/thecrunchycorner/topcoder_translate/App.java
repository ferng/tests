package com.thecrunchycorner.topcoder_translate;

import com.thecrunchycorner.topcoder_translate.arguments.OptParser;
import picocli.CommandLine;

public class App {
    static final String SECTION_KEY_HEADER_HEADING = "headerHeading";

    private static OptParser optParser = new OptParser();

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(optParser);
        cmd.setStopAtUnmatched(true);
        cmd.parseArgs(args);
        if (cmd.getUnmatchedArguments().size() > 0) {
            String parameterLabel = "parameter";
            if (cmd.getUnmatchedArguments().size() > 1) {
                parameterLabel = "parameters";
            }
            String errorMsg = String.format("Unknown %1s: %2s\n", parameterLabel,
                    cmd.getUnmatchedArguments());
            cmd.getHelpSectionMap().put(SECTION_KEY_HEADER_HEADING,
                    help -> help.createHeading(errorMsg));
            cmd.usage(System.out);
            return;
        }

        if (cmd.isUsageHelpRequested()) {
            cmd.usage(System.out);
            return;
        } else if (cmd.isVersionHelpRequested()) {
            cmd.printVersionHelp(System.out);
            return;
        }

        System.out.println(optParser.getSource());
        System.out.println(optParser.getTarget());
        System.out.println(optParser.getInput());
        System.out.println(optParser.getOutput());

    }
}
