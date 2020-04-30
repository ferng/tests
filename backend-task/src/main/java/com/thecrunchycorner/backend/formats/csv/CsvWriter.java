package com.thecrunchycorner.backend.formats.csv;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.thecrunchycorner.backend.formats.Definition;
import com.thecrunchycorner.backend.formats.Writer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* File writer able to save CSV data
 *   CSV is a loose standard and needs most tweaking.
 *   This shows the need for discrete read / write components and such a pairing for each file type
 *   Some formats are simply not as co-operative as others
 * */
public class CsvWriter implements Writer {
    private static final Logger LOGGER = LogManager.getLogger(CsvWriter.class);

    @Override
    public void saveData(String file, List<Definition> definitions) throws IOException {
        // Prepare all the formats
        CsvMapper mapper = new CsvMapper();
        mapper.getFactory().disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);

        /*
        Adding .withoutQuoteChar() makes the headings appear without quotes as per source
         */
        // Two mappers are needed one to output the header (along with the first record)
        CsvSchema schemaHeader =
                mapper.schemaFor(CsvDefinition.class).withHeader().withoutQuoteChar();
        // and one for all subsequent records
        CsvSchema schemaNoHeader = mapper.schemaFor(CsvDefinition.class).withoutQuoteChar();

        // We also need two writers: one for the row with the header (with the header schema)
        ObjectWriter writerHeader = mapper.writerFor(CsvDefinition.class).with(schemaHeader);
        // and one for all subsequent records (with the no-header schema)
        ObjectWriter writerNoHeader = mapper.writerFor(CsvDefinition.class).with(schemaNoHeader);


        // where we will be writing to
        FileOutputStream fileOutputStream = new FileOutputStream(new File(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream
                , 1024);
        OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream);


        LOGGER.info("Writing CSV data {}", file);
        writerHeader.writeValue(writerOutputStream, new CsvDefinition(definitions.get(0)));

        for (int i = 1; i < definitions.size(); i++) {
            writerNoHeader.writeValue(writerOutputStream, new CsvDefinition(definitions.get(i)));
        }

        writerOutputStream.flush();
        writerOutputStream.close();
    }
}
