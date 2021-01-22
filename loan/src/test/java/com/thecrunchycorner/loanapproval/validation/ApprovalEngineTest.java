package com.thecrunchycorner.loanapproval.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import org.junit.jupiter.api.Test;

public class ApprovalEngineTest {
    ApprovalEngine engine = new ApprovalEngine();

    @Test
    public void testTooLow() {
        DecisionRequest decisionRequest = new DecisionRequest("310-00-0000", 60000, 32500);
        assertEquals(699, engine.applicantRating(decisionRequest));
    }

    @Test
    public void almostThere() {
        DecisionRequest decisionRequest = new DecisionRequest("310-00-0001", 60000, 32500);
        assertEquals(700, engine.applicantRating(decisionRequest));
    }

    @Test
    public void passed() {
        DecisionRequest decisionRequest = new DecisionRequest("310-00-0002", 60000, 32500);
        assertEquals(701, engine.applicantRating(decisionRequest));
    }


}
