package com.example.task.service;

import com.example.task.entity.Department;
import com.example.task.entity.Department_;
import com.example.task.entity.School;
import com.example.task.entity.School_;
import com.example.task.json.filters.DepartmentFilterRequest;
import com.example.task.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private EntityManager entityManager;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EntityManager entityManager) {
        this.departmentRepository = departmentRepository;
        this.entityManager = entityManager;
    }

    public List<Department> criteriaFindAll() {
    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<School> criteriaQuery = builder.createQuery(School.class);
    	Root<School> root = criteriaQuery.from(School.class);
    	criteriaQuery.select(root);
    	return null;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        departmentRepository.findAll().forEach(departments::add);
        return departments;
    }

    @Override
    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> findDepartmentsCriteria(DepartmentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = builder.createQuery(Department.class);
        Root<Department> departmentRoot = criteriaQuery.from(Department.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);
        Join<Department, School> join = departmentRoot.join(Department_.school);
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
    public void addDepartment(DepartmentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<School> schoolCriteriaQuery = builder.createQuery(School.class);
        Root<School> schoolRoot = schoolCriteriaQuery.from(School.class);
        if (filter.getSchoolName() != null) {
            schoolCriteriaQuery.select(schoolRoot)
                    .where(builder.like(schoolRoot.get(School_.name), "%" + filter.getSchoolName() + "%"))
                    .distinct(true);
            School school = entityManager.createQuery(schoolCriteriaQuery).getSingleResult();
            if (filter.getDptName() != null) {
                Department department = new Department();
                department.setName(filter.getDptName());
                department.setSchool(school);
                departmentRepository.save(department);
            }
        }
    }

    @Override
    @Transactional
    public void deleteDepartment(DepartmentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Department> departmentCriteriaDelete = builder.createCriteriaDelete(Department.class);
        Root<Department> departmentRoot = departmentCriteriaDelete.from(Department.class);
        Predicate dptNameRestriction;
        Predicate schoolNameRestriction;
        if (filter.getDptName() != null) {
            dptNameRestriction = builder.equal(departmentRoot.get(Department_.name), filter.getDptName());
            Join<Department, School> join;
            if (filter.getSchoolName() != null) {
                Subquery<Department> subquery = departmentCriteriaDelete.subquery(Department.class);
                Root<Department> departmentRoot2 = subquery.from(Department.class);
                subquery.select(departmentRoot2);
                join = departmentRoot2.join(Department_.school);
                schoolNameRestriction = builder.equal(join.get(School_.name), filter.getSchoolName());
                subquery.where(builder.and(dptNameRestriction, schoolNameRestriction));
                departmentCriteriaDelete.where(departmentRoot.in(subquery));
                entityManager.createQuery(departmentCriteriaDelete).executeUpdate();
                return;
            }
            departmentCriteriaDelete.where(dptNameRestriction);
            entityManager.createQuery(departmentCriteriaDelete).executeUpdate();
        }
    }

    @Override
    @Transactional
    public void updateDepartment(DepartmentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Department> departmentCriteriaUpdate = builder.createCriteriaUpdate(Department.class);
        Root<Department> departmentRoot = departmentCriteriaUpdate.from(Department.class);
        Predicate dptNameRestriction;
        Predicate schoolNameRestriction;
        if (filter.getDptName() != null) {
            dptNameRestriction = builder.equal(departmentRoot.get(Department_.name), filter.getDptName());
            Join<Department, School> join;
            if (filter.getSchoolName() != null) {
                Subquery<Department> subquery = departmentCriteriaUpdate.subquery(Department.class);
                Root<Department> departmentRoot2 = subquery.from(Department.class);
                subquery.select(departmentRoot2);
                join = departmentRoot2.join(Department_.school);
                schoolNameRestriction = builder.equal(join.get(School_.name), filter.getSchoolName());
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
    }
}
