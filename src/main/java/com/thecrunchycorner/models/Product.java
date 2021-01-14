package com.thecrunchycorner.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder( {"id", "sequence"})
public class Product implements Serializable {

    @JsonProperty("id")
    private int id;

    @JsonProperty("sequence")
    private int sequence;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public int getSequence() {
        return sequence;
    }
}
