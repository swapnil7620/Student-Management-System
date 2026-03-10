package com.billing.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="payment_details")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer paymentId;
    @Column(nullable = false)
    private String paymentMethod;
    @Column(nullable = false, unique = true)
    private String transactionId;
}