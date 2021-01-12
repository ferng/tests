package com.thecrunchycorner.loanapproval.validation;

import com.thecrunchycorner.loanapproval.decision.DecisionRequest;

public class Applicant {

    // Additional checks could be added here with additional predicates (ssn validation, etc).

    // As this returns a boolean it can be called by a predicate which would
    // allow it to be used to validate applications in bulk via a stream.

    public static boolean isRecentApplicant(DecisionRequest decisionRequest) {
        return getDays(decisionRequest.getSsn()) <= getTimeLimit();
    }

    private static int getDays(String ssn) {
        return Integer.parseInt(ssn.substring(0, 2));
    }


    //magic number used here but would be something else in a real world implementation
    private static int getTimeLimit() {
        return 30;
    }

}
