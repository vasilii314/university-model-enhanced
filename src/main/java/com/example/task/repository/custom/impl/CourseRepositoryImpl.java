package com.example.task.repository.custom.impl;

import com.example.task.entity.*;
import com.example.task.exception.custom.*;
import com.example.task.json.requests.filters.CourseFilterRequest;
import com.example.task.json.requests.filters.DepartmentFilterRequest;
import com.example.task.json.requests.save_or_update.CourseAddRequest;
import com.example.task.repository.custom.CourseRepositoryCustom;
import com.example.task.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements CourseRepositoryCustom {

    private EntityManager entityManager;
    private DepartmentRepository departmentRepository;

    @Autowired
    public CourseRepositoryImpl(EntityManager entityManager, DepartmentRepository departmentRepository) {
        this.entityManager = entityManager;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Course> findCoursesCriteria(CourseFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
        Root<Course> courseRoot = courseCriteriaQuery.from(Course.class);
        Join<Course, CourseType> joinCourseTypeToCourse = courseRoot.join(Course_.courseType);
        Join<Course, Department> joinDepartmentToCourse = courseRoot.join(Course_.department);
        Predicate courseTypeRestriction = filter.getCourseType() != null ?
                builder.equal(joinCourseTypeToCourse.get(CourseType_.type), filter.getCourseType()) :
                joinCourseTypeToCourse.get(CourseType_.type).in(CourseTypeEnum.MATHEMATICAL, CourseTypeEnum.SOCIAL);
        Predicate dptNameRestriction = filter.getDptName() != null ?
                builder.like(joinDepartmentToCourse.get(Department_.name), "%" + filter.getDptName() + "%") :
                builder.like(joinDepartmentToCourse.get(Department_.name), "%");
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
    @Transactional
    public void addCourseCriteria(CourseAddRequest filter) {
        DepartmentFilterRequest dptFilter = new DepartmentFilterRequest(filter.getDptName(), null);
//        dptFilter.setDptName(filter.getDptName());
        List<Department> departments = departmentRepository.findDepartmentsCriteria(dptFilter);
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
                entityManager.persist(course);
            } catch (NoResultException e) {
                throw new CourseTypeNotFoundException();
            }
        } else {
            throw new DepartmentNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteCourseCriteria(CourseFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Course> courseCriteriaDelete = builder.createCriteriaDelete(Course.class);
            Root<Course> courseRoot = courseCriteriaDelete.from(Course.class);
            Subquery<Course> subquery = courseCriteriaDelete.subquery(Course.class);
            Root<Course> courseRoot2 = subquery.from(Course.class);
            subquery.select(courseRoot2);
            Join<Course, CourseType> joinCourseTypeToCourse = courseRoot2.join(Course_.courseType);
            Join<Course, Department> joinDepartmentToCourse = courseRoot2.join(Course_.department);
            Predicate courseTypeRestriction = filter.getCourseType() != null ?
                    builder.equal(joinCourseTypeToCourse.get(CourseType_.type), filter.getCourseType()) :
                    joinCourseTypeToCourse.get(CourseType_.type).in(CourseTypeEnum.MATHEMATICAL, CourseTypeEnum.SOCIAL);
            Predicate dptNameRestriction = filter.getDptName() != null ?
                    builder.like(joinDepartmentToCourse.get(Department_.name), "%" + filter.getDptName() + "%") :
                    builder.like(joinDepartmentToCourse.get(Department_.name), "%");
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
        } catch (NonUniqueResultException e) {
            throw new InternalException();
        } catch (Exception e) {
            throw new DeleteOrUpdateException();
        }
    }

    @Override
    @Transactional
    public void updateCourseCriteria(CourseAddRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Course> courseCriteriaUpdate = builder.createCriteriaUpdate(Course.class);
            CriteriaQuery<Department> departmentCriteriaQuery = builder.createQuery(Department.class);
            CriteriaQuery<CourseType> courseTypeCriteriaQuery = builder.createQuery(CourseType.class);
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

                List<Course> courses = findCoursesCriteria(filter.getCourseFilter());
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
                        entityManager.persist(course);
                    }
                }
            }
        } catch (NonUniqueResultException e) {
            throw new InternalException();
        } catch (Exception e) {
            throw new DeleteOrUpdateException();
        }
    }
}
