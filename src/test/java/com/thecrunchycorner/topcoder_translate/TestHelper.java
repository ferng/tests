package com.thecrunchycorner.topcoder_translate;

public class TestHelper {
    static String getMsg() {
        return "Usage: translate [-hlV] [-i=<input>] [-o=<output>] [-s=<source>] [-t=<target>]\n" +
                "Translate text in one file to another language, then write the translation to\n" +
                "another language\n" +
                "  -h, --help              Show this help message and exit.\n" +
                "  -i, --input=<input>     file with text to be translated\n" +
                "  -l, --languages         list available languages\n" +
                "  -o, --output=<output>   file to save translated text to\n" +
                "  -s, --source=<source>   source language\n" +
                "  -t, --target=<target>   target language\n" +
                "  -V, --version           Print version information and exit.\n";
    }
}
