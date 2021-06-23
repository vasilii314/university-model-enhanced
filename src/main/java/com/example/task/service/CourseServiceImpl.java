package com.example.task.service;

import com.example.task.entity.*;
import com.example.task.json.filters.CourseFilterRequest;
import com.example.task.json.filters.DepartmentFilterRequest;
import com.example.task.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private EntityManager entityManager;
    private DepartmentService departmentService;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, EntityManager entityManager, DepartmentService departmentService) {
        this.courseRepository = courseRepository;
        this.entityManager = entityManager;
        this.departmentService = departmentService;
    }

    @Override
    public List<Course> findCoursesCriteria(CourseFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
        Root<Group> groupRoot = courseCriteriaQuery.from(Group.class);
        Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
        Join<Course, CourseType> join1 = courseRoot.join(Course_.courseType);
        Join<Course, Department> join2 = courseRoot.join(Course_.department);
        Predicate courseTypeRestriction = filter.getCourseType() != null ?
                builder.equal(join1.get(CourseType_.type), filter.getCourseType()) :
                join1.get(CourseType_.type).in(CourseTypeEnum.MATHEMATICAL, CourseTypeEnum.SOCIAL);
        Predicate dptNameRestriction = filter.getDptName() != null ?
                builder.like(join2.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(join2.get(Department_.name), "%");
        Predicate courseNameRestriction = filter.getCourseName() != null ?
                builder.like(courseRoot.get(Course_.name), "%" + filter.getCourseName() + "%") :
                builder.like(courseRoot.get(Course_.name), "%");
        Predicate durationLowerBound = filter.getDurationLowerBound() != null ?
                builder.greaterThanOrEqualTo(courseRoot.get(Course_.duration), filter.getDurationLowerBound()) :
                builder.greaterThanOrEqualTo(courseRoot.get(Course_.duration), 0);
        Predicate durationUpperBound = filter.getDurationUpperBound() != null ?
                builder.lessThanOrEqualTo(courseRoot.get(Course_.duration), filter.getDurationUpperBound()) :
                builder.lessThanOrEqualTo(courseRoot.get(Course_.duration), Integer.MAX_VALUE);
        courseCriteriaQuery
                .select(courseRoot)
                .where(
                        builder.
                                and(courseNameRestriction,
                                        durationLowerBound,
                                        durationUpperBound,
                                        courseTypeRestriction,
                                        dptNameRestriction))
                .distinct(true);
        List<Course> courses = entityManager.createQuery(courseCriteriaQuery).getResultList();
        return courses;
    }

    @Override
    public void addCourseCriteria(CourseFilterRequest filter) {
        DepartmentFilterRequest dptFilter = new DepartmentFilterRequest();
        dptFilter.setDptName(filter.getDptName());
        List<Department> departments = departmentService.findDepartmentsCriteria(dptFilter);
        if (departments.size() == 1) {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<CourseType> courseTypeCriteriaQuery = builder.createQuery(CourseType.class);
            Root<CourseType> courseTypeRoot = courseTypeCriteriaQuery.from(CourseType.class);
            Predicate courseTypeRestriction = filter.getCourseType() != null ?
                    builder.equal(courseTypeRoot.get(CourseType_.type), filter.getCourseType()) :
                    courseTypeRoot.get(CourseType_.type).in(CourseTypeEnum.MATHEMATICAL, CourseTypeEnum.SOCIAL);
            courseTypeCriteriaQuery.select(courseTypeRoot).where(courseTypeRestriction).distinct(true);
            try {
                CourseType courseType = entityManager.createQuery(courseTypeCriteriaQuery).getSingleResult();
                Course course = new Course(filter.getCourseName(), filter.getDuration(), courseType, departments.get(0));
                courseRepository.save(course);
            } catch (NoResultException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void deleteCourseCriteria(CourseFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Course> courseCriteriaDelete = builder.createCriteriaDelete(Course.class);
        Root<Course> courseRoot = courseCriteriaDelete.from(Course.class);
        Subquery<Course> subquery = courseCriteriaDelete.subquery(Course.class);
        Root<Course> courseRoot2 = subquery.from(Course.class);
        subquery.select(courseRoot2);
        Join<Course, CourseType> join1 = courseRoot2.join(Course_.courseType);
        Join<Course, Department> join2 = courseRoot2.join(Course_.department);
        Predicate courseTypeRestriction = filter.getCourseType() != null ?
                builder.equal(join1.get(CourseType_.type), filter.getCourseType()) :
                join1.get(CourseType_.type).in(CourseTypeEnum.MATHEMATICAL, CourseTypeEnum.SOCIAL);
        Predicate dptNameRestriction = filter.getDptName() != null ?
                builder.like(join2.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(join2.get(Department_.name), "%");
        Predicate courseNameRestriction = filter.getCourseName() != null ?
                builder.like(courseRoot2.get(Course_.name), "%" + filter.getCourseName() + "%") :
                builder.like(courseRoot2.get(Course_.name), "%");
        Predicate durationLowerBound = filter.getDurationLowerBound() != null ?
                builder.greaterThanOrEqualTo(courseRoot2.get(Course_.duration), filter.getDurationLowerBound()) :
                builder.greaterThanOrEqualTo(courseRoot2.get(Course_.duration), 0);
        Predicate durationUpperBound = filter.getDurationUpperBound() != null ?
                builder.lessThanOrEqualTo(courseRoot2.get(Course_.duration), filter.getDurationUpperBound()) :
                builder.lessThanOrEqualTo(courseRoot2.get(Course_.duration), Integer.MAX_VALUE);
        subquery.where(
                builder.and(
                        courseNameRestriction,
                        courseTypeRestriction,
                        dptNameRestriction,
                        durationLowerBound,
                        durationUpperBound
                )
        );
        courseCriteriaDelete.where(courseRoot.in(subquery));
        entityManager.createQuery(courseCriteriaDelete).executeUpdate();
    }

    @Override
    @Transactional
    public void updateCourseCriteria(CourseFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Course> courseCriteriaUpdate = builder.createCriteriaUpdate(Course.class);
        CriteriaQuery<Department> departmentCriteriaQuery = builder.createQuery(Department.class);
        CriteriaQuery<CourseType> courseTypeCriteriaQuery = builder.createQuery(CourseType.class);
        Root<Course> courseRoot = courseCriteriaUpdate.from(Course.class);
        Root<Department> departmentRoot = departmentCriteriaQuery.from(Department.class);
        Root<CourseType> courseTypeRoot = courseTypeCriteriaQuery.from(CourseType.class);

        if (filter.getUpdates() != null) {
            Department department = null;
            if (filter.getUpdates().getDptName() != null) {
                Predicate dptNameRestriction = filter.getUpdates().getDptName() != null ?
                        builder.like(departmentRoot.get(Department_.name), "%" + filter.getUpdates().getDptName() + "%") :
                        builder.like(departmentRoot.get(Department_.name), "%");
                departmentCriteriaQuery.select(departmentRoot).where(dptNameRestriction).distinct(true);
                department = entityManager.createQuery(departmentCriteriaQuery).getSingleResult();
            }

            CourseType courseType = null;
            if (filter.getUpdates().getCourseType() != null) {
                Predicate courseTypeRestriction = filter.getCourseType() != null ?
                        builder.equal(courseTypeRoot.get(CourseType_.type), filter.getCourseType()) :
                        courseTypeRoot.get(CourseType_.type).in(CourseTypeEnum.MATHEMATICAL, CourseTypeEnum.SOCIAL);
                courseTypeCriteriaQuery.select(courseTypeRoot).where(courseTypeRestriction).distinct(true);
                courseType = entityManager.createQuery(courseTypeCriteriaQuery).getSingleResult();
            }

            List<Course> courses = findCoursesCriteria(filter);
            if (courses.size() == 1) {
                Course course = courses.get(0);
                boolean flag = false;
                if (filter.getUpdates().getCourseName() != null) {
                    course.setName(filter.getUpdates().getCourseName());
                    flag = true;
                }
                if (filter.getUpdates().getDuration() != null) {
                    course.setDuration(filter.getUpdates().getDuration());
                    flag = true;
                }
                if (department != null) {
                    course.setDepartment(department);
                    flag = true;
                }
                if (courseType != null) {
                    course.setCourseType(courseType);
                    flag = true;
                }
                if (flag) {
                    courseRepository.save(course);
                }
            }
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }
}
