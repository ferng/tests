package com.thecrunchycorner.topcoder_translate.arguments;

import java.util.ArrayList;
import java.util.Arrays;

public class LangParser {
    private static ArrayList<String> languages = new ArrayList<>();

    static {
        for (Languages existingLanguage: Languages.values()) {
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
