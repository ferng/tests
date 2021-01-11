package com.thecrunchycorner.loanapproval.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.decision.Status;
import org.junit.jupiter.api.Test;

public class ProcesApprovalTest {
    ProcessApproval approval = new ProcessApproval();

    @Test
    public void validApplication() {
        String ssn = "310-00-0000";
        DecisionRequest decisionRequest = new DecisionRequest(ssn, 60000, 32500);

        Decision decision = approval.approve(decisionRequest);

        assertEquals(ssn, decision.getSsn());
        assertEquals(Status.APPROVED.getDescription(), decision.getDecision());
        assertEquals("Applicant has successfully passed all checks.", decision.getReason());
    }

    @Test
    public void applicationTooSoonAfterLastOne() {
        String ssn = "100-00-0000";
        DecisionRequest decisionRequest = new DecisionRequest(ssn, 60000, 32500);

        Decision decision = approval.approve(decisionRequest);

        assertEquals(ssn, decision.getSsn());
        assertEquals(Status.REJECTED.getDescription(), decision.getDecision());
        assertEquals("Applicant applied within 30 days of last application.", decision.getReason());
    }


}
