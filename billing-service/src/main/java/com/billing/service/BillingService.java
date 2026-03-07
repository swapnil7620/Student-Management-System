package com.billing.service;

import org.springframework.stereotype.Service;



import com.billing.dto.StudentEventDTO;
import com.billing.entity.Payment;
import com.billing.entity.PaymentDetails;
import com.billing.entity.Student;
import com.billing.repository.PaymentDetailsRepository;
import com.billing.repository.PaymentRepository;
import com.billing.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    private final StudentRepository studentRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentDetailsRepository paymentDetailsRepository;

    public BillingService(StudentRepository studentRepository,
                          PaymentRepository paymentRepository,
                          PaymentDetailsRepository paymentDetailsRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    public void createBilling(StudentEventDTO dto) {

        // Save Student
        Student student = new Student();
        student.setStudentId(dto.getStudentId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setDivisionCode(dto.getDivisionCode());
        student.setStanderdStandard(dto.getStanderdStandard());

        Student savedStudent = studentRepository.save(student);

        // Create Payment
        Payment payment = new Payment();
        payment.setStudentId(savedStudent.getStudentId());
        payment.setAmount(5000.0);
        payment.setStatus("PENDING");

        Payment savedPayment = paymentRepository.save(payment);

        // Create Payment Details
        PaymentDetails details = new PaymentDetails();
        details.setPaymentId(savedPayment.getId());
        details.setPaymentMethod("CASH");
        details.setTransactionId("TXN-" + savedPayment.getId());

        paymentDetailsRepository.save(details);
    }
}