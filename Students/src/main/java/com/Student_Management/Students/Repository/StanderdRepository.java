package com.Student_Management.Students.Repository;

import com.Student_Management.Students.Enity.Division;
import com.Student_Management.Students.Enity.Standerd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StanderdRepository  extends JpaRepository<Standerd,Integer> {
    Optional<Standerd> findByStandard(Integer Standard);
    boolean existsByStandard(Integer standard);
}
