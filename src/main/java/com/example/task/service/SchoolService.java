package com.example.task.service;

import com.example.task.entity.School;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;
import com.example.task.json.responses.SchoolDTO;

import java.util.List;

public interface SchoolService extends Service<School> {
    List<SchoolDTO> findSchoolsByName(SchoolFilterRequest filter);

    int deleteSchoolByName(SchoolFilterRequest filter);

    int updateSchoolByName(SchoolAddRequest filter);
}
