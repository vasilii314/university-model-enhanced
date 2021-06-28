package com.example.task.service;

import com.example.task.entity.School;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.repository.default_repos.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;

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
    public List<School> findSchoolsByName(SchoolFilterRequest filter) {
        return schoolRepository.findSchoolsByName(filter);
    }

    @Override
    public int deleteSchoolByName(SchoolFilterRequest filter) {
        return schoolRepository.deleteSchoolByName(filter);
    }

    @Override
    public int updateSchoolByName(SchoolFilterRequest filter) {
        return schoolRepository.updateSchoolByName(filter);
    }
}
