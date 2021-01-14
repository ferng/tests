package com.thecrunchycorner.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.models.Stock;
import java.util.HashMap;

public class StockParser {

    public static HashMap<Integer, Integer> parseData(MappingIterator<Stock> mi) {
        HashMap<Integer, Integer> parsedData = new HashMap<>();
        mi.forEachRemaining(stock -> parsedData.put(stock.getSizeId(), stock.getQuantity()));

        return parsedData;
    }
}
