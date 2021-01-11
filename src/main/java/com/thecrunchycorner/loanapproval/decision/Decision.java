package com.thecrunchycorner.loanapproval.decision;

public class Decision {
    String ssn;
    String decision;
    String reason;

    public Decision(String ssn, String decision, String reason) {
        this.ssn = ssn;
        this.decision = decision;
        this.reason = reason;
    }

    public String getSsn() {
        return ssn;
    }

    public String getDecision() {
        return decision;
    }

    public String getReason() {
        return reason;
    }
}
