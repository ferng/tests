package com.thecrunchycorner.backend;

import static com.thecrunchycorner.testHelpers.Data.badProductCsv;
import static com.thecrunchycorner.testHelpers.Data.productCsv;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.thecrunchycorner.helpers.CsvReader;
import com.thecrunchycorner.models.Product;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductParserTest {
    CsvReader<Product> csvReader;

    @BeforeEach
    public void setup() {
        csvReader = new CsvReader<>();
    }

    @Test
    public void productDataParsing() throws IOException {
        CsvReader<Product> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(productCsv);
        MappingIterator<Product> productMi = csvReader.readCsv(rawReader, Product.class);
        List<Product> expectedData = productMi.readAll();

        rawReader = new StringReader(productCsv);
        productMi = csvReader.readCsv(rawReader, Product.class);
        HashMap<Integer, Integer> actualData = ProductParser.parseData(productMi);

        assertEquals(expectedData.size(), actualData.size());
        expectedData.forEach((product -> {
            int id = product.getId();
            assertEquals(product.getSequence(), actualData.get(id));
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
