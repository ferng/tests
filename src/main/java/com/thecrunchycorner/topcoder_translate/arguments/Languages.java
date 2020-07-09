package com.thecrunchycorner.topcoder_translate.arguments;

public enum Languages {
    ENGLISH("en", new String[] {"en", "english"}),
    ICELANDIC("is", new String[] {"is", "icelandic"}),
    JAPANESE("ja", new String[] {"ja", "japanese"}),
    SPANISH("es", new String[] {"es", "spanish"});

    private String languageCode;
    private String[] languageAliases;

    Languages(String languageCode, String[] languageAliases) {
        this.languageCode = languageCode;
        this.languageAliases = languageAliases;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String[] getLanguageAliases() {
        return languageAliases;
    }
}
