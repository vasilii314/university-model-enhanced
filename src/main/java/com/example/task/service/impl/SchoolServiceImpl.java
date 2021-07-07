package com.example.task.service.impl;

import com.example.task.entity.School;
import com.example.task.exception.custom.DeleteOrUpdateException;
import com.example.task.exception.custom.SchoolNotFoundException;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;
import com.example.task.json.responses.SchoolDTO;
import com.example.task.repository.SchoolRepository;
import com.example.task.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> findAll() {
        List<School> schools = new ArrayList<>();
        schoolRepository.findAll().forEach(schools::add);
        return schools;
    }

    @Override
    public Optional<School> findById(int id) {
        return schoolRepository.findById(id);
    }

    @Override
    public void save(School school) {
        schoolRepository.save(school);
    }

    @Override
    public void deleteById(int id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public List<SchoolDTO> findSchoolsByName(SchoolFilterRequest filter) {
        return schoolRepository
                .findSchoolsByName(filter)
                .stream()
                .map(SchoolDTO::toSchoolDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int deleteSchoolByName(SchoolFilterRequest filter) {
        int status = schoolRepository.deleteSchoolByName(filter);
        if (status == 0) {
            throw new DeleteOrUpdateException();
        }
        return status;
    }

    @Override
    public int updateSchoolByName(SchoolAddRequest filter) {
        int status = schoolRepository.updateSchoolByName(filter);
        if (status == 0) {
            throw new DeleteOrUpdateException();
        }
        return status;
    }
}
