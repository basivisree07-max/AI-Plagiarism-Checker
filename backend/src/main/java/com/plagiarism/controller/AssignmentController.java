package com.plagiarism.controller;

import com.plagiarism.dto.AssignmentRequest;
import com.plagiarism.model.Assignment;
import com.plagiarism.service.AssignmentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(
            AssignmentService assignmentService) {

        this.assignmentService = assignmentService;
    }

    // CREATE ASSIGNMENT
    @PostMapping
    public ResponseEntity<Assignment> createAssignment(
            @RequestBody AssignmentRequest request,
            Authentication authentication) {

        String teacherEmail =
                authentication.getName();

        Assignment assignment =
                assignmentService.createAssignment(
                        request,
                        teacherEmail
                );

        return ResponseEntity.ok(assignment);
    }

    // GET ALL ASSIGNMENTS
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {

        return ResponseEntity.ok(
                assignmentService.getAllAssignments()
        );
    }

    // GET TEACHER'S ASSIGNMENTS
    @GetMapping("/my")
    public ResponseEntity<List<Assignment>>
    getMyAssignments(Authentication authentication) {

        String teacherEmail =
                authentication.getName();

        return ResponseEntity.ok(
                assignmentService.getTeacherAssignments(
                        teacherEmail
                )
        );
    }
}