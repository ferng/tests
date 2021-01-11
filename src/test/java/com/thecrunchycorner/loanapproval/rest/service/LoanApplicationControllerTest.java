package com.thecrunchycorner.loanapproval.rest.service;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thecrunchycorner.loanapproval.decision.Status;
import org.junit.jupiter.api.Test;

public class LoanApplicationControllerTest {
    DecisionRequest decisionRequest = new DecisionRequest("400-00-0000", 60000, 32500);
    Decision expected = new Decision("400-00-0000", "Application approved",
            "Applicant has successfully passed all checks.");

    LoanApplicationController controller = new LoanApplicationController();

    @Test
    void testTheVerySimpleWorkflowInTheController() {
        Decision decision = controller.decisionMaker(decisionRequest);

        assertEquals(expected.getSsn(), decision.getSsn());
        assertEquals(expected.getDecision(), decision.getDecision());
        assertEquals(expected.getReason(), decision.getReason());
    }

}
