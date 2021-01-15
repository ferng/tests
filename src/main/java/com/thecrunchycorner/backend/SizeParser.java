package com.thecrunchycorner.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.models.Size;
import java.io.IOException;
import java.util.List;

public class SizeParser {

    public static List<Size> parseData(MappingIterator<Size> mi) throws IOException {
        return mi.readAll();
    }
}
