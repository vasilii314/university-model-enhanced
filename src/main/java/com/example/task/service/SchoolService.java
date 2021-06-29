package com.example.task.service;

import com.example.task.entity.School;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;

import java.util.List;

public interface SchoolService extends Service<School> {
    List<School> findSchoolsByName(SchoolFilterRequest filter);
    int deleteSchoolByName(SchoolFilterRequest filter);
    int updateSchoolByName(SchoolAddRequest filter);
}
