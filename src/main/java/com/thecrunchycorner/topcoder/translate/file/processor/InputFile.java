package com.thecrunchycorner.topcoder.translate.file.processor;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFile {
    public static String getSourceText(Reader source) {
        BufferedReader reader = new BufferedReader(source);

        Stream<String> text = reader.lines();

        return text
                .map(Objects::toString)
                .collect(Collectors.joining());
    }
}
