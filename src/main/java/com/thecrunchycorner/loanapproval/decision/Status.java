package com.thecrunchycorner.loanapproval.decision;

public enum Status {
    RECEIVED("Application received"),
    IN_PROCESS("Application currently in process"),
    REJECTED("Application rejected"),
    APPROVED("Application approved")
    ;

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
