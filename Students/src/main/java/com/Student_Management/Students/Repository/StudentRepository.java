package com.Student_Management.Students.Repository;

import com.Student_Management.Students.Enity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    // first way to solve N+1 problems
/*
    @Query(value = """
    SELECT s FROM Student s
    JOIN FETCH s.division
    JOIN FETCH s.standerd
    """)
Page<Student> findAllWithRelations(Pageable pageable);


 */
    // second way to solve N+1 problem
     @EntityGraph(attributePaths = {"division", "standerd"})
     @Query("SELECT s FROM Student s")
    Page<Student> findAllWithRelations(Pageable pageable);
}
