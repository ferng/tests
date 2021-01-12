package com.thecrunchycorner.loanapproval.rest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.validation.ProcessApproval;
import org.junit.jupiter.api.Test;

public class LoanApplicationControllerTest {
    ProcessApproval approval = mock(ProcessApproval.class);
    LoanApplicationController controller = new LoanApplicationController(approval);

    DecisionRequest decisionRequest = new DecisionRequest("400-00-0000", 60000, 32500);
    Decision expected = new Decision("400-00-0000", "Application approved",
            "Applicant has successfully passed all checks.");


    @Test
    void testTheVerySimpleWorkflowInTheController() {
        when(approval.approve(any(DecisionRequest.class))).thenReturn(expected);

        Decision decision = controller.decisionMaker(decisionRequest);

        assertEquals(expected.getSsn(), decision.getSsn());
        assertEquals(expected.getDecision(), decision.getDecision());
        assertEquals(expected.getReason(), decision.getReason());
    }

}
