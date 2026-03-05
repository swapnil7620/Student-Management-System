package com.Student_Management.Students.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DivisionRequestDTO {

    @NotNull(message = "Division code is required")
    @Positive(message = "Code must be positive")
    @Min(value = 201, message = "Minimum standard is 201")
    private Integer code;

    @NotBlank(message = "Division name is required")
    @Pattern(regexp = "A|B|C", message = "Division must be A, B or C")
    private String name;

    @NotNull(message = "Standard ID is required")
    private Integer standard;
}
