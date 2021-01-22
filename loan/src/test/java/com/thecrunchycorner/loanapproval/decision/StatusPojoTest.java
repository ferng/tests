package com.thecrunchycorner.loanapproval.decision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusPojoTest {

    @Test
    void testPojo() {

        Status approved = Status.APPROVED;
        Status inProcess = Status.IN_PROCESS;
        Status received = Status.RECEIVED;
        Status rejected = Status.REJECTED;

        assertEquals("Application approved", approved.getDescription());
        assertEquals("Application currently in process", inProcess.getDescription());
        assertEquals("Application received", received.getDescription());
        assertEquals("Application rejected", rejected.getDescription());

    }

}
