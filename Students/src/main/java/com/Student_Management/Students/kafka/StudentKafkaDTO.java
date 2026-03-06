package com.Student_Management.Students.kafka;



import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentKafkaDTO implements Serializable {

    private String name;
    private String email;
    private String phone;
    private Integer divisionCode;
    private Integer standerdStandard;

}