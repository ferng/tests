package com.thecrunchycorner.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.models.Stock;
import java.io.IOException;
import java.util.List;

public class StockParser {

    public static List<Stock> parseData(MappingIterator<Stock> mi) throws IOException {
        return mi.readAll();
    }
}
