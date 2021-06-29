package com.example.task.repository;

import com.example.task.criteria.SearchCriteria;
import com.example.task.entity.School;
import com.example.task.entity.School_;
import com.example.task.json.requests.filters.SchoolFilterRequest;
import com.example.task.json.requests.save_or_update.SchoolAddRequest;
import com.example.task.operations.SearchOperation;
import com.example.task.repository.custom.SchoolRepositoryCustom;
import com.example.task.specification.SchoolSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class SchoolRepositoryImpl implements SchoolRepositoryCustom {

    private EntityManager entityManager;

    @Autowired
    public SchoolRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
    public int updateSchoolByName(SchoolAddRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<School> criteriaUpdate = builder.createCriteriaUpdate(School.class);
        Root<School> root = criteriaUpdate.from(School.class);
        criteriaUpdate.set(School_.name, filter.getUpdates().getSchoolName());
        criteriaUpdate.where(builder.equal(root.get(School_.name), filter.getSchoolFilter().getSchoolName()));
        return entityManager.createQuery(criteriaUpdate).executeUpdate();
    }
}
