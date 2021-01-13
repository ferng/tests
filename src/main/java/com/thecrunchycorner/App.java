package com.thecrunchycorner;

import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.backend.ProductReader;
import com.thecrunchycorner.models.backend.SizeReader;
import com.thecrunchycorner.models.backend.StockReader;
import com.thecrunchycorner.rules.Filter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        String sourceDirName = "data";
        try {
            HashMap<Integer, Integer> products = ProductReader.loadData(sourceDirName + "/product" +
                    ".csv");
            List<Size> sizes = SizeReader.loadData(sourceDirName + "/size.csv");
            HashMap<Integer, Integer> stocks = StockReader.loadData(sourceDirName + "/stock.csv");

            Filter filter = new Filter();

            Set<Integer> filtered = filter.apply(products, sizes, stocks);

            System.out.println(filtered);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
