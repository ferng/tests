package com.thecrunchycorner.loanapproval.rest.service;

import com.thecrunchycorner.loanapproval.decision.Decision;
import com.thecrunchycorner.loanapproval.decision.DecisionRequest;
import com.thecrunchycorner.loanapproval.validation.ProcessApproval;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanApplicationController {

    ProcessApproval processApproval;

    public LoanApplicationController(ProcessApproval processApproval) {
        this.processApproval = processApproval;
    }

    @GetMapping("/decision")
    public Decision decisionMaker(@RequestBody DecisionRequest decisionRequest) {
        return processApproval.approve(decisionRequest);
    }
}
