package com.plagiarism.service;

import com.plagiarism.model.Report;
import com.plagiarism.model.SimilarityResult;
import com.plagiarism.repository.ReportRepository;
import com.plagiarism.repository.SimilarityResultRepository;
import com.plagiarism.repository.SubmissionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final SimilarityResultRepository similarityResultRepository;
    private final SubmissionRepository submissionRepository;

    public ReportService(
            ReportRepository reportRepository,
            SimilarityResultRepository similarityResultRepository,
            SubmissionRepository submissionRepository) {

        this.reportRepository = reportRepository;
        this.similarityResultRepository =
                similarityResultRepository;
        this.submissionRepository =
                submissionRepository;
    }

    public Report generateReport(String assignmentId) {

        // Get all submissions for this assignment
        int totalSubmissions =
                submissionRepository
                        .findByAssignmentId(assignmentId)
                        .size();

        // Get all similarity results
        List<SimilarityResult> results =
                similarityResultRepository
                        .findByAssignmentId(assignmentId);

        int highRiskPairs = 0;
        int mediumRiskPairs = 0;
        int lowRiskPairs = 0;

        double totalSimilarity = 0.0;

        for (SimilarityResult result : results) {

            // Count risk levels
            if ("HIGH".equalsIgnoreCase(
                    result.getRiskLevel())) {

                highRiskPairs++;

            } else if ("MEDIUM".equalsIgnoreCase(
                    result.getRiskLevel())) {

                mediumRiskPairs++;

            } else if ("LOW".equalsIgnoreCase(
                    result.getRiskLevel())) {

                lowRiskPairs++;
            }

            // Add similarity score
            totalSimilarity +=
                    result.getFinalSimilarity();
        }

        // Calculate average similarity
        double averageSimilarity = 0.0;

        if (!results.isEmpty()) {

            averageSimilarity =
                    totalSimilarity / results.size();
        }

        // Create report
        Report report = new Report();

        report.setAssignmentId(assignmentId);

        report.setTotalSubmissions(
                totalSubmissions
        );

        report.setHighRiskPairs(
                highRiskPairs
        );

        report.setMediumRiskPairs(
                mediumRiskPairs
        );

        report.setLowRiskPairs(
                lowRiskPairs
        );

        report.setAverageSimilarity(
                averageSimilarity
        );

        report.setGeneratedAt(
                LocalDateTime.now()
        );

        // Save report in MongoDB
        return reportRepository.save(report);
    }

    public Report getReport(String assignmentId) {

        return reportRepository
                .findByAssignmentId(assignmentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Report not found for this assignment"
                        )
                );
    }
}