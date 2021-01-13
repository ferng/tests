package com.thecrunchycorner.models.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StockReader {
    private static final Logger LOGGER = LogManager.getLogger(StockReader.class);

    public static List<Stock> loadData(String file) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        ObjectReader reader = mapper.readerFor(Stock.class).with(schema);

        List<Stock> csvDefinitions = new ArrayList<>();

        LOGGER.info("Loading CSV data {}", file);
        FileReader input = new FileReader(file);
        MappingIterator<Stock> mi = reader.readValues(input);
        mi.forEachRemaining(csvDefinitions::add);
        LOGGER.info("Loaded CSV data {}", file);

        return csvDefinitions;
    }
}
