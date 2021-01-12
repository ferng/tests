package com.thecrunchycorner.loanapproval.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.decision.Status;
import org.junit.jupiter.api.Test;

public class ProcesApprovalTest {
    private final ApprovalEngine engine = mock(ApprovalEngine.class);
    ProcessApproval approval = new ProcessApproval(engine);

    @Test
    public void validApplication() {
        String ssn = "310-00-0000";
        DecisionRequest decisionRequest = new DecisionRequest(ssn, 60000, 32500);

        when(engine.applicantRating(any())).thenReturn(701);

        Decision decision = approval.approve(decisionRequest);

        assertEquals(ssn, decision.getSsn());
        assertEquals(Status.APPROVED.getDescription(), decision.getDecision());
        assertEquals("Acceptable rating.", decision.getReason());
        assertEquals(16250, decision.getLoan());
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

    @Test
    public void ratingIsJustABitTooLow() {
        String ssn = "310-00-0000";
        DecisionRequest decisionRequest = new DecisionRequest(ssn, 60000, 32500);

        when(engine.applicantRating(any())).thenReturn(699);

        Decision decision = approval.approve(decisionRequest);

        assertEquals(ssn, decision.getSsn());
        assertEquals(Status.REJECTED.getDescription(), decision.getDecision());
        assertEquals("Insufficient rating.", decision.getReason());
    }

}
