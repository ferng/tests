package com.thecrunchycorner.topcoder.translate.arguments;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class LangParserTest {

    @Test
    public void shouldReturnTrueIfValidLanguageCodeGiven() {
        Assert.assertTrue(LangParser.isValidLanguage("en"));
    }

    @Test
    public void shouldReturnTrueIfValidLanguageNameGiven() {
        Assert.assertTrue(LangParser.isValidLanguage("English"));
    }

    @Test
    public void shouldReturnFalseIfUnknownLanguageGiven() {
        Assert.assertFalse(LangParser.isValidLanguage("Plop"));
    }

    @Test
    public void shouldReturnListOfKnownLanguages() {
        ArrayList<String> languages = LangParser.getLanguages();

        Assert.assertEquals(8, languages.size());
        Assert.assertEquals(languages.get(0), "en");
        Assert.assertEquals(languages.get(1), "english");
    }

}
