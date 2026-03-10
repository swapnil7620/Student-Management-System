package com.billing.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetailsDTO {
    @NotNull(message = "PaymentId is required")
    private Integer paymentId;
    @NotNull(message = "PaymentId is required")
    private String paymentMethod;
}
