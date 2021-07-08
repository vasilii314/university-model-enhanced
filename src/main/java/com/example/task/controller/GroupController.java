package com.example.task.controller;

import com.example.task.json.requests.filters.GroupFilterRequest;
import com.example.task.json.requests.save_or_update.GroupAddRequest;
import com.example.task.json.responses.GroupDTO;
import com.example.task.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups")
    public ResponseEntity<List<GroupDTO>> getGroups(@RequestBody GroupFilterRequest req) {
        return ResponseEntity.ok(groupService.findGroups(req));
    }

    @PostMapping("/add-group")
    public ResponseEntity<GroupDTO> addGroup(@RequestBody GroupAddRequest req) {
        groupService.addGroup(req);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/groups")
    public ResponseEntity<GroupDTO> deleteGroup(@RequestBody GroupFilterRequest req) {
        groupService.deleteGroup(req);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/groups")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupAddRequest req) {
        groupService.updateGroup(req);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<GroupDTO> deleteGroupById(@PathVariable Integer id) {
        groupService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
