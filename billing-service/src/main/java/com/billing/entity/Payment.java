package com.billing.entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer studentId;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
