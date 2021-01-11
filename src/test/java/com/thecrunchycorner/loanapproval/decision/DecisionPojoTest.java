package com.thecrunchycorner.loanapproval.decision;

import com.thecrunchycorner.loanapproval.decision.Decision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
