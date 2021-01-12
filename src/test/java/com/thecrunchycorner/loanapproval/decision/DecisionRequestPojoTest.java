package com.thecrunchycorner.loanapproval.decision;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DecisionRequestPojoTest {

    @Test
    void testPojo() {

        String ssn = "010-234-5678";
        int loan = 60000;
        int income = 32000;

        DecisionRequest decision = new DecisionRequest(ssn, loan, income);
        assertEquals(ssn, decision.getSsn());
        assertEquals(loan, decision.getLoan());
        assertEquals(income, decision.getIncome());
    }

}
