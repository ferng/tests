package com.thecrunchycorner.topcoder_translate.arguments;

public class ParserResult {
    boolean success;
    String message;

    public ParserResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}