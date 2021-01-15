package com.thecrunchycorner.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void compareThisLessThan() {
        Product product = new Product(1, 1);
        Product comparison = new Product(2, 2);
        assertEquals(-1,product.compareTo(comparison) );
    }

    @Test
    public void compareThisMoreThan() {
        Product product = new Product(3, 3);
        Product comparison = new Product(2, 2);
        assertEquals(1,product.compareTo(comparison) );
    }

    @Test
    public void compareThisSame() {
        Product product = new Product(3, 3);
        Product comparison = new Product(4, 3);
        assertEquals(0,product.compareTo(comparison) );
    }
}
