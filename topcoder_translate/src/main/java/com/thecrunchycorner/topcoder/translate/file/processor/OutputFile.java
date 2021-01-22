package com.thecrunchycorner.topcoder.translate.file.processor;

import java.io.PrintWriter;
import java.io.Writer;

public class OutputFile {
    public static void writeTargetText(Writer target, String text) {
        PrintWriter writer = new PrintWriter(target);
        writer.print(text);
        writer.flush();
        writer.close();
    }

}
