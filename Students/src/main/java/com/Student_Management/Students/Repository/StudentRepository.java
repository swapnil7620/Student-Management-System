package com.Student_Management.Students.Repository;

import com.Student_Management.Students.Enity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository  extends JpaRepository<Student,Integer> {

//    boolean existsByEmail(String email);

//    boolean existsByPhone(String phone);

    Optional<Student> findByEmail(String email);
    Optional<Student> findByPhone(String phone);
    boolean existsByEmailAndIdNot(String email, Integer id);

    boolean existsByPhoneAndIdNot(String phone, Integer id);
}
