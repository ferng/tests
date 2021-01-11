package com.thecrunchycorner.loanapproval.validation;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.decision.Status;

public class ProcessApproval {
    public Decision approve(DecisionRequest decisionRequest) {

        String decision = Status.APPROVED.getDescription();
        String reason = "Applicant has successfully passed all checks.";
        if(Applicant.isRecentApplicant(decisionRequest)) {
            decision = Status.REJECTED.getDescription();
            reason = "Applicant applied within 30 days of last application.";
        }

        return new Decision(decisionRequest.getSsn(), decision, reason);
    }
}
