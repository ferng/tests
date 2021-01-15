package com.thecrunchycorner.backend;

import static com.thecrunchycorner.testHelpers.Data.badProductCsv;
import static com.thecrunchycorner.testHelpers.Data.stockCsv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.thecrunchycorner.helpers.CsvReader;
import com.thecrunchycorner.models.Product;
import com.thecrunchycorner.models.Stock;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockParserTest {
    CsvReader<Stock> csvReader;

    @BeforeEach
    public void setup() {
        csvReader = new CsvReader<>();
    }

    @Test
    public void stockDataParsing() throws IOException {
        CsvReader<Stock> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(stockCsv);
        MappingIterator<Stock> stockMi = csvReader.readCsv(rawReader, Stock.class);
        List<Stock> expectedData = stockMi.readAll();

        rawReader = new StringReader(stockCsv);
        stockMi = csvReader.readCsv(rawReader, Stock.class);
        List<Stock> actualData = StockParser.parseData(stockMi);


        assertEquals(expectedData.size(), actualData.size());
        expectedData.forEach((expected -> {
            boolean foundMatch = false;
            for (Stock actual : actualData) {
                if (expected.getSizeId() == actual.getSizeId()) {
                    foundMatch = true;
                    assertEquals(expected.getQuantity(), actual.getQuantity());
                    break;
                }
            }
            assertTrue(foundMatch);
        }));
    }


    @Test
    public void badProductDataParsing() {
        Assertions.assertThrows(RuntimeJsonMappingException.class, () -> {
            CsvReader<Product> csvReader = new CsvReader<>();
            StringReader rawReader = new StringReader(badProductCsv);

            MappingIterator<Product> productMi =
                    csvReader.readCsv(rawReader,
                            Product.class);

            HashMap<Integer, Integer> parsedData = new HashMap<>();
            productMi.forEachRemaining(product -> parsedData.put(product.getId(),
                    product.getSequence()));
        });
    }

}
