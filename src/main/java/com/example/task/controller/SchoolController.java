package com.example.task.controller;

import com.example.task.entity.School;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;
import com.example.task.json.responses.SchoolDTO;
import com.example.task.repository.default_repos.SchoolRepository;
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

    @PostMapping("/schools")
    public ResponseEntity<List<SchoolDTO>> getSchoolsCriteria(@Valid @RequestBody SchoolFilterRequest req) {
        List<SchoolDTO> schoolDTOS = schoolService.findSchoolsByName(req)
                .stream()
                .map(SchoolDTO::toSchoolDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(schoolDTOS);
    }

    @PostMapping("/add-school")
    public ResponseEntity<SchoolDTO> addSchoolCriteria(@Valid @RequestBody SchoolAddRequest req) {
        School school = new School(req.getSchoolName());
        schoolService.save(school);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/schools")
    public ResponseEntity<SchoolDTO> deleteSchoolCriteria(@Valid @RequestBody SchoolFilterRequest req) {
        int status = schoolService.deleteSchoolByName(req);
        return status > 0 ? ResponseEntity.status(204).build() : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/schools")
    public ResponseEntity<SchoolDTO> updateSchoolByName(@Valid @RequestBody SchoolAddRequest req) {
        if (req.getUpdates() == null) {
            return ResponseEntity.badRequest().build();
        }
        int status = schoolService.updateSchoolByName(req);
        return status > 0 ?  ResponseEntity.status(204).build() : ResponseEntity.badRequest().build();
    }
}
