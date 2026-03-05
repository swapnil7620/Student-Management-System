package com.Student_Management.Students.Controller;




import com.Student_Management.Students.DTO.*;
import com.Student_Management.Students.Service.DivisionService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/division")
public class DivisionController {

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping("/create")
    public ResponseEntity<DivisionResponseDTO> create(
            @Valid @RequestBody DivisionRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(divisionService.create(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DivisionResponseDTO>> getAll() {
        return ResponseEntity.ok(divisionService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<DivisionResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(divisionService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        divisionService.delete(id);
        return ResponseEntity.ok("Division deleted successfully");
    }
}