package com.thecrunchycorner.topcoder_translate;

import com.thecrunchycorner.topcoder_translate.arguments.Parser;
import picocli.CommandLine;

public class App
{
    private static Parser tr = new Parser();

    public static void main( String[] args )
    {
        CommandLine opts = new CommandLine(tr);
        opts.parseArgs(args);

        if(opts.isUsageHelpRequested()) {
            opts.usage(System.out);
            return;
        } else if (opts.isVersionHelpRequested()) {
            opts.printVersionHelp(System.out);
            return;
        }

        System.out.println(tr.getSource());
        System.out.println(tr.getTarget());
        System.out.println(tr.getInput());
        System.out.println(tr.getOutput());

    }
}
