package com.example.task.controller;

import com.example.task.entity.School;
import com.example.task.json.filters.SchoolFilterRequest;
import com.example.task.json.responses.SchoolDTO;
import com.example.task.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public ResponseEntity<List<SchoolDTO>> getSchools(@Valid @RequestBody SchoolFilterRequest req) {
        List<SchoolDTO> schoolDTOS = schoolService.findSchoolsByName(req)
                .stream()
                .map(SchoolDTO::toSchoolDTO)
                .collect(Collectors.toList());
        return schoolDTOS.size() > 0 ? ResponseEntity.ok().body(schoolDTOS) : ResponseEntity.notFound().build();
    }

    @PostMapping("/schools")
    public ResponseEntity<?> addSchool(@Valid @RequestBody SchoolFilterRequest req) {
        School school = new School(req.getSchoolName());
        schoolService.save(school);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/schools")
    public ResponseEntity<?> deleteSchool(@Valid @RequestBody SchoolFilterRequest req) {
        int status = schoolService.deleteSchoolByName(req);
        return status > 0 ? ResponseEntity.status(204).build() : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/schools")
    public ResponseEntity<?> updateSchoolByName(@Valid @RequestBody SchoolFilterRequest req) {
        if (req.getUpdates() == null) {
            return ResponseEntity.badRequest().build();
        }
        int status = schoolService.updateSchoolByName(req);
        return status > 0 ?  ResponseEntity.status(204).build() : ResponseEntity.badRequest().build();
    }
}
