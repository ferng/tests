package com.thecrunchycorner.loanapproval.validation;

import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import org.springframework.stereotype.Service;

// a  mock approval engine which would call a real rating service and decision engine outside of
// a demo scenario.

@Service
public class ApprovalEngine {

    public int applicantRating(DecisionRequest decisionRequest) {
        return getRating(decisionRequest.getSsn());
    }

    // the credit rating agency consumer
    private static int getRating(String ssn) {
        int ratingSel = Integer.parseInt(ssn.substring(ssn.length() - 1));
        int rating;

        if (ratingSel == 0) {
            rating = 699;
        } else if (ratingSel == 1) {
            rating = 700;
        } else {
            rating = 701;
        }

        return rating;
    }
}
