package com.example.task.repository;

import com.example.task.entity.Department;
import com.example.task.entity.Department_;
import com.example.task.entity.Group;
import com.example.task.entity.Group_;
import com.example.task.json.filters.GroupFilterRequest;
import com.example.task.repository.custom.GroupRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepositoryCustom {

    private EntityManager entityManager;

    @Autowired
    public GroupRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Group> findGroupsCriteria(GroupFilterRequest filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> groupCriteriaQuery = builder.createQuery(Group.class);
        Root<Group> groupRoot = groupCriteriaQuery.from(Group.class);
        groupCriteriaQuery.select(groupRoot);
        if (filter.getGroupName() != null) {
            Predicate groupNameRestriction = builder
                    .like(groupRoot.get(Group_.name), "%" + filter.getGroupName() + "%");
            if (filter.getDptName() != null) {
                Join<Group, Department> join = groupRoot.join(Group_.groupDepartment);
                Predicate dptNameRestriction = builder
                        .like(join.get(Department_.name), "%" + filter.getDptName() + "%");
                groupCriteriaQuery.where(builder.and(groupNameRestriction, dptNameRestriction)).distinct(true);
                return entityManager.createQuery(groupCriteriaQuery).getResultList();
            }
            groupCriteriaQuery.where(groupNameRestriction).distinct(true);
        }
        return entityManager.createQuery(groupCriteriaQuery).getResultList();
    }

    @Override
    @Transactional
    public void addGroupCriteria(GroupFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = builder.createQuery(Department.class);
            Root<Department> dptRoot = criteriaQuery.from(Department.class);
            if (filter.getDptName() != null) {
                criteriaQuery.select(dptRoot)
                        .where(builder.like(dptRoot.get(Department_.name), filter.getDptName())).distinct(true);
                Department department = entityManager.createQuery(criteriaQuery).getSingleResult();
                if (filter.getGroupName() != null) {
                    Group group = new Group();
                    group.setName(filter.getGroupName());
                    group.setDepartment(department);
                    entityManager.persist(group);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Save failed");
        }
    }

    @Override
    @Transactional
    public void deleteGroupCriteria(GroupFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Group> groupCriteriaDelete = builder.createCriteriaDelete(Group.class);
            Root<Group> groupRoot = groupCriteriaDelete.from(Group.class);
            Predicate groupNameRestriction;
            Predicate dptNameRestriction;
            if (filter.getGroupName() != null) {
                groupNameRestriction = builder.equal(groupRoot.get(Group_.name), filter.getGroupName());
                Join<Group, Department> join;
                if (filter.getDptName() != null) {
                    Subquery<Group> subquery = groupCriteriaDelete.subquery(Group.class);
                    Root<Group> groupRoot2 = subquery.from(Group.class);
                    subquery.select(groupRoot2);
                    join = groupRoot2.join(Group_.groupDepartment);
                    dptNameRestriction = builder.equal(join.get(Department_.name), filter.getDptName());
                    subquery.where(builder.and(groupNameRestriction, dptNameRestriction));
                    groupCriteriaDelete.where(groupRoot.in(subquery));
                    entityManager.createQuery(groupCriteriaDelete).executeUpdate();
                    return;
                }
                groupCriteriaDelete.where(groupNameRestriction);
                entityManager.createQuery(groupCriteriaDelete).executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Delete failed");
        }
    }

    @Override
    @Transactional
    public void updateGroupCriteria(GroupFilterRequest filter) {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Group> groupCriteriaUpdate = builder.createCriteriaUpdate(Group.class);
            Root<Group> groupRoot = groupCriteriaUpdate.from(Group.class);
            Predicate groupNameRestriction;
            Predicate dptNameRestriction;
            if (filter.getGroupName() != null) {
                groupNameRestriction = builder.equal(groupRoot.get(Group_.name), filter.getGroupName());
                Join<Group, Department> join;
                if (filter.getDptName() != null) {
                    Subquery<Group> subquery = groupCriteriaUpdate.subquery(Group.class);
                    Root<Group> groupRoot2 = subquery.from(Group.class);
                    subquery.select(groupRoot2);
                    join = groupRoot2.join(Group_.groupDepartment);
                    dptNameRestriction = builder.equal(join.get(Department_.name), filter.getDptName());
                    subquery.where(builder.and(groupNameRestriction, dptNameRestriction));
                    groupCriteriaUpdate.where(groupRoot.in(subquery));
                }
                groupCriteriaUpdate.where(groupNameRestriction);
                if (filter.getUpdates() != null) {
                    if (filter.getUpdates().getGroupName() != null) {
                        groupCriteriaUpdate.set(Group_.name, filter.getUpdates().getGroupName());
                    }
                    if (filter.getUpdates().getDptName() != null) {
                        CriteriaQuery<Department> departmentCriteriaQuery = builder.createQuery(Department.class);
                        Root<Department> departmentRoot = departmentCriteriaQuery.from(Department.class);
                        departmentCriteriaQuery.select(departmentRoot)
                                .where(builder.equal(departmentRoot.get(Department_.name), filter.getUpdates().getDptName()))
                                .distinct(true);
                        Department department = entityManager.createQuery(departmentCriteriaQuery).getSingleResult();
                        groupCriteriaUpdate.set(Group_.groupDepartment, department);
                    }
                    entityManager.createQuery(groupCriteriaUpdate).executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Update failed");
        }
    }
}
