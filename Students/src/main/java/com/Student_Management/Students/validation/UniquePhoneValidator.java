package com.Student_Management.Students.validation;

import com.Student_Management.Students.Repository.StudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if(phone==null){
            return true;
        }
        return !studentRepository.existsByPhone(phone);
    }
}
*/