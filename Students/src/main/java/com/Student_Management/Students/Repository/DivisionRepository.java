package com.Student_Management.Students.Repository;

import com.Student_Management.Students.Enity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DivisionRepository extends JpaRepository<Division,Integer> {
    Optional<Division> findByCode(Integer code);
 //   boolean existsByCode(Integer code);
    boolean existsByNameAndStanderd_Standard(String name, Integer standard);;
}
