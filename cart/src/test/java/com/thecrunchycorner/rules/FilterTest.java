package com.thecrunchycorner.rules;

import static com.thecrunchycorner.testHelpers.Data.productCsv;
import static com.thecrunchycorner.testHelpers.Data.sizeCsv;
import static com.thecrunchycorner.testHelpers.Data.stockCsv;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.backend.ProductParser;
import com.thecrunchycorner.helpers.CsvReader;
import com.thecrunchycorner.models.Product;
import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class FilterTest {
    Filter filter = new Filter();

    @Test
    public void getBackSoonSizesTest() throws IOException {
        CsvReader<Size> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(sizeCsv);
        MappingIterator<Size> sizeMi = csvReader.readCsv(rawReader, Size.class);
        List<Size> loadedData = sizeMi.readAll();

        Set<Integer> actual = filter.getBackSoonSizes(loadedData);

        Set<Integer> expected = new HashSet<>(Arrays.asList(32, 51, 54, 23, 11, 44, 13, 31));

        assertEquals(expected.size(), actual.size());
        expected.forEach((size) -> {
            assertTrue(actual.contains(size));
        });
    }

    @Test

    public void getInStockSizesTest() throws IOException {
        CsvReader<Stock> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(stockCsv);
        MappingIterator<Stock> stockMi = csvReader.readCsv(rawReader, Stock.class);
        List<Stock> loadedData = stockMi.readAll();

        Set<Integer> actual = filter.getInStockSizes(loadedData);

        Set<Integer> expected = new HashSet<>(Arrays.asList(32, 33, 51, 52, 53, 54, 44, 31));

        assertEquals(expected.size(), actual.size());
        expected.forEach((size) -> {
            assertTrue(actual.contains(size));
        });
    }

    @Test
    public void sortedProductsTest() throws IOException {
        CsvReader<Product> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(productCsv);
        MappingIterator<Product> productMi = csvReader.readCsv(rawReader, Product.class);
        HashMap<Integer, Integer> productData = ProductParser.parseData(productMi);

        HashSet<Integer> validProductIds = new HashSet<>(Arrays.asList(1, 3, 5));

        LinkedHashSet<Integer> expected = new LinkedHashSet<>(Arrays.asList(5, 1, 3));

        LinkedHashSet<Integer> actual = filter.sortedProducts(validProductIds, productData);

        assertEquals(expected, actual);
    }

    @Test
    public void isProductSpecialAndInStockTrueTest() throws IOException {
        CsvReader<Size> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(sizeCsv);
        MappingIterator<Size> sizeMi = csvReader.readCsv(rawReader, Size.class);
        List<Size> loadedSizes = sizeMi.readAll();

        Set<Integer> validSizes = new HashSet<>(Arrays.asList(32, 33, 51, 52, 53, 54, 23, 11, 44,
                13, 31));

        List<Size> testSizes = new ArrayList<>();
        for (Size size : loadedSizes) {
            if (size.getProductId() == 1) {
                testSizes.add(size);
            }
        }

        Optional<Integer> actual = filter.isProductSpecialAndInStock(testSizes, validSizes);
        assertFalse(actual.isPresent());
    }

    @Test
    public void isProductSpecialAndInStockFalseTest() throws IOException {
        CsvReader<Size> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(sizeCsv);
        MappingIterator<Size> sizeMi = csvReader.readCsv(rawReader, Size.class);
        List<Size> loadedSizes = sizeMi.readAll();

        Set<Integer> validSizes = new HashSet<>(Arrays.asList(32, 33, 51, 52, 53, 54, 23, 11, 44,
                13, 31));

        List<Size> testSizes = new ArrayList<>();
        for (Size size : loadedSizes) {
            if (size.getProductId() == 2) {
                testSizes.add(size);
            }
        }

        Optional<Integer> actual = filter.isProductSpecialAndInStock(testSizes, validSizes);

        assertTrue(actual.isPresent());
        assertEquals(2, actual.get());
    }

    @Test
    public void getInvalidSpecialsTest() throws IOException {
        CsvReader<Size> csvReader = new CsvReader<>();

        StringReader rawReader = new StringReader(sizeCsv);
        MappingIterator<Size> sizeMi = csvReader.readCsv(rawReader, Size.class);
        List<Size> testSizes = sizeMi.readAll();

        Set<Integer> validSizes = new HashSet<>(Arrays.asList(32, 33, 51, 52, 53, 54, 23, 11, 44,
                13, 31));

        Set<Integer> actual = filter.getInvalidSpecials(validSizes, testSizes);

        assertEquals(2, actual.size());
        assertTrue(actual.contains(2));
        assertTrue(actual.contains(4));
    }

    @Test
    public void applyTest() throws IOException {
        CsvReader<Product> productReader = new CsvReader<>();
        StringReader rawReader = new StringReader(productCsv);
        MappingIterator<Product> productMi = productReader.readCsv(rawReader, Product.class);
        HashMap<Integer, Integer> productData = ProductParser.parseData(productMi);

        CsvReader<Size> sizeReader = new CsvReader<>();
        rawReader = new StringReader(sizeCsv);
        MappingIterator<Size> sizeMi = sizeReader.readCsv(rawReader, Size.class);
        List<Size> sizeData = sizeMi.readAll();

        CsvReader<Stock> stockReader = new CsvReader<>();
        rawReader = new StringReader(stockCsv);
        MappingIterator<Stock> stockMi = stockReader.readCsv(rawReader, Stock.class);
        List<Stock> stockData = stockMi.readAll();

        Set<Integer> actual = filter.apply(productData, sizeData, stockData);

        assertEquals(3, actual.size());
        assertEquals("[5, 1, 3]", actual.toString());
    }
}
