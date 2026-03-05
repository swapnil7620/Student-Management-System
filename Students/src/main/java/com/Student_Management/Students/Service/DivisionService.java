package com.Student_Management.Students.Service;

import com.Student_Management.Students.Repository.DivisionRepository;
import com.Student_Management.Students.Repository.StanderdRepository;
import com.Student_Management.Students.DTO.*;
import com.Student_Management.Students.Enity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionService {

    private final DivisionRepository divisionRepository;
    private final StanderdRepository standerdRepository;

    public DivisionService(DivisionRepository divisionRepository,
                           StanderdRepository standerdRepository) {
        this.divisionRepository = divisionRepository;
        this.standerdRepository = standerdRepository;
    }

    // CREATE
    public DivisionResponseDTO create(DivisionRequestDTO dto) {

        // 🔹 Fetch using STANDARD not ID
        Standerd standerd = standerdRepository
                .findByStandard(dto.getStandard())
                .orElseThrow(() -> new IllegalArgumentException("Standard not found"));
/*
        if (divisionRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Division code already exists");
        }
*/
        if (divisionRepository.existsByNameAndStanderd_Standard(dto.getName(), dto.getStandard())) {
            throw new IllegalArgumentException("Division already exists in this standard");
        }

        Division division = new Division();
        division.setCode(dto.getCode());
        division.setName(dto.getName());
        division.setStanderd(standerd);

        Division saved = divisionRepository.save(division);

        return new DivisionResponseDTO(
                saved.getId(),
                saved.getCode(),
                saved.getName(),
                saved.getStanderd().getStandard(), // return standard value
                saved.getCreatedAt()
        );
    }

    // GET ALL
    public List<DivisionResponseDTO> getAll() {
        return divisionRepository.findAll()
                .stream()
                .map(d -> new DivisionResponseDTO(
                        d.getId(),
                        d.getCode(),
                        d.getName(),
                        d.getStanderd().getStandard(),
                        d.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    // GET BY ID
    public DivisionResponseDTO getById(Integer id) {

        Division division = divisionRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Division not found with id: " + id));

        return new DivisionResponseDTO(
                division.getId(),
                division.getCode(),
                division.getName(),
                division.getStanderd().getStandard(),
                division.getCreatedAt()
        );
    }

    // DELETE
    public void delete(Integer id) {

        Division division = divisionRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Division not found with id: " + id));

        divisionRepository.delete(division);
    }
}