package com.example.task.service;

import com.example.task.entity.*;
import com.example.task.json.filters.EmployeeAddRequest;
import com.example.task.json.filters.EmployeeFilterRequest;
import com.example.task.repository.HumanInUniversityRepository;
import com.example.task.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HumanServiceImpl implements HumanService {

    private HumanRepository humanRepository;

    private EntityManager entityManager;

    private HumanInUniversityRepository humanInUniversityRepository;

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepository, EntityManager entityManager, HumanInUniversityRepository humanInUniversityRepository) {
        this.humanRepository = humanRepository;
        this.entityManager = entityManager;
        this.humanInUniversityRepository = humanInUniversityRepository;
    }

    @Override
    public List<Human> findAll() {
        List<Human> people = new ArrayList<>();
        humanRepository.findAll().forEach(people::add);
        return people;
    }

    @Override
    public Optional<Human> findById(int id) {
        return humanRepository.findById(id);
    }

    @Override
    public void save(Human human) {
        humanRepository.save(human);
    }

    @Override
    public void deleteById(int id) {
        humanRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Human> findEmployeesCriteria(EmployeeFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Human> criteriaQuery = builder.createQuery(Human.class);
        Root<Human> humanRoot = criteriaQuery.from(Human.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        Join<Human, HumanInUniversity> join1 = humanRoot.join(Human_.roles);
        Join<HumanInUniversity, Role> join2 = join1.join(HumanInUniversity_.roles);
        Predicate fullNameRestriction = filter.getEmployeeFullName() != null ?
                builder.like(humanRoot.get(Human_.fullName), "%" + filter.getEmployeeFullName() + "%") :
                builder.like(humanRoot.get(Human_.fullName), "%");
        Predicate roleRestriction = filter.getRole() != null &&
                (filter.getRole().equals(RoleEnum.POSTGRADUATE) || filter.getRole().equals(RoleEnum.PROFESSOR)) ?
                        builder.equal(roleRoot.get(Role_.roleDescription), filter.getRole()) :
                        roleRoot.get(Role_.roleDescription).in(RoleEnum.PROFESSOR, RoleEnum.POSTGRADUATE, RoleEnum.STUDENT);
        criteriaQuery.select(humanRoot).distinct(true);
        criteriaQuery.where(builder.and(fullNameRestriction, roleRestriction));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void addEmployeeCriteria(EmployeeAddRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<Department> dptRoot = criteriaQuery.from(Department.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        Join<Department, School> join = dptRoot.join(Department_.school);
        criteriaQuery.multiselect(dptRoot, schoolRoot, roleRoot);
        criteriaQuery.where(
                builder.and(
                        builder.equal(dptRoot.get(Department_.name), filter.getDptName()),
                        builder.equal(schoolRoot.get(School_.name), filter.getSchoolName()),
                        builder.equal(roleRoot.get(Role_.roleDescription), filter.getRole())
                        ));
        Tuple schoolDepartmentRole = entityManager.createQuery(criteriaQuery).getSingleResult();
//        CriteriaQuery<Role> roleCriteriaQuery = builder.createQuery(Role.class)

        Human employee = new Human();
        HumanInUniversity humanInUniversity = new HumanInUniversity();
        humanInUniversity.setHuman(employee);
        humanInUniversity.setRoles(Arrays.asList(schoolDepartmentRole.get(roleRoot)));
        humanInUniversity.setDepartments(Arrays.asList(schoolDepartmentRole.get(dptRoot)));
        employee.setFullName(filter.getEmployeeFullName());
        employee.setBirthDate(LocalDate.parse(filter.getBirthDate()));
//        employee.setRoles(Arrays.asList(schoolDepartmentRole.get(roleRoot).getRoleDescription()));
        humanRepository.save(employee);
//        humanInUniversityRepository.save(humanInUniversity);


        System.out.println(schoolDepartmentRole.get(schoolRoot).getName() + " "
                + schoolDepartmentRole.get(dptRoot).getName()
        + schoolDepartmentRole.get(roleRoot).getRoleDescription());
    }
}
