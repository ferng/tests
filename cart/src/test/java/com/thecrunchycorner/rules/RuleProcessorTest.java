package com.thecrunchycorner.rules;

import static com.thecrunchycorner.rules.RuleProcessor.backSoonFilter;
import static com.thecrunchycorner.rules.RuleProcessor.inStockFilter;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import org.junit.jupiter.api.Test;

public class RuleProcessorTest {

    @Test
    public void backSoonFilterTest() {
        Size backSoon = new Size(1, 1, true, true);
        assertTrue(backSoonFilter.test(backSoon));

        Size notBackSoon = new Size(1, 1, false, true);
        assertFalse(backSoonFilter.test(notBackSoon));
    }

    @Test
    public void inStockFilterTest() {
        Stock inStock1 = new Stock(1, 1);
        assertTrue(inStockFilter.test(inStock1));

        Stock inStock10 = new Stock(1, 10);
        assertTrue(inStockFilter.test(inStock10));

        Stock notInStock = new Stock(1, 0);
        assertFalse(inStockFilter.test(notInStock));

    }
}
