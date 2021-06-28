package com.example.task.repository.custom;

import com.example.task.entity.School;
import com.example.task.json.filters.SchoolFilterRequest;
import com.example.task.service.Service;

import java.util.List;

public interface SchoolRepositoryCustom {
    List<School> findSchoolsByName(SchoolFilterRequest filter);
    int deleteSchoolByName(SchoolFilterRequest filter);
    int updateSchoolByName(SchoolFilterRequest filter);
}
