package com.thecrunchycorner.backend;

import static com.thecrunchycorner.testHelpers.Data.sizeCsv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.helpers.CsvReader;
import com.thecrunchycorner.models.Size;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SizeParserTest {
    CsvReader<Size> csvReader;

    @BeforeEach
    public void setup() {
        csvReader = new CsvReader<>();
    }

    @Test
    public void sizeDataParsing() throws IOException {
        CsvReader<Size> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(sizeCsv);
        MappingIterator<Size> sizeMi = csvReader.readCsv(rawReader, Size.class);
        List<Size> expectedData = sizeMi.readAll();

        rawReader = new StringReader(sizeCsv);
        sizeMi = csvReader.readCsv(rawReader, Size.class);
        List<Size> actualData = SizeParser.parseData(sizeMi);

        assertEquals(expectedData.size(), actualData.size());
        expectedData.forEach((size -> {
            boolean foundMatch = false;
            for (Size actual : actualData) {
                if (size.getId() == actual.getId()) {
                    foundMatch = true;
                    assertEquals(size.getProductId(), actual.getProductId());
                    assertEquals(size.isBackSoon(), actual.isBackSoon());
                    assertEquals(size.isSpecial(), actual.isSpecial());
                    break;
                }
            }
            assertTrue(foundMatch);
        }));
    }
}
