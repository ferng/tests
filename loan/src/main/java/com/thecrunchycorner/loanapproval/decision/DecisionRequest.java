package com.thecrunchycorner.loanapproval.decision;

public class DecisionRequest {
    String ssn;
    int loan;
    int income;

    public DecisionRequest(String ssn, int loan, int income) {
        this.ssn = ssn;
        this.loan = loan;
        this.income = income;
    }

    public String getSsn() {
        return ssn;
    }

    public int getLoan() {
        return loan;
    }

    public int getIncome() {
        return income;
    }
}
