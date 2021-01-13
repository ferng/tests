package com.thecrunchycorner.rules;

import com.thecrunchycorner.models.Size;
import com.thecrunchycorner.models.Stock;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class RuleProcessor {
    static Predicate<Size> backSoonFilter = Size::isBackSoon;

}
