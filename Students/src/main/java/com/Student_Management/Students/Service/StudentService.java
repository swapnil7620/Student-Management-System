package com.Student_Management.Students.Service;


import com.Student_Management.Students.DTO.StudentRequestDTO;
import com.Student_Management.Students.DTO.StudentResponseDTO;
import com.Student_Management.Students.DTO.StudentUpdateDTO;
import com.Student_Management.Students.Enity.Division;
import com.Student_Management.Students.Enity.Standerd;
import com.Student_Management.Students.Enity.Student;
import com.Student_Management.Students.Repository.DivisionRepository;
import com.Student_Management.Students.Repository.StanderdRepository;
import com.Student_Management.Students.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private  final StudentRepository studentRepository;
    private  final StanderdRepository standerdRepository;
    private  final DivisionRepository divisionRepository;

    public StudentService(StudentRepository studentRepository, StanderdRepository standerdRepository, DivisionRepository divisionRepository) {
        this.studentRepository = studentRepository;
        this.standerdRepository = standerdRepository;
        this.divisionRepository = divisionRepository;
    }
    // create
    public StudentResponseDTO create(StudentRequestDTO dto) {

        Standerd standerd = standerdRepository
                .findByStandard(dto.getStanderdStandard())
                .orElseThrow(() -> new IllegalArgumentException("Standard not found"));

        Division division = divisionRepository
                .findByCode(dto.getDivisionCode())
                .orElseThrow(() -> new IllegalArgumentException("Division code not found"));

        // Correct validation
        if (!division.getStanderd().getId().equals(standerd.getId())) {
            throw new IllegalArgumentException("Division does not belong to given Standard");
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setStanderd(standerd);
        student.setDivision(division);

        Student saved = studentRepository.save(student);

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getActive(),
                saved.getDivision().getId(),
                saved.getDivision().getCode(),
                saved.getStanderd().getId(),
                saved.getStanderd().getStandard()
        );
    }
  // get all student
    public List<StudentResponseDTO> getAll() {

        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getPhone(),
                        student.getActive(),
                        student.getDivision().getId(),
                        student.getDivision().getCode(),
                        student.getStanderd().getId(),
                        student.getStanderd().getStandard()
                ))
                .toList();
    }

    // get by ID
    public StudentResponseDTO getById(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found with id: " + id));

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getActive(),
                student.getDivision().getId(),
                student.getDivision().getCode(),
                student.getStanderd().getId(),
                student.getStanderd().getStandard()
        );
    }
// update student
public StudentResponseDTO updateStudent(Integer id, StudentUpdateDTO dto) {

    // Find student
    Student student = studentRepository.findById(id)
            .orElseThrow(() ->
                    new IllegalArgumentException("Student not found with id: " + id));

    // Update Name
    if (dto.getName() != null) {
        student.setName(dto.getName());
    }

    // Update Email
    if (dto.getEmail() != null) {

        if (studentRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException("Email already exists");
        }

        student.setEmail(dto.getEmail());
    }

    // Update Phone
    if (dto.getPhone() != null) {

        if (studentRepository.existsByPhoneAndIdNot(dto.getPhone(), id)) {
            throw new IllegalArgumentException("Phone already exists");
        }

        student.setPhone(dto.getPhone());
    }

    // Get current values
    Standerd standerd = student.getStanderd();
    Division division = student.getDivision();

    // Update Standard
    if (dto.getStanderdStandard() != null) {

        standerd = standerdRepository.findById(dto.getStanderdStandard())
                .orElseThrow(() ->
                        new IllegalArgumentException("Standerd not found"));

        student.setStanderd(standerd);
    }

    // Update Division
    if (dto.getDivisionCode() != null) {

        division = divisionRepository.findByCode(dto.getDivisionCode())
                .orElseThrow(() ->
                        new IllegalArgumentException("Division not found"));

        student.setDivision(division);
    }

    // Validate relationship ONLY if division updated
    if (dto.getDivisionCode() != null &&
            !division.getStanderd().getId().equals(standerd.getId())) {

        throw new IllegalArgumentException("Division does not belong to the given Standerd");
    }

    // Save updated student
    Student saved = studentRepository.save(student);

    // Convert to ResponseDTO
    return new StudentResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            saved.getPhone(),
            saved.getActive(),
            saved.getDivision().getId(),
            saved.getDivision().getCode(),
            saved.getStanderd().getId(),
            saved.getStanderd().getStandard()
    );
}
//  soft delete
public StudentResponseDTO softDelete(Integer id) {

    Student student = studentRepository.findById(id)
            .orElseThrow(() ->
                    new IllegalArgumentException("Student not found with id: " + id));

    if (!student.getActive()) {
        throw new IllegalArgumentException("Student already deleted");
    }

    student.setActive(false);

    Student saved = studentRepository.save(student);

    return new StudentResponseDTO(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            saved.getPhone(),
            saved.getActive(),
            saved.getDivision().getId(),
            saved.getDivision().getCode(),
            saved.getStanderd().getId(),
            saved.getStanderd().getStandard()
    );
}
    public StudentResponseDTO restoreStudent(Integer id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found with id: " + id));

        if (student.getActive()) {
            throw new IllegalArgumentException("Student already active");
        }

        student.setActive(true);

        Student saved = studentRepository.save(student);

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getActive(),
                saved.getDivision().getId(),
                saved.getDivision().getCode(),
                saved.getStanderd().getId(),
                saved.getStanderd().getStandard()
        );
    }

}
