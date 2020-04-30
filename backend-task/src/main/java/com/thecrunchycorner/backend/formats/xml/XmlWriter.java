package com.thecrunchycorner.backend.formats.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Writer;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* File writer able to save XML data */
public class XmlWriter implements Writer {
    private static final Logger LOGGER = LogManager.getLogger(XmlWriter.class);

    @Override
    public void saveData(String file, List<Definition> definitions) throws IOException {
        // Prepare all the formats
        XmlMapper mapper = new XmlMapper();
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        // Format data for JSON files
        XmlOutPutDefinition xmlData = new XmlOutPutDefinition();
        for (Definition definition : definitions) {
            xmlData.add(new XmlDefinition(definition));
        }

        LOGGER.info("Writing XML data {}", file);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(file), xmlData);
    }
}
