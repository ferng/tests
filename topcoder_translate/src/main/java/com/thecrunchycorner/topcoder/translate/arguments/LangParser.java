package com.thecrunchycorner.topcoder.translate.arguments;

import java.util.ArrayList;
import java.util.Arrays;

public class LangParser {
    private static final ArrayList<String> languages = new ArrayList<>();

    static {
        //in a production system this would be in a database somewhere
        for (Languages existingLanguage : Languages.values()) {
            languages.addAll(Arrays.asList(existingLanguage.getLanguageAliases()));
        }
    }

    public static boolean isValidLanguage(String suppliedLanguage) {
        for (String language : languages) {
            if (suppliedLanguage.equalsIgnoreCase(language)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getLanguages() {
        return languages;
    }
}
