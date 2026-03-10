package com.billing.repository;

import com.billing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment,Integer> {
    Optional<Payment> findByStudentId(Integer studentId);
    boolean existsByStudentIdAndStatus(Integer studentId, String status);
}
