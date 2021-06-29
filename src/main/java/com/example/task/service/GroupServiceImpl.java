package com.example.task.service;

import com.example.task.entity.Group;
import com.example.task.exception.custom.GroupNotFoundException;
import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.requests.save_or_update.GroupAddRequest;
import com.example.task.repository.default_repos.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll().forEach(groups::add);
        return groups;
    }

    @Override
    public Optional<Group> findById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void deleteById(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> findGroupsCriteria(GroupFilterRequest filter) {
        List<Group> groups = groupRepository.findGroupsCriteria(filter);
        if (groups.size() == 0) {
            throw new GroupNotFoundException();
        }
        return groups;
    }

    @Override
    public void addGroupCriteria(GroupAddRequest filter) {
        groupRepository.addGroupCriteria(filter);
    }

    @Override
    public void deleteGroupCriteria(GroupFilterRequest filter) {
        groupRepository.deleteGroupCriteria(filter);
    }

    @Override
    public void updateGroupCriteria(GroupAddRequest filter) {
        groupRepository.updateGroupCriteria(filter);
    }
}
