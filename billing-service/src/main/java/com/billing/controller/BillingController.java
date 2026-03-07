package com.billing.controller;

import com.billing.dto.StudentEventDTO;
import com.billing.service.BillingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/test")
    public String testBilling(@RequestBody StudentEventDTO dto) {

        billingService.createBilling(dto);

        return "Billing Created Successfully";
    }
}