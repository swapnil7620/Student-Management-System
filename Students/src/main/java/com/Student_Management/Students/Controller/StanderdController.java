package com.Student_Management.Students.Controller;


import com.Student_Management.Students.DTO.StanderdRequest;
import com.Student_Management.Students.DTO.StanderdResponse;
import com.Student_Management.Students.Enity.Standerd;
import com.Student_Management.Students.Service.StanderdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/standerd")
public class StanderdController {
    private final StanderdService standerdService;

    public StanderdController(StanderdService standerdService) {

        this.standerdService = standerdService;
    }
    @PostMapping("/create")
    public ResponseEntity<StanderdResponse> create(
            @Valid @RequestBody StanderdRequest request) {

        Standerd standerd = new Standerd();
        standerd.setStandard(request.getStandard());

        Standerd saved = standerdService.createStanderd(standerd);

        StanderdResponse response =
                new StanderdResponse(
                        saved.getId(),
                        saved.getStandard(),
                        saved.getCreatedAt()
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<StanderdResponse>> getAll() {

        List<StanderdResponse> list = standerdService.getAllStanderds()
                .stream()
                .map(s -> new StanderdResponse(
                        s.getId(),
                        s.getStandard(),
                        s.getCreatedAt()
                ))
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByStandard/{standard}")
    public ResponseEntity<StanderdResponse> getById(@PathVariable Integer standard) {

        Standerd standerd = standerdService.getStanderdByStandard(standard);

        StanderdResponse response =
                new StanderdResponse(
                        standerd.getId(),
                        standerd.getStandard(),
                        standerd.getCreatedAt()
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{standard}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer standard) {

        standerdService.deleteStanderd(standard);

        return ResponseEntity.ok(
                Map.of("message", "Standerd deleted successfully")
        );
    }
}



