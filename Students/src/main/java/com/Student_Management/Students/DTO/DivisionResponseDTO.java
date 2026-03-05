package com.Student_Management.Students.DTO;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DivisionResponseDTO {

    private Integer id;
    private Integer code;
    private String name;
    private Integer standerdId;
    private LocalDateTime createdAt;
}