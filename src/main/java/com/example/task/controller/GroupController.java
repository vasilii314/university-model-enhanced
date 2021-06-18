package com.example.task.controller;

import com.example.task.entity.Group;
import com.example.task.json.filters.GroupFilterRequest;
import com.example.task.json.responses.GroupDTO;
import com.example.task.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public List<GroupDTO> getGroups(@RequestBody GroupFilterRequest req) {
        List<Group> groupsRaw = groupService.findGroupsCriteria(req);
        List<GroupDTO> groups = groupsRaw.stream().map(GroupDTO::toGroupDTO).collect(Collectors.toList());
        return groups;
    }

    @PostMapping("/groups")
    public void addGroup(@RequestBody GroupFilterRequest req) {
        groupService.addGroup(req);
    }

    @DeleteMapping("/groups")
    public void deleteGroup(@RequestBody GroupFilterRequest req) {
        groupService.deleteGroup(req);
    }

    @PatchMapping("/groups")
    public void updateGroup(@RequestBody GroupFilterRequest req) {
        groupService.updateGroup(req);
    }
}
