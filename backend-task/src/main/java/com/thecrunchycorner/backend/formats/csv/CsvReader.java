package com.thecrunchycorner.backend.formats.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* File reader able to load CSV data
*   CSV is a loose standard and needs most tweaking,
* */
public class CsvReader implements Reader {
    private static final Logger LOGGER = LogManager.getLogger(CsvReader.class);

    public List<Definition> loadData(String file) throws IOException {

        // Prepare all the formats
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        ObjectReader reader = mapper.readerFor(CsvDefinition.class).with(schema);

        List<Definition> csvDefinitions = new ArrayList<>();

        LOGGER.info("Loading CSV data {}", file);
        FileReader input = new FileReader(file);
        MappingIterator<CsvDefinition> mi = reader.readValues(input);
        mi.forEachRemaining(csvDefinitions::add);

        return csvDefinitions;
    }
}
