package com.thecrunchycorner;

import com.fasterxml.jackson.databind.MappingIterator;
import com.thecrunchycorner.backend.ProductParser;
import com.thecrunchycorner.backend.SizeParser;
import com.thecrunchycorner.backend.StockParser;
import com.thecrunchycorner.helpers.CsvReader;
import com.thecrunchycorner.models.Product;
import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import com.thecrunchycorner.rules.Filter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        String sourceDirName = "data";
        try {
            CsvReader<Product> productCsvReader = new CsvReader<>();
            MappingIterator<Product> productMi =
                    productCsvReader.readCsv(new FileReader(sourceDirName + "/product.csv"),
                            Product.class);
            HashMap<Integer, Integer> products = ProductParser.parseData(productMi);

            CsvReader<Size> sizeCsvReader = new CsvReader<>();
            MappingIterator<Size> sizeMi =
                    sizeCsvReader.readCsv(new FileReader(sourceDirName + "/size.csv"), Size.class);
            List<Size> sizes = SizeParser.parseData(sizeMi);

            CsvReader<Stock> stockCsvReader = new CsvReader<>();
            MappingIterator<Stock> stockMi =
                    stockCsvReader.readCsv(new FileReader(sourceDirName + "/stock.csv"),
                            Stock.class);
            HashMap<Integer, Integer> stocks = StockParser.parseData(stockMi);


            Filter filter = new Filter();
            Set<Integer> filtered = filter.apply(products, sizes, stocks);

            System.out.println(filtered);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
