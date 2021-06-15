package com.example.task.service;

import com.example.task.criteria.SearchCriteria;
import com.example.task.entity.School;
import com.example.task.entity.School_;
import com.example.task.json.filters.SchoolFilterRequest;
import com.example.task.operations.SearchOperation;
import com.example.task.repository.SchoolRepository;
import com.example.task.specification.SchoolSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;

    private EntityManager entityManager;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, EntityManager entityManager) {
        this.schoolRepository = schoolRepository;
        this.entityManager = entityManager;
    }
    
    public List<School> criteriaFindAll() {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<School> criteriaQuery = builder.createQuery(School.class);
    	Root<School> root = criteriaQuery.from(School.class);
    	criteriaQuery.select(root);
    	return entityManager.createQuery(criteriaQuery).getResultList();
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
        SchoolSpecification specification = new SchoolSpecification();
        specification.add(new SearchCriteria("name", filter.getSchoolName(), SearchOperation.MATCH));
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<School> criteriaQuery = builder.createQuery(School.class);
        Root<School> root = criteriaQuery.from(School.class);
        criteriaQuery.where(specification.toPredicate(root, criteriaQuery, builder));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public int deleteSchoolByName(SchoolFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<School> criteriaDelete = builder.createCriteriaDelete(School.class);
        Root<School> root = criteriaDelete.from(School.class);
        criteriaDelete.where(builder.equal(root.get(School_.name), filter.getSchoolName()));
        return entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    @Transactional
    public int updateSchoolByName(SchoolFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<School> criteriaUpdate = builder.createCriteriaUpdate(School.class);
        Root<School> root = criteriaUpdate.from(School.class);
        criteriaUpdate.set(School_.name, filter.getUpdates().getSchoolName());
        criteriaUpdate.where(builder.equal(root.get(School_.name), filter.getSchoolName()));
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
}
