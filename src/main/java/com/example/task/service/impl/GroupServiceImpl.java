package com.example.task.service.impl;

import com.example.task.entity.Group;
import com.example.task.exception.custom.GroupNotFoundException;
import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.requests.save_or_update.GroupAddRequest;
import com.example.task.json.responses.GroupDTO;
import com.example.task.repository.GroupRepository;
import com.example.task.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

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
    public List<GroupDTO> findGroups(GroupFilterRequest filter) {
        return groupRepository
                .findGroups(filter)
                .stream()
                .map(GroupDTO::toGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addGroup(GroupAddRequest filter) {
        groupRepository.addGroup(filter);
    }

    @Override
    public void deleteGroup(GroupFilterRequest filter) {
        groupRepository.deleteGroup(filter);
    }

    @Override
    public void updateGroup(GroupAddRequest filter) {
        groupRepository.updateGroup(filter);
    }
}
