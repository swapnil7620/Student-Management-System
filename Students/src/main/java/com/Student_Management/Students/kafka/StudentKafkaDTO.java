package com.Student_Management.Students.kafka;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentKafkaDTO implements Serializable {
    private Integer studentId;
    private String name;
    private String email;
    private String phone;
    private Integer divisionCode;
    private Integer standerdStandard;


}