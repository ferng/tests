package com.thecrunchycorner.topcoder_translate.file_processor;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFile {
    public static String getSourceText(Reader source, String delimiter) {
        BufferedReader reader = new BufferedReader(source);

        Stream<String> text = reader.lines();

        String response = text
                .map(Objects::toString)
                .collect(Collectors.joining(delimiter));

        return response;
    }
}
