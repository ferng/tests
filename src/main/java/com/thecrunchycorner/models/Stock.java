package com.thecrunchycorner.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder( {"sizeId", "quantity"})
public class Stock {

    @JsonProperty("sizeId")
    private int sizeId;

    @JsonProperty("quantity")
    private int quantity;

    public Stock() {
    }

    public Stock(int sizeId, int quantity) {
        this.sizeId = sizeId;
        this.quantity = quantity;
    }

    public int getSizeId() {
        return sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

}
