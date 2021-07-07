package com.example.task.repository.custom;

import com.example.task.entity.School;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepositoryCustom {
    List<School> findSchoolsByName(SchoolFilterRequest filter);

    int deleteSchoolByName(SchoolFilterRequest filter);

    int updateSchoolByName(SchoolAddRequest filter);
}
