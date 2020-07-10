package com.thecrunchycorner.topcoder_translate.file_processor;

import java.io.PrintWriter;
import java.io.Writer;

public class OutputFile {
    public static void writeTargetText(Writer target, String delimiter, String text) {
        PrintWriter writer = new PrintWriter(target);
        String[] formattedText = text.split(delimiter);

        for (String line : formattedText) {
            writer.println(line);
        }
        writer.flush();
        writer.close();
    }

}
