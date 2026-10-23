package com.plagiarism.controller;

import com.plagiarism.dto.SubmissionRequest;
import com.plagiarism.model.Submission;
import com.plagiarism.service.SubmissionService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(
            SubmissionService submissionService) {

        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(
            @RequestBody SubmissionRequest request,
            Authentication authentication) {

        String studentId = authentication.getName();

        Submission submission =
                submissionService.createSubmission(
                        request,
                        studentId
                );

        return ResponseEntity.ok(submission);
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions() {

        return ResponseEntity.ok(
                submissionService.getAllSubmissions()
        );
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>>
    getAssignmentSubmissions(
            @PathVariable String assignmentId) {

        return ResponseEntity.ok(
                submissionService
                        .getAssignmentSubmissions(assignmentId)
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Submission>>
    getStudentSubmissions(
            @PathVariable String studentId,
            Authentication authentication) {

        if (!authentication.getName().equals(studentId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(
                submissionService
                        .getStudentSubmissions(studentId)
        );
    }
}
