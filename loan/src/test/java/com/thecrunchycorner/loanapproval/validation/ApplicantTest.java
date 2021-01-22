package com.thecrunchycorner.loanapproval.validation;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import org.junit.jupiter.api.Test;


//These could be changed to parameterized tests
public class ApplicantTest {
    @Test
    public void testApplicationWithinTimeframe() {
        DecisionRequest decisionRequest = new DecisionRequest("100-00-0000", 60000, 32500);
        assertTrue(Applicant.isRecentApplicant(decisionRequest));
    }

    @Test
    public void testApplicationWithinTimeframeEdgeCase() {
        DecisionRequest decisionRequest = new DecisionRequest("300-00-0000", 60000, 32500);
        assertTrue(Applicant.isRecentApplicant(decisionRequest));
    }

    @Test
    public void testApplicationWithinTimeframeToday() {
        DecisionRequest decisionRequest = new DecisionRequest("000-00-0000", 60000, 32500);
        assertTrue(Applicant.isRecentApplicant(decisionRequest));
    }

    @Test
    public void testApplicationOutsideTimeFrame() {
        DecisionRequest decisionRequest = new DecisionRequest("310-00-0000", 60000, 32500);
        assertFalse(Applicant.isRecentApplicant(decisionRequest));
    }


}
