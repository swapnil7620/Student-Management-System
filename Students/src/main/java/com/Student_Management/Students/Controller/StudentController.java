package com.Student_Management.Students.Controller;


import com.Student_Management.Students.DTO.StudentRequestDTO;
import com.Student_Management.Students.DTO.StudentResponseDTO;
import com.Student_Management.Students.DTO.StudentUpdateDTO;
import com.Student_Management.Students.Enity.Student;
import com.Student_Management.Students.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {


    private  final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    // CREATE
    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO> create(
            @Valid @RequestBody StudentRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.create(dto));
    }

// GET All
    @GetMapping("/getAll")
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    // GET BY ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }



    // update student
    @PatchMapping("/update/{id}")
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody StudentUpdateDTO dto) {

        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }


    // soft delete Student
    @DeleteMapping("/softDelete/{id}")
    public ResponseEntity<StudentResponseDTO> softDelete(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.softDelete(id));
    }

    // Restore Student
    @PatchMapping("/restore/{id}")
    public ResponseEntity<StudentResponseDTO> restoreStudent(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.restoreStudent(id));
    }
}
