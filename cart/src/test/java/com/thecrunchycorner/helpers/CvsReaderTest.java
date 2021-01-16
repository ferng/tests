package com.thecrunchycorner.helpers;

import static com.thecrunchycorner.testHelpers.Data.badProductCsv;
import static com.thecrunchycorner.testHelpers.Data.productCsv;
import static com.thecrunchycorner.testHelpers.Data.sizeCsv;
import static com.thecrunchycorner.testHelpers.Data.stockCsv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.thecrunchycorner.models.Product;
import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CvsReaderTest {

    @Test
    public void productDataParsing() throws IOException {
        CsvReader<Product> csvReader = new CsvReader<>();
        StringReader rawReader = new StringReader(productCsv);

        MappingIterator<Product> productMi =
                csvReader.readCsv(rawReader,
                        Product.class);

        HashMap<Integer, Integer> parsedData = new HashMap<>();
        productMi.forEachRemaining(product -> parsedData.put(product.getId(),
                product.getSequence()));

        assertEquals(5, parsedData.size());
        assertEquals(10, parsedData.get(1));
    }


    @Test
    public void sizeDataParsing() throws IOException {
        CsvReader<Size> csvReader = new CsvReader<>();
        StringReader rawReader = new StringReader(sizeCsv);

        MappingIterator<Size> productMi =
                csvReader.readCsv(rawReader,
                        Size.class);

        List<Size> parsedData = new ArrayList<>();
        productMi.forEachRemaining(parsedData::add);

        assertEquals(17, parsedData.size());
        assertEquals(11, parsedData.get(0).getId());
        assertEquals(1, parsedData.get(0).getProductId());
        assertTrue(parsedData.get(0).isBackSoon());
        assertFalse(parsedData.get(0).isSpecial());
    }


    @Test
    public void stockDataParsing() throws IOException {
        CsvReader<Stock> csvReader = new CsvReader<>();
        StringReader rawReader = new StringReader(stockCsv);

        MappingIterator<Stock> stockMi =
                csvReader.readCsv(rawReader,
                        Stock.class);

        HashMap<Integer, Integer> parsedData = new HashMap<>();
        stockMi.forEachRemaining(stock -> parsedData.put(stock.getSizeId(), stock.getQuantity()));

        assertEquals(15, parsedData.size());
        assertEquals(10, parsedData.get(32));
        assertEquals(0, parsedData.get(41));
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
