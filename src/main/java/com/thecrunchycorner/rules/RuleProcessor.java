package com.thecrunchycorner.rules;

import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import java.util.function.Predicate;

public class RuleProcessor {
    static Predicate<Size> backSoonFilter = Size::isBackSoon;

    static Predicate<Stock> inStockFilter = ((stock -> stock.getQuantity() > 0));
}
