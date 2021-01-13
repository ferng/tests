package com.thecrunchycorner.models.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.thecrunchycorner.models.Stock;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StockReader {
    private static final Logger LOGGER = LogManager.getLogger(StockReader.class);

    // I chose to use a Hashmap here as the data is only ever referenced by the size ID
    public static HashMap<Integer, Integer> loadData(String file) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        ObjectReader reader = mapper.readerFor(Stock.class).with(schema);

        HashMap<Integer, Integer> csvDefinitions = new HashMap<>();

        LOGGER.info("Loading CSV data {}", file);
        FileReader input = new FileReader(file);
        MappingIterator<Stock> mi = reader.readValues(input);

        mi.forEachRemaining(stock -> csvDefinitions.put(stock.getSizeId(), stock.getQuantity()));
        LOGGER.info("Loaded CSV data {}", file);

        return csvDefinitions;
    }
}
