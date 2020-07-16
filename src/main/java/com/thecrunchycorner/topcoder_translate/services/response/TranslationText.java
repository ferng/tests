package com.thecrunchycorner.topcoder_translate.services.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationText {
    @JsonProperty("trans")
    private String translation;

    @JsonProperty("orig")
    private String original;

    @JsonProperty("backend")
    private String backend;

    public String getTranslation() {
        return translation;
    }

    public String getOriginal() {
        return original;
    }

    public String getBackend() {
        return backend;
    }
}
