package com.example.task.repository.custom.impl;

import com.example.task.entity.Department;
import com.example.task.entity.Department_;
import com.example.task.entity.School;
import com.example.task.entity.School_;
import com.example.task.exception.custom.DeleteOrUpdateException;
import com.example.task.exception.custom.InternalException;
import com.example.task.exception.custom.SchoolNotFoundException;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.DepartmentAddRequest;
import com.example.task.repository.custom.DepartmentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.*;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public DepartmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Department> findDepartments(DepartmentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = builder.createQuery(Department.class);
        Root<Department> departmentRoot = criteriaQuery.from(Department.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);
        Join<Department, School> joinSchoolToDepartment = departmentRoot.join(Department_.school);
        criteriaQuery.select(departmentRoot);
        Predicate dptNameRestriction = filter.getDptName() != null ? builder.like(departmentRoot.get(Department_.name),
                "%" + filter.getDptName() + "%") :
                builder.like(departmentRoot.get(Department_.name), "%");
        Predicate schoolNameRestriction = filter.getSchoolName() != null ? builder.like(schoolRoot.get(School_.name),
                "%" + filter.getDptName() + "%") :
                builder.like(schoolRoot.get(School_.name), "%");
        criteriaQuery.select(departmentRoot).where(
                builder.and(dptNameRestriction, schoolNameRestriction)
        ).distinct(true);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public void addDepartment(DepartmentAddRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<School> schoolCriteriaQuery = builder.createQuery(School.class);
        Root<School> schoolRoot = schoolCriteriaQuery.from(School.class);
        try {
            if (filter.getSchoolName() != null) {
                schoolCriteriaQuery.select(schoolRoot)
                        .where(builder.like(schoolRoot.get(School_.name), "%" + filter.getSchoolName() + "%"))
                        .distinct(true);
                School school = entityManager.createQuery(schoolCriteriaQuery).getSingleResult();
                if (filter.getDptName() != null) {
                    Department department = new Department();
                    department.setName(filter.getDptName());
                    department.setSchool(school);
                    entityManager.persist(department);
                }
            }
        } catch (NoResultException e) {
            throw new SchoolNotFoundException();
        } catch (NonUniqueResultException e) {
            throw new InternalException();
        } catch (Exception e) {
            throw new RuntimeException("Save failed");
        }
    }

    @Override
    @Transactional
    public void deleteDepartment(DepartmentFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Department> departmentCriteriaDelete = builder.createCriteriaDelete(Department.class);
            Root<Department> departmentRoot = departmentCriteriaDelete.from(Department.class);
            Predicate dptNameRestriction;
            Predicate schoolNameRestriction;
            if (filter.getDptName() != null) {
                dptNameRestriction = builder.equal(departmentRoot.get(Department_.name), filter.getDptName());
                Join<Department, School> joinSchoolToDepartment;
                if (filter.getSchoolName() != null) {
                    Subquery<Department> subquery = departmentCriteriaDelete.subquery(Department.class);
                    Root<Department> departmentRoot2 = subquery.from(Department.class);
                    subquery.select(departmentRoot2);
                    joinSchoolToDepartment = departmentRoot2.join(Department_.school);
                    schoolNameRestriction = builder.equal(joinSchoolToDepartment.get(School_.name), filter.getSchoolName());
                    subquery.where(builder.and(dptNameRestriction, schoolNameRestriction));
                    departmentCriteriaDelete.where(departmentRoot.in(subquery));
                    entityManager.createQuery(departmentCriteriaDelete).executeUpdate();
                    return;
                }
                departmentCriteriaDelete.where(dptNameRestriction);
                entityManager.createQuery(departmentCriteriaDelete).executeUpdate();
            }
        } catch (NonUniqueResultException e) {
            throw new InternalException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeleteOrUpdateException();
        }
    }

    @Override
    @Transactional
    public void updateDepartment(DepartmentAddRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Department> departmentCriteriaUpdate = builder.createCriteriaUpdate(Department.class);
            Root<Department> departmentRoot = departmentCriteriaUpdate.from(Department.class);
            Predicate dptNameRestriction;
            Predicate schoolNameRestriction;
            if (filter.getDepartmentFilter().getDptName() != null) {
                dptNameRestriction = builder.equal(departmentRoot.get(Department_.name), filter.getDepartmentFilter().getDptName());
                Join<Department, School> joinSchoolToDepartment;
                if (filter.getDepartmentFilter().getSchoolName() != null) {
                    Subquery<Department> subquery = departmentCriteriaUpdate.subquery(Department.class);
                    Root<Department> departmentRoot2 = subquery.from(Department.class);
                    subquery.select(departmentRoot2);
                    joinSchoolToDepartment = departmentRoot2.join(Department_.school);
                    schoolNameRestriction = builder.equal(joinSchoolToDepartment.get(School_.name), filter.getDepartmentFilter().getSchoolName());
                    subquery.where(builder.and(dptNameRestriction, schoolNameRestriction));
                    departmentCriteriaUpdate.where(departmentRoot.in(subquery));
                }
                departmentCriteriaUpdate.where(dptNameRestriction);
                if (filter.getUpdates() != null) {
                    if (filter.getUpdates().getDptName() != null) {
                        departmentCriteriaUpdate.set(Department_.name, filter.getUpdates().getDptName());
                    }
                    if (filter.getUpdates().getSchoolName() != null) {
                        CriteriaQuery<School> schoolCriteriaQuery = builder.createQuery(School.class);
                        Root<School> schoolRoot = schoolCriteriaQuery.from(School.class);
                        schoolCriteriaQuery.select(schoolRoot)
                                .where(builder.equal(schoolRoot.get(School_.name), filter.getUpdates().getSchoolName()))
                                .distinct(true);
                        School school = entityManager.createQuery(schoolCriteriaQuery).getSingleResult();
                        departmentCriteriaUpdate.set(Department_.school, school);
                    }
                    entityManager.createQuery(departmentCriteriaUpdate).executeUpdate();
                }
            }
        } catch (NonUniqueResultException e) {
            throw new InternalException();
        } catch (Exception e) {
            throw new DeleteOrUpdateException();
        }
    }
}
