package com.example.task.service;

import com.example.task.entity.CourseType;
import com.example.task.repository.default_repos.CourseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {

    private CourseTypeRepository courseTypeRepository;

    @Autowired
    public CourseTypeServiceImpl(CourseTypeRepository courseTypeRepository) {
        this.courseTypeRepository = courseTypeRepository;
    }

    @Override
    public List<CourseType> findAll() {
        List<CourseType> courseTypes = new ArrayList<>();
        courseTypeRepository.findAll().forEach(courseTypes::add);
        return courseTypes;
    }

    @Override
    public Optional<CourseType> findById(int id) {
        return courseTypeRepository.findById(id);
    }

    @Override
    public void save(CourseType courseType) {
        courseTypeRepository.save(courseType);
    }

    @Override
    public void deleteById(int id) {
        courseTypeRepository.deleteById(id);
    }
}
