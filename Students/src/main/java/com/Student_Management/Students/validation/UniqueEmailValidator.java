package com.Student_Management.Students.validation;

import com.Student_Management.Students.Repository.StudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if ( email== null) {
            return true;
        }

        return !studentRepository.existsByEmail(email);
    }
}
*/