package com.thecrunchycorner.topcoder.translate.services.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sentences {
    List<TranslationText> translations;

    @JsonProperty("sentences")
    public List<TranslationText> getTranslations() {
        return translations;
    }
}
