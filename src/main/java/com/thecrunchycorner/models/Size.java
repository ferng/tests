package com.thecrunchycorner.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder( {"id", "productId", "backSoon", "special"})
public class Size {

    @JsonProperty("id")
    private int id;

    @JsonProperty("productId")
    private int productId;

    @JsonProperty("backSoon")
    private boolean backSoon;

    @JsonProperty("special")
    private boolean special;

    public Size() {
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public boolean isBackSoon() {
        return backSoon;
    }

    public boolean isSpecial() {
        return special;
    }
}
