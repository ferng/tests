package com.thecrunchycorner.loanapproval.validation;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.decision.Status;
import org.springframework.stereotype.Service;

@Service
public class ProcessApproval {
    private final ApprovalEngine engine;

    public ProcessApproval(ApprovalEngine engine) {
        this.engine = engine;

    }

    public Decision approve(DecisionRequest decisionRequest) {
        Decision decision;
        int rating;

        if (Applicant.isRecentApplicant(decisionRequest)) {
            decision = new Decision(decisionRequest.getSsn(),
                    Status.REJECTED.getDescription(),
                    "Applicant applied within 30 days of last application."
            );
        } else {
            rating = engine.applicantRating(decisionRequest);

            if (rating <= 700) {
                decision = new Decision(decisionRequest.getSsn(),
                        Status.REJECTED.getDescription(),
                        "Insufficient rating."
                );
            } else {
                float approvedLoad = calculateLoan(rating, decisionRequest);
                decision = new Decision(decisionRequest.getSsn(),
                        Status.APPROVED.getDescription(),
                        "Acceptable rating.",
                        approvedLoad);
            }
        }

        return decision;
    }

    // this could call another service to calculate loans.
    // we don't use rating here, but they probably would.
    private float calculateLoan(int rating, DecisionRequest decisionRequest) {
        return decisionRequest.getIncome() / 2f;
    }
}
