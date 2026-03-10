package com.billing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    @NotNull(message = "StudentId is required")
    private Integer studentId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 10")
    private Double amount;
}
