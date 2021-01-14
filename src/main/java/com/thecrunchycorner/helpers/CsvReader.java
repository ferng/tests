package com.thecrunchycorner.helpers;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CsvReader<T> {
    private static final Logger LOGGER = LogManager.getLogger(CsvReader.class);

    public MappingIterator<T> readCsv(Reader fileReader, Class<T> definition) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        ObjectReader reader = mapper.readerFor(definition).with(schema);

        LOGGER.info("Loading CSV data");
        MappingIterator<T> mi = reader.readValues(fileReader);
        LOGGER.info("Loaded CSV data");

        return mi;
    }
}
