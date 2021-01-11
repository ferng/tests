package com.thecrunchycorner.loanapproval.validation;

import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import java.util.function.Predicate;

public class Applicant {

    // Additional checks could be added here with additional predicates (ssn validation, etc).

    // This has been could be called as a predicate which would allow it to be used
    // to validate applications in bulk via a stream.

    public static boolean isRecentApplicant(DecisionRequest decisionRequest) {
        return getDays(decisionRequest.getSsn()) <= getTimeLimit();
    }

    private static int getTimeLimit() {
        //magic number here but would be something else in a real world implementation
        return 30;
    }

    private static int getDays(String ssn) {
        return Integer.parseInt(ssn.substring(0,2));
    }

}
