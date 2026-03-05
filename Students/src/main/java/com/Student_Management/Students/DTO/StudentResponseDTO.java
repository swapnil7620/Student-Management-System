package com.Student_Management.Students.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Boolean active;

    private Integer divisionId;
    private  Integer divisionCode;

    private Integer standerdId;
    private  Integer standerdStandard;
}
