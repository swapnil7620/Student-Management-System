package com.Student_Management.Students.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StanderdRequest {


    @NotNull(message = "Standard is required")
    @Min(value = 1, message = "Minimum standard is 1")
    @Max(value = 12, message = "Maximum standard is 12")
    private Integer standard;

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }
}
