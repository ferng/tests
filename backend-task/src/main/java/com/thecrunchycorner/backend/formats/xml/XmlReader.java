package com.thecrunchycorner.backend.formats.xml;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Reader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* File reader able to load XML data */
public class XmlReader implements Reader {
    private static final Logger LOGGER = LogManager.getLogger(XmlReader.class);

    public List<Definition> loadData(String file) throws IOException {
        // Prepare all the formats
        XmlMapper mapper = new XmlMapper();
        CollectionType collectionType =
                mapper.getTypeFactory().constructCollectionType(List.class,
                        XmlDefinition.class);

        InputStream input = new FileInputStream(file);

        LOGGER.info("Loading XML data {}", file);
        return mapper.readValue(input, collectionType);
    }
}
