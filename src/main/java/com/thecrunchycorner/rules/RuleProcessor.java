package com.thecrunchycorner.rules;

import com.thecrunchycorner.models.Size;
import java.util.function.Predicate;

public class RuleProcessor {
    static Predicate<Size> backSoonFilter = Size::isBackSoon;
}
