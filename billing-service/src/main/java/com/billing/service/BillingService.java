package com.billing.service;

import com.billing.dto.PaymentDetailsDTO;
import com.billing.dto.PaymentRequestDTO;
import org.springframework.stereotype.Service;



import com.billing.dto.StudentEventDTO;
import com.billing.entity.Payment;
import com.billing.entity.PaymentDetails;
import com.billing.entity.Student;
import com.billing.repository.PaymentDetailsRepository;
import com.billing.repository.PaymentRepository;
import com.billing.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
/*
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
        */

    }
    //  Create Payment
    public void createPayment(PaymentRequestDTO dto) {

        // check if student exists
        studentRepository.findByStudentId(dto.getStudentId())
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + dto.getStudentId()));

        if(paymentRepository.existsByStudentIdAndStatus(dto.getStudentId(),"PENDING")){
            throw new RuntimeException("Pending payment already exists for this student");
        }


        // create payment
        Payment payment = new Payment();
        payment.setStudentId(dto.getStudentId());
        payment.setAmount(dto.getAmount());
        payment.setStatus("PENDING");

        paymentRepository.save(payment);
    }

    //  Pay Bill
    public void payBill(PaymentDetailsDTO dto) {

        Payment payment = paymentRepository.findById(dto.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        PaymentDetails details = new PaymentDetails();
        details.setPaymentId(payment.getId());
        details.setPaymentMethod(dto.getPaymentMethod());
        details.setTransactionId("TXN-" + payment.getId());

        paymentDetailsRepository.save(details);

        // update payment status
        payment.setStatus("PAID");
        paymentRepository.save(payment);
    }

// get student
    public Student getStudent(Integer studentId) {

        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Student not found with id: " + studentId));
    }
// find by payment by student
    public Payment getPaymentByStudent(Integer studentId) {

        return paymentRepository.findByStudentId(studentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found for student: " + studentId));
    }

    public PaymentDetails getPaymentDetails(Integer paymentId) {

        return paymentDetailsRepository.findByPaymentId(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment details not found for paymentId: " + paymentId));
    }
}