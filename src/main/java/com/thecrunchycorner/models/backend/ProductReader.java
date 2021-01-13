package com.thecrunchycorner.models.backend;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.thecrunchycorner.models.Product;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductReader {
    private static final Logger LOGGER = LogManager.getLogger(ProductReader.class);

    public static List<Product> loadData(String file) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        ObjectReader reader = mapper.readerFor(Product.class).with(schema);

        List<Product> csvDefinitions = new ArrayList<>();

        LOGGER.info("Loading CSV data {}", file);
        java.io.FileReader input = new FileReader(file);
        MappingIterator<Product> mi = reader.readValues(input);
        mi.forEachRemaining(csvDefinitions::add);
        LOGGER.info("Loaded CSV data {}", file);

        return csvDefinitions;
    }
}
