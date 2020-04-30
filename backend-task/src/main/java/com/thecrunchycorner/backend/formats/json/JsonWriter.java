package com.thecrunchycorner.backend.formats.json;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Writer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* File writer able to save JSON data */
public class JsonWriter implements Writer {
    private static final Logger LOGGER = LogManager.getLogger(JsonWriter.class);

    @Override
    public void saveData(String file, List<Definition> definitions) throws IOException {
        // Prepare all the formats
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        // Format data for JSON files
        List<JsonDefinition> jsonData = new ArrayList<>();
        definitions.forEach((definition -> jsonData.add(new JsonDefinition(definition))));

        LOGGER.info("Writing JSON data {}", file);
        writer.writeValue(new File(file), jsonData);
    }
}
