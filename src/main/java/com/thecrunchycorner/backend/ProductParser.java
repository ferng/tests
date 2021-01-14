package com.thecrunchycorner.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.models.Product;
import java.util.HashMap;

public class ProductParser {

    public static HashMap<Integer, Integer> parseData(MappingIterator<Product> mi) {
        HashMap<Integer, Integer> parsedData = new HashMap<>();
        mi.forEachRemaining(product -> parsedData.put(product.getId(), product.getSequence()));

        return parsedData;
    }
}
