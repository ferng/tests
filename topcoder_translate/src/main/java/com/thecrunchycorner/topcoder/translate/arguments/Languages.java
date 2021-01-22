package com.thecrunchycorner.topcoder.translate.arguments;

public enum Languages {
    ENGLISH("en", new String[] {"en", "english"}),
    ICELANDIC("is", new String[] {"is", "icelandic"}),
    JAPANESE("ja", new String[] {"ja", "japanese"}),
    SPANISH("es", new String[] {"es", "spanish"});

    private final String languageCode;
    private final String[] languageAliases;

    Languages(String languageCode, String[] languageAliases) {
        this.languageCode = languageCode;
        this.languageAliases = languageAliases;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String[] getLanguageAliases() {
        String[] copy = new String[languageAliases.length];
        System.arraycopy(languageAliases, 0, copy, 0, languageAliases.length);

        return copy;
    }
}
