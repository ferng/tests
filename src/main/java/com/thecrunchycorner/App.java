package com.thecrunchycorner;

import com.thecrunchycorner.models.Product;
import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import com.thecrunchycorner.models.backend.ProductReader;
import com.thecrunchycorner.models.backend.SizeReader;
import com.thecrunchycorner.models.backend.StockReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String sourceDirName = "data";
        String destinationDirName = "output";

        Path sourceDir = Paths.get(sourceDirName);

        try {
            List<Product> products = ProductReader.loadData(sourceDirName + "/product.csv");
            List<Size> sizes = SizeReader.loadData(sourceDirName + "/size.csv");
            List<Stock> stocks = StockReader.loadData(sourceDirName + "/stock.csv");
            System.out.println(22);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( "Hello World!" );
    }
}
