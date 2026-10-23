package com.plagiarism.service;

import com.plagiarism.dto.AssignmentRequest;
import com.plagiarism.model.Assignment;
import com.plagiarism.repository.AssignmentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(
            AssignmentRepository assignmentRepository) {

        this.assignmentRepository = assignmentRepository;
    }

    // CREATE ASSIGNMENT
    public Assignment createAssignment(
            AssignmentRequest request,
            String teacherId) {

        Assignment assignment = new Assignment();

        assignment.setTitle(request.getTitle());
        assignment.setDescription(request.getDescription());
        assignment.setLanguage(request.getLanguage());
        assignment.setDeadline(request.getDeadline());
        assignment.setTeacherId(teacherId);
        assignment.setSimilarityThreshold(
                request.getSimilarityThreshold()
        );
        assignment.setCreatedAt(LocalDateTime.now());

        return assignmentRepository.save(assignment);
    }

    // GET ALL ASSIGNMENTS
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    // GET TEACHER ASSIGNMENTS
    public List<Assignment> getTeacherAssignments(
            String teacherId) {

        return assignmentRepository.findByTeacherId(teacherId);
    }
}