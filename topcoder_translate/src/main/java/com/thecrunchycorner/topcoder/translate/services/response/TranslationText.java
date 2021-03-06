package com.thecrunchycorner.topcoder.translate.services.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TranslationText {
    @JsonProperty("trans")
    private String translation;

    public String getTranslation() {
        return translation;
    }
}
