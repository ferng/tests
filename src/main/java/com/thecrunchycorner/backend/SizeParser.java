package com.thecrunchycorner.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.models.Size;
import java.util.ArrayList;
import java.util.List;

public class SizeParser {

    public static List<Size> parseData(MappingIterator<Size> mi) {
        List<Size> parsedData = new ArrayList<>();
        mi.forEachRemaining(parsedData::add);

        return parsedData;
    }
}
