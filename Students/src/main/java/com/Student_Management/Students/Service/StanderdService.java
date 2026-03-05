package com.Student_Management.Students.Service;

import com.Student_Management.Students.Enity.Standerd;
import com.Student_Management.Students.Repository.StanderdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StanderdService {


    private final StanderdRepository standerdRepository;

    public StanderdService(StanderdRepository standerdRepository) {
        this.standerdRepository = standerdRepository;
    }

    // CREATE
    public Standerd createStanderd(Standerd standerd) {
//        if (standerdRepository.existsByStandard(standerd.getStandard()))
//        {
//            throw new IllegalArgumentException("Standard already exists");
//        }

        return standerdRepository.save(standerd);
    }

    // GET ALL
    public List<Standerd> getAllStanderds() {
        return standerdRepository.findAll();
    }

    // GET BY Standard
    public Standerd getStanderdByStandard(Integer standard ) {
        return standerdRepository.findByStandard(standard)
                .orElseThrow(() -> new RuntimeException("Standerd not found : " + standard));
    }

    // DELETE
    public void deleteStanderd(Integer standard) {

        Standerd standerd = standerdRepository.findByStandard(standard)
                .orElseThrow(() -> new RuntimeException("Standerd not found : " + standard));

        standerdRepository.delete(standerd);
    }

}
