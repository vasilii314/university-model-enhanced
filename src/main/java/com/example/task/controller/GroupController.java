package com.example.task.controller;

import com.example.task.entity.Group;
import com.example.task.json.filters.GroupFilterRequest;
import com.example.task.json.responses.GroupDTO;
import com.example.task.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GroupDTO>> getGroups(@RequestBody GroupFilterRequest req) {
        List<Group> groupsRaw = groupService.findGroupsCriteria(req);
        if (groupsRaw.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<GroupDTO> groups = groupsRaw.stream().map(GroupDTO::toGroupDTO).collect(Collectors.toList());
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/groups")
    public ResponseEntity<?> addGroup(@RequestBody GroupFilterRequest req) {
        try {
            groupService.addGroup(req);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/groups")
    public ResponseEntity<?> deleteGroup(@RequestBody GroupFilterRequest req) {
        try {
            groupService.addGroup(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/groups")
    public ResponseEntity<?> updateGroup(@RequestBody GroupFilterRequest req) {
        try {
            groupService.updateGroup(req);
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
