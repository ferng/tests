package com.thecrunchycorner.backend.formats.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Reader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* File reader able to load JSON data */
public class JsonReader implements Reader {
    private static final Logger LOGGER = LogManager.getLogger(JsonReader.class);

    public List<Definition> loadData(String file) throws IOException {
        // Prepare all the formats
        InputStream input = new FileInputStream(file);
        ObjectMapper mapper = new ObjectMapper();
        CollectionType collectionType =
                mapper.getTypeFactory().constructCollectionType(List.class,
                        JsonDefinition.class);


        LOGGER.info("Loading JSON data {}", file);
        return mapper.readValue(input, collectionType);
    }
}
