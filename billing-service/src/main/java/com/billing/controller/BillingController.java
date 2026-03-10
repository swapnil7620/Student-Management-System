package com.billing.controller;

import com.billing.dto.PaymentDetailsDTO;
import com.billing.dto.PaymentRequestDTO;
import com.billing.dto.StudentEventDTO;
import com.billing.entity.Payment;
import com.billing.entity.PaymentDetails;
import com.billing.entity.Student;
import com.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {

        this.billingService = billingService;
    }

    // Test API
    @PostMapping("/test")
    public ResponseEntity<?> testBilling( @Valid @RequestBody StudentEventDTO dto) {

        billingService.createBilling(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Billing Created Successfully");
    }

    // Create Payment
    @PostMapping("/payment")
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequestDTO dto) {

        billingService.createPayment(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Payment Created (PENDING)");
    }

    // Pay Bill
    @PostMapping("/pay")
    public ResponseEntity<?> payBill( @Valid @RequestBody PaymentDetailsDTO dto) {

        billingService.payBill(dto);

        return ResponseEntity.ok("Payment Successful");
    }

    // get student by student id
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable Integer studentId) {

        Student student = billingService.getStudent(studentId);

        return ResponseEntity.ok(student);
    }
  // get Payment By studentId
    @GetMapping("/payment/{studentId}")
    public ResponseEntity<?> getPaymentByStudent(@PathVariable Integer studentId) {

        Payment payment = billingService.getPaymentByStudent(studentId);

        return ResponseEntity.ok(payment);
    }
// get Payment Details by Payment ID
    @GetMapping("/payment-details/{paymentId}")
    public ResponseEntity<?> getPaymentDetails(@PathVariable Integer paymentId) {

        PaymentDetails details = billingService.getPaymentDetails(paymentId);

        return ResponseEntity.ok(details);
    }
}