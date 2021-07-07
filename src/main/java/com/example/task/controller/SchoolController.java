package com.example.task.controller;

import com.example.task.entity.School;
import com.example.task.exception.custom.UpdatesObjectMissingException;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;
import com.example.task.json.responses.SchoolDTO;
import com.example.task.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/schools")
    public ResponseEntity<List<SchoolDTO>> getSchools(@Valid @RequestBody SchoolFilterRequest req) {
        return ResponseEntity.ok().body(schoolService.findSchoolsByName(req));
    }

    @PostMapping("/add-school")
    public ResponseEntity<SchoolDTO> addSchool(@Valid @RequestBody SchoolAddRequest req) {
        School school = new School(req.getSchoolName());
        schoolService.save(school);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/schools")
    public ResponseEntity<SchoolDTO> deleteSchool(@Valid @RequestBody SchoolFilterRequest req) {
        int status = schoolService.deleteSchoolByName(req);
        return status > 0 ? ResponseEntity.status(204).build() : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/schools")
    public ResponseEntity<SchoolDTO> updateSchoolByName(@Valid @RequestBody SchoolAddRequest req) {
        if (req.getUpdates() == null) {
            throw new UpdatesObjectMissingException();
        }
        int status = schoolService.updateSchoolByName(req);
        return status > 0 ? ResponseEntity.status(204).build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/schools/{id}")
    public ResponseEntity<SchoolDTO> deleteSchoolById(@PathVariable Integer id) {
        schoolService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
