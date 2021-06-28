package com.example.task.repository;

import com.example.task.entity.*;
import com.example.task.json.requests.save_or_update.EmployeeAddRequest;
import com.example.task.json.requests.filters.EmployeeFilterRequest;
import com.example.task.json.requests.filters.StudentFilterRequest;
import com.example.task.json.responses.StudentGradeDTO;
import com.example.task.repository.custom.HumanRepositoryCustom;
import com.example.task.repository.default_repos.HumanInUniversityRepository;
import com.example.task.repository.default_repos.StudentGradesRepository;
import com.example.task.repository.default_repos.StudentInGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HumanRepositoryImpl implements HumanRepositoryCustom {
    private EntityManager entityManager;
    private HumanInUniversityRepository humanInUniversityRepository;
    private StudentInGroupRepository studentInGroupRepository;
    private StudentGradesRepository studentGradesRepository;

    @Autowired
    public HumanRepositoryImpl(EntityManager entityManager,
                               HumanInUniversityRepository humanInUniversityRepository,
                               StudentInGroupRepository studentInGroupRepository,
                               StudentGradesRepository studentGradesRepository) {
        this.entityManager = entityManager;
        this.humanInUniversityRepository = humanInUniversityRepository;
        this.studentInGroupRepository = studentInGroupRepository;
        this.studentGradesRepository = studentGradesRepository;
    }

    @Override
    public List<Human> findEmployeesCriteria(EmployeeFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Human> criteriaQuery = builder.createQuery(Human.class);
        Root<Human> humanRoot = criteriaQuery.from(Human.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        Join<Human, HumanInUniversity> joinHumanInUniversityToHuman = humanRoot.join(Human_.occupations);
        Join<HumanInUniversity, Role> joinRoleToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.role);
        Join<HumanInUniversity, Department> joinDepartmentToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.department);
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
        try {
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

            Join<Department, School> joinSchoolToDepartment = dptRoot.join(Department_.school, JoinType.INNER);
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save failed");
        }
    }

    @Override
    @Transactional
    public void deleteEmployeeOrStudentCriteria(EmployeeFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Human> criteriaDelete = builder.createCriteriaDelete(Human.class);
            Root<Human> humanRoot = criteriaDelete.from(Human.class);
            Subquery<Human> subquery = criteriaDelete.subquery(Human.class);
            Root<Human> humanRoot2 = subquery.from(Human.class);
            subquery.select(humanRoot2);
            Join<Human, HumanInUniversity> joinHumanInUniversityToHuman = humanRoot2.join(Human_.occupations);
            Join<HumanInUniversity, Department> joinDepartmentToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.department);
            Join<Department, Group> join4 = joinDepartmentToHumanInUniversity.join(Department_.groups);
            Join<HumanInUniversity, Role> joinRoleToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.role);
            Predicate fullNameRestriction = filter.getEmployeeFullName() != null ?
                    builder.like(humanRoot2.get(Human_.fullName), "%" + filter.getEmployeeFullName() + "%") :
                    builder.like(humanRoot2.get(Human_.fullName), "%");
            Predicate groupNameRestriction = filter.getGroupName() != null ?
                    builder.like(join4.get(Group_.name), "%" + filter.getGroupName() + "%") :
                    builder.like(join4.get(Group_.name), "%");
            Predicate roleRestriction = filter.getRole() != null &&
                    (filter.getRole().equals(RoleEnum.POSTGRADUATE) || filter.getRole().equals(RoleEnum.PROFESSOR)) ?
                    builder.equal(joinRoleToHumanInUniversity.get(Role_.roleDescription), filter.getRole()) :
                    joinRoleToHumanInUniversity.get(Role_.roleDescription).in(RoleEnum.PROFESSOR, RoleEnum.POSTGRADUATE, RoleEnum.STUDENT);
            Predicate dptRestriction =  filter.getDptName() != null ?
                    builder.like(joinDepartmentToHumanInUniversity.get(Department_.name), "%" + filter.getDptName() + "%") :
                    builder.like(joinDepartmentToHumanInUniversity.get(Department_.name), "%");
            Predicate birthDateUpperBound = filter.getBirthDateUpperBound() != null ?
                    builder.lessThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateUpperBound())) :
                    builder.lessThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.now());
            Predicate birthDateLowerBound = filter.getBirthDateLowerBound() != null ?
                    builder.greaterThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateLowerBound())) :
                    builder.greaterThanOrEqualTo(humanRoot2.get(Human_.birthDate), LocalDate.parse("1800-01-01"));
            subquery.where(builder.and(fullNameRestriction,
                    roleRestriction,
                    dptRestriction,
                    groupNameRestriction,
                    birthDateUpperBound,
                    birthDateLowerBound));
            criteriaDelete.where(humanRoot.in(subquery));
            entityManager.createQuery(criteriaDelete).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete failed");
        }
    }

    @Override
    public void updateEmployeeOrStudentCriteria(EmployeeFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Human> humanCriteriaQuery= builder.createQuery(Human.class);
            CriteriaQuery<Role> roleCriteriaQuery = builder.createQuery(Role.class);
            CriteriaQuery<Department> departmentCriteriaQuery = builder.createQuery(Department.class);
            CriteriaQuery<Group> groupCriteriaQuery = builder.createQuery(Group.class);
            Root<Human> humanRoot = humanCriteriaQuery.from(Human.class);
            Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);
            Root<Department> departmentRoot = departmentCriteriaQuery.from(Department.class);
            Root<Group> groupRoot = groupCriteriaQuery.from(Group.class);

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

            Group group = null;
            if (filter.getUpdates().getGroupName() != null) {
                Predicate groupNameRestriction = builder.like(groupRoot.get(Group_.name),
                        "%" + filter.getUpdates().getGroupName() + "%");
                group = entityManager.createQuery(groupCriteriaQuery).getSingleResult();
            }

            Join<Human, HumanInUniversity> joinHumanInUniversityToHuman = humanRoot.join(Human_.occupations);
            Join<HumanInUniversity, Department> joinDepartmentToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.department);
            Join<HumanInUniversity, Role> joinRoleToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.role);
            Join<Department, Group> joinGroupToDepartment = joinDepartmentToHumanInUniversity.join(Department_.groups);
            Predicate fullNameRestriction = filter.getEmployeeFullName() != null ?
                    builder.like(humanRoot.get(Human_.fullName), "%" + filter.getEmployeeFullName() + "%") :
                    builder.like(humanRoot.get(Human_.fullName), "%");
            Predicate humanRoleRestriction = filter.getRole() != null &&
                    (filter.getRole().equals(RoleEnum.POSTGRADUATE) || filter.getRole().equals(RoleEnum.PROFESSOR)) ?
                    builder.equal(joinRoleToHumanInUniversity.get(Role_.roleDescription), filter.getRole()) :
                    joinRoleToHumanInUniversity.get(Role_.roleDescription).in(RoleEnum.PROFESSOR, RoleEnum.POSTGRADUATE, RoleEnum.STUDENT);
            Predicate humanDptRestriction =  filter.getDptName() != null ?
                    builder.like(joinDepartmentToHumanInUniversity.get(Department_.name), "%" + filter.getDptName() + "%") :
                    builder.like(joinDepartmentToHumanInUniversity.get(Department_.name), "%");
            Predicate birthDateUpperBound = filter.getBirthDateUpperBound() != null ?
                    builder.lessThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateUpperBound())) :
                    builder.lessThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.now());
            Predicate birthDateLowerBound = filter.getBirthDateLowerBound() != null ?
                    builder.greaterThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateLowerBound())) :
                    builder.greaterThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse("1800-01-01"));
            Predicate groupNameRestriction = filter.getGroupName() != null ?
                    builder.like(joinGroupToDepartment.get(Group_.name), "%" + filter.getGroupName() + "%") :
                    builder.like(joinGroupToDepartment.get(Group_.name), "%");

            humanCriteriaQuery.where(builder.and(fullNameRestriction,
                    humanRoleRestriction,
                    humanDptRestriction,
                    birthDateUpperBound,
                    birthDateLowerBound,
                    groupNameRestriction)).distinct(true);
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
                    if (role.getRoleDescription().equals(RoleEnum.STUDENT) && group != null) {
                        StudentsInGroups updatedStudent = new StudentsInGroups();
                        updatedStudent.setStudent(occupation);
                        updatedStudent.setGroup(group);
                        studentInGroupRepository.save(updatedStudent);
                        return;
                    }
                    humanInUniversityRepository.save(occupation);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Update Failed");
        }
    }

    @Override
    public List<Human> findStudentsCriteria(StudentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Human> criteriaQuery = builder.createQuery(Human.class);
        Root<Human> humanRoot = criteriaQuery.from(Human.class);
        Root<Role> roleRoot = criteriaQuery.from(Role.class);
        Join<Human, HumanInUniversity> joinHumanInUniversityToHuman = humanRoot.join(Human_.occupations);
        Join<HumanInUniversity, Role> joinRoleToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.role);
        Join<HumanInUniversity, Department> joinDepartmentToHumanInUniversity = joinHumanInUniversityToHuman.join(HumanInUniversity_.department);
        Predicate fullNameRestriction = filter.getStudentFullName() != null ?
                builder.like(humanRoot.get(Human_.fullName), "%" + filter.getStudentFullName() + "%") :
                builder.like(humanRoot.get(Human_.fullName), "%");
        Predicate birthDateUpperBound = filter.getBirthDateUpperBound() != null ?
                builder.lessThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateUpperBound())) :
                builder.lessThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.now());
        Predicate birthDateLowerBound = filter.getBirthDateLowerBound() != null ?
                builder.greaterThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse(filter.getBirthDateLowerBound())) :
                builder.greaterThanOrEqualTo(humanRoot.get(Human_.birthDate), LocalDate.parse("1800-01-01"));
        Predicate roleRestriction = builder.equal(roleRoot.get(Role_.roleDescription), RoleEnum.STUDENT);
        Predicate humanDptRestriction =  filter.getDptName() != null ?
                builder.like(joinDepartmentToHumanInUniversity.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(joinDepartmentToHumanInUniversity.get(Department_.name), "%");
        criteriaQuery.select(humanRoot).distinct(true);
        criteriaQuery.where(builder.and(fullNameRestriction,
                birthDateLowerBound,
                birthDateUpperBound,
                roleRestriction,
                humanDptRestriction));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void addStudentCriteria(StudentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        CriteriaQuery<Role> roleCriteriaQuery = builder.createQuery(Role.class);
        Root<Department> dptRoot = criteriaQuery.from(Department.class);
        Root<School> schoolRoot = criteriaQuery.from(School.class);
        Root<Role> roleRoot = roleCriteriaQuery.from(Role.class);
        Root<Group> groupRoot = criteriaQuery.from(Group.class);

        roleCriteriaQuery.select(roleRoot);
        roleCriteriaQuery.where(builder.equal(roleRoot.get(Role_.roleDescription), RoleEnum.STUDENT));
        Role role = entityManager.createQuery(roleCriteriaQuery).getSingleResult();

        Join<Department, School> joinSchoolToDepartment = dptRoot.join(Department_.school, JoinType.INNER);
        Join<Department, Group> joinGroupToDepartment = dptRoot.join(Department_.groups);
        criteriaQuery.multiselect(dptRoot, schoolRoot, groupRoot).distinct(true);
        criteriaQuery.where(
                builder.and(
                        builder.equal(dptRoot.get(Department_.name), filter.getDptName()),
                        builder.equal(schoolRoot.get(School_.name), filter.getSchoolName()),
                        builder.equal(groupRoot.get(Group_.name), filter.getGroupName())
                ));
        Tuple schoolDepartment = entityManager.createQuery(criteriaQuery).getSingleResult();

        Human human = new Human();
        human.setFullName(filter.getStudentFullName());
        human.setBirthDate(LocalDate.parse(filter.getBirthDate()));
        HumanInUniversity employee = new HumanInUniversity();
        employee.setHuman(human);
        employee.setRole(role);
        employee.setDepartment(schoolDepartment.get(dptRoot));
        StudentsInGroups student = new StudentsInGroups();
        student.setStudent(employee);
        student.setGroup(schoolDepartment.get(groupRoot));
        studentInGroupRepository.save(student);
    }

    @Override
    public List<StudentGradeDTO> getStudentGradesCriteria(StudentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        Root<StudentGrade> studentGradeRoot = criteriaQuery.from(StudentGrade.class);

        Join<StudentGrade, Course> joinCourseToStudentGrade = studentGradeRoot.join(StudentGrade_.course);
        Join<StudentGrade, StudentsInGroups> joinStudentsInGroupsToStudentGrade = studentGradeRoot.join(StudentGrade_.student);
        Join<StudentsInGroups, Group> joinGroupToStudentsInGroups = joinStudentsInGroupsToStudentGrade.join(StudentsInGroups_.group);
        Join<StudentsInGroups, HumanInUniversity> joinHumanInUniversityToStudentsInGroups = joinStudentsInGroupsToStudentGrade.join(StudentsInGroups_.student);
        Join<HumanInUniversity, Human> joinHumanToHumanInUniversity = joinHumanInUniversityToStudentsInGroups.join(HumanInUniversity_.human);
        Join<Course, Department> joinDepartmentToCourse = joinCourseToStudentGrade.join(Course_.department);
        Predicate courseNameRestriction = filter.getCourseName() != null ?
                builder.like(joinCourseToStudentGrade.get(Course_.name), "%" + filter.getCourseName() + "%") :
                builder.like(joinCourseToStudentGrade.get(Course_.name), "%");
        Predicate fullNameRestriction = filter.getStudentFullName() != null ?
                builder.like(joinHumanToHumanInUniversity.get(Human_.fullName), "%" + filter.getStudentFullName() + "%") :
                builder.like(joinHumanToHumanInUniversity.get(Human_.fullName), "%");
        Predicate groupNameRestriction = filter.getGroupName() != null ?
                builder.like(joinGroupToStudentsInGroups.get(Group_.name), "%" + filter.getGroupName() + "%") :
                builder.like(joinGroupToStudentsInGroups.get(Group_.name), "%");
        Predicate gradeLowerBound = filter.getGradeLowerBound() != 0 ?
                builder.greaterThanOrEqualTo(studentGradeRoot.get(StudentGrade_.grade), filter.getGradeLowerBound()) :
                builder.greaterThanOrEqualTo(studentGradeRoot.get(StudentGrade_.grade), 2);
        Predicate gradeUpperBound = filter.getGradeUpperBound() != 0 ?
                builder.lessThanOrEqualTo(studentGradeRoot.get(StudentGrade_.grade), filter.getGradeUpperBound()) :
                builder.lessThanOrEqualTo(studentGradeRoot.get(StudentGrade_.grade), 5);
        Predicate studentDptRestriction =  filter.getDptName() != null ?
                builder.like(joinDepartmentToCourse.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(joinDepartmentToCourse.get(Department_.name), "%");


        Path<String> studentFullNamePath = joinHumanToHumanInUniversity.get(Human_.fullName);
        Path<String> courseNamePath = joinCourseToStudentGrade.get(Course_.name);

        criteriaQuery.multiselect(studentFullNamePath, courseNamePath, studentGradeRoot).where(builder.and(courseNameRestriction,
                groupNameRestriction,
                fullNameRestriction,
                gradeLowerBound,
                gradeUpperBound,
                studentDptRestriction)).distinct(true);

        List<Tuple> gradesRaw = entityManager.createQuery(criteriaQuery).getResultList();
        List<StudentGradeDTO> grades = gradesRaw
                .stream()
                .map(gradeRaw -> new StudentGradeDTO(gradeRaw.get(studentGradeRoot).getId(), gradeRaw.get(studentGradeRoot).getGrade(),
                        gradeRaw.get(studentFullNamePath),
                        gradeRaw.get(courseNamePath))).collect(Collectors.toList());

        return grades;
    }

    @Override
    public void addStudentGradeCriteria(StudentFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
        CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
        Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
        Root<StudentsInGroups> studentsInGroupsRoot = criteriaQuery.from(StudentsInGroups.class);

        Join<StudentsInGroups, HumanInUniversity> joinHumanInUniversityToStudentsInGroups = studentsInGroupsRoot.join(StudentsInGroups_.student);
        Join<StudentsInGroups, Group> joinGroupToStudentsInGroups = studentsInGroupsRoot.join(StudentsInGroups_.group);
        Join<HumanInUniversity, Human> joinHumanToHumanInUniversity = joinHumanInUniversityToStudentsInGroups.join(HumanInUniversity_.human);


        Predicate courseNameRestriction = filter.getCourseName() != null ?
                builder.like(courseRoot.get(Course_.name), "%" + filter.getCourseName() + "%") :
                builder.like(courseRoot.get(Course_.name), "%");
        Predicate fullNameRestriction = filter.getStudentFullName() != null ?
                builder.like(joinHumanToHumanInUniversity.get(Human_.fullName), "%" + filter.getStudentFullName() + "%") :
                builder.like(joinHumanToHumanInUniversity.get(Human_.fullName), "%");
        Predicate groupNameRestriction = filter.getGroupName() != null ?
                builder.like(joinGroupToStudentsInGroups.get(Group_.name), "%" + filter.getGroupName() + "%") :
                builder.like(joinGroupToStudentsInGroups.get(Group_.name), "%");

        courseCriteriaQuery.select(courseRoot).where(courseNameRestriction).distinct(true);
        Course course = entityManager.createQuery(courseCriteriaQuery).getSingleResult();


        criteriaQuery.multiselect(joinHumanToHumanInUniversity.get(Human_.fullName), studentsInGroupsRoot).distinct(true);
        criteriaQuery.where(builder.and(fullNameRestriction, groupNameRestriction)).distinct(true);

        try {
            Tuple tuple = entityManager.createQuery(criteriaQuery).getSingleResult();
            System.out.println(tuple.get(joinHumanToHumanInUniversity.get(Human_.fullName)));
            System.out.println(tuple.get(studentsInGroupsRoot).getStudent().getHuman());
            StudentGrade grade = new StudentGrade();
            grade.setGrade(filter.getUpdates().getGrade());
            grade.setStudent(tuple.get(studentsInGroupsRoot));
            grade.setCourse(course);
            studentGradesRepository.save(grade);
        } catch (NoResultException e) {
            System.out.println("404 - Not Found");
        }
    }
}
