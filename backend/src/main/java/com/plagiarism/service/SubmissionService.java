package com.plagiarism.service;

import com.plagiarism.dto.SubmissionRequest;
import com.plagiarism.model.Submission;
import com.plagiarism.repository.SubmissionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(
            SubmissionRepository submissionRepository) {

        this.submissionRepository = submissionRepository;
    }

    public Submission createSubmission(
            SubmissionRequest request,
            String studentId) {

        Submission submission = new Submission();

        submission.setAssignmentId(
                request.getAssignmentId()
        );

        submission.setStudentId(studentId);

        submission.setFileName(
                request.getFileName()
        );

        submission.setSourceCode(
                request.getSourceCode()
        );

        submission.setLanguage(
                request.getLanguage()
        );

        submission.setStatus("SUBMITTED");

        submission.setSubmittedAt(
                LocalDateTime.now()
        );

        return submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {

        return submissionRepository.findAll();
    }

    public List<Submission> getAssignmentSubmissions(
            String assignmentId) {

        return submissionRepository
                .findByAssignmentId(assignmentId);
    }

    public List<Submission> getStudentSubmissions(
            String studentId) {

        return submissionRepository
                .findByStudentId(studentId);
    }
}