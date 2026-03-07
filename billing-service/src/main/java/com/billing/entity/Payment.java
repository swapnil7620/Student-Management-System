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

    private Integer studentId;

    private Double amount;

    private String status;

    private LocalDateTime createdDate;

    @PrePersist
    public void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
