package com.thecrunchycorner.loanapproval.rest.service;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.validation.ProcessApproval;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanApplicationController {

    @GetMapping("/decision")
    public Decision decisionMaker(@RequestBody DecisionRequest decisionRequest) {
        ProcessApproval approval = new ProcessApproval();


        Decision decision = approval.approve(decisionRequest);

        return decision;
    }
}
