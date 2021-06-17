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
        Join<Human, HumanInUniversity> join1 = humanRoot.join(Human_.occupations);
        Join<HumanInUniversity, Role> join2 = join1.join(HumanInUniversity_.role);
        Join<HumanInUniversity, Department> join3 = join1.join(HumanInUniversity_.department);
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
    @Transactional
    public void addEmployeeCriteria(EmployeeAddRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        CriteriaQuery<Role> roleCriteriaQuery = builder.createQuery(Role.class);
        Root<Department> dptRoot = criteriaQuery.from(Department.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);
        Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);

        roleCriteriaQuery.select(roleRoot);
        roleCriteriaQuery.where((
                builder.equal(roleRoot.get(Role_.roleDescription), filter.getRole())
        ));
        Role role = entityManager.createQuery(roleCriteriaQuery).getSingleResult();

        Join<Department, School> join = dptRoot.join(Department_.school, JoinType.INNER);
        criteriaQuery.multiselect(dptRoot, schoolRoot).distinct(true);
        criteriaQuery.where(
                builder.and(
                        builder.equal(dptRoot.get(Department_.name), filter.getDptName()),
                        builder.equal(schoolRoot.get(School_.name), filter.getSchoolName())
                        ));
        Tuple schoolDepartment = entityManager.createQuery(criteriaQuery).getSingleResult();

        Human human = new Human();
        human.setFullName(filter.getEmployeeFullName());
        human.setBirthDate(LocalDate.parse(filter.getBirthDate()));
        HumanInUniversity employee = new HumanInUniversity();
        employee.setHuman(human);
        employee.setRole(role);
        employee.setDepartment(schoolDepartment.get(dptRoot));
        humanInUniversityRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeOrStudent(EmployeeFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Human> criteriaDelete = builder.createCriteriaDelete(Human.class);
        Root<Human> humanRoot = criteriaDelete.from(Human.class);
        Subquery<Human> subquery = criteriaDelete.subquery(Human.class);
        Root<Human> humanRoot2 = subquery.from(Human.class);
        subquery.select(humanRoot2);
        Join<Human, HumanInUniversity> join1 = humanRoot2.join(Human_.occupations);
        Join<HumanInUniversity, Department> join2 = join1.join(HumanInUniversity_.department);
        Join<HumanInUniversity, Role> join3 = join1.join(HumanInUniversity_.role);
        Predicate fullNameRestriction = filter.getEmployeeFullName() != null ?
                builder.like(humanRoot2.get(Human_.fullName), "%" + filter.getEmployeeFullName() + "%") :
                builder.like(humanRoot2.get(Human_.fullName), "%");
        Predicate roleRestriction = filter.getRole() != null &&
                (filter.getRole().equals(RoleEnum.POSTGRADUATE) || filter.getRole().equals(RoleEnum.PROFESSOR)) ?
                builder.equal(join3.get(Role_.roleDescription), filter.getRole()) :
                join3.get(Role_.roleDescription).in(RoleEnum.PROFESSOR, RoleEnum.POSTGRADUATE, RoleEnum.STUDENT);
        Predicate dptRestriction =  filter.getDptName() != null ?
                builder.like(join2.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(join2.get(Department_.name), "%");
        Predicate birthDateUpperBound = filter.getBirthDateUpperBound() != null ?
                builder.lessThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateUpperBound())) :
                builder.lessThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.now());
        Predicate birthDateLowerBound = filter.getBirthDateLowerBound() != null ?
                builder.greaterThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateLowerBound())) :
                builder.greaterThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.parse("1800-01-01"));
        subquery.where(builder.and(fullNameRestriction, roleRestriction, dptRestriction, birthDateUpperBound, birthDateLowerBound));
        criteriaDelete.where(humanRoot.in(subquery));
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    @Override
    @Transactional
    public void updateEmployeeOrStudent(EmployeeFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Human> humanCriteriaQuery= builder.createQuery(Human.class);
        CriteriaQuery<Role> roleCriteriaQuery = builder.createQuery(Role.class);
        CriteriaQuery<Department> departmentCriteriaQuery = builder.createQuery(Department.class);
        Root<Human> humanRoot = humanCriteriaQuery.from(Human.class);
        Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);
        Root<Department> departmentRoot = departmentCriteriaQuery.from(Department.class);

        Role role = null;
        if (filter.getUpdates().getRole() != null) {
            Predicate roleRestriction = builder.equal(roleRoot.get(Role_.roleDescription), filter.getUpdates().getRole());
            roleCriteriaQuery.select(roleRoot).where(roleRestriction).distinct(true);
            role = entityManager.createQuery(roleCriteriaQuery).getSingleResult();
        }

        Department department = null;
        if (filter.getUpdates().getDptName() != null) {
            Predicate dptRestriction = filter.getUpdates().getDptName() != null ?
                    builder.like(departmentRoot.get(Department_.name), "%" + filter.getUpdates().getDptName() + "%") :
                    builder.like(departmentRoot.get(Department_.name), "%");
            departmentCriteriaQuery.select(departmentRoot).where(dptRestriction).distinct(true);
            department = entityManager.createQuery(departmentCriteriaQuery).getSingleResult();
        }

        Join<Human, HumanInUniversity> join1 = humanRoot.join(Human_.occupations);
        Join<HumanInUniversity, Department> join2 = join1.join(HumanInUniversity_.department);
        Join<HumanInUniversity, Role> join3 = join1.join(HumanInUniversity_.role);
        Predicate fullNameRestriction = filter.getEmployeeFullName() != null ?
                builder.like(humanRoot.get(Human_.fullName), "%" + filter.getEmployeeFullName() + "%") :
                builder.like(humanRoot.get(Human_.fullName), "%");
        Predicate humanRoleRestriction = filter.getRole() != null &&
                (filter.getRole().equals(RoleEnum.POSTGRADUATE) || filter.getRole().equals(RoleEnum.PROFESSOR)) ?
                builder.equal(join3.get(Role_.roleDescription), filter.getRole()) :
                join3.get(Role_.roleDescription).in(RoleEnum.PROFESSOR, RoleEnum.POSTGRADUATE, RoleEnum.STUDENT);
        Predicate humanDptRestriction =  filter.getDptName() != null ?
                builder.like(join2.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(join2.get(Department_.name), "%");
        Predicate birthDateUpperBound = filter.getBirthDateUpperBound() != null ?
                builder.lessThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateUpperBound())) :
                builder.lessThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.now());
        Predicate birthDateLowerBound = filter.getBirthDateLowerBound() != null ?
                builder.greaterThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateLowerBound())) :
                builder.greaterThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse("1800-01-01"));
        humanCriteriaQuery.where(builder.and(fullNameRestriction, humanRoleRestriction, humanDptRestriction, birthDateUpperBound, birthDateLowerBound)).distinct(true);
        Human human = entityManager.createQuery(humanCriteriaQuery).getSingleResult();

        HumanInUniversity occupation = new HumanInUniversity();
        occupation.setHuman(human);
        if(department != null) {
            occupation.setDepartment(department);
            if (role != null) {
                occupation.setRole(role);
                human.getOccupations().add(occupation);
                if (filter.getUpdates().getFullName() != null) {
                    human.setFullName(filter.getUpdates().getFullName());
                }
                humanInUniversityRepository.save(occupation);
            }
        }
    }
}
