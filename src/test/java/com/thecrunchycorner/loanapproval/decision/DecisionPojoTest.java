package com.thecrunchycorner.loanapproval.decision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DecisionPojoTest {

    @Test
    void testPojo() {
        String ssn = "010-234-5678";
        String decisionText = "Aproved";
        String reason = "Everything was hunky dory";

        Decision decision = new Decision(ssn, decisionText, reason);
        assertEquals(ssn, decision.getSsn());
        assertEquals(decisionText, decision.getDecision());
        assertEquals(reason, decision.getReason());
    }

}
