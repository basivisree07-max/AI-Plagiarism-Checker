package com.plagiarism.similarity;

import java.util.List;

public class SimilarityEngine {

    private final CodeTokenizer tokenizer;
    private final TokenSimilarityCalculator tokenCalculator;
    private final JaccardSimilarityCalculator jaccardCalculator;
    private final CosineSimilarityCalculator cosineCalculator;
    private final StructuralSimilarityCalculator structuralCalculator;

    public SimilarityEngine() {

        tokenizer = new CodeTokenizer();

        tokenCalculator =
                new TokenSimilarityCalculator();

        jaccardCalculator =
                new JaccardSimilarityCalculator();

        cosineCalculator =
                new CosineSimilarityCalculator();

        structuralCalculator =
                new StructuralSimilarityCalculator();
    }

    public double[] calculateSimilarity(
            String code1,
            String code2) {

        List<String> tokens1 =
                tokenizer.tokenize(code1);

        List<String> tokens2 =
                tokenizer.tokenize(code2);

        double tokenSimilarity =
                tokenCalculator.calculate(
                        tokens1,
                        tokens2
                );

        double jaccardSimilarity =
                jaccardCalculator.calculate(
                        tokens1,
                        tokens2
                );

        double cosineSimilarity =
                cosineCalculator.calculate(
                        tokens1,
                        tokens2
                );

        double structuralSimilarity =
                structuralCalculator.calculate(
                        code1,
                        code2
                );

        return new double[] {
                tokenSimilarity,
                jaccardSimilarity,
                cosineSimilarity,
                structuralSimilarity
        };
    }

    public double calculateFinalSimilarity(
            double tokenSimilarity,
            double jaccardSimilarity,
            double cosineSimilarity,
            double structuralSimilarity) {

        return
                (tokenSimilarity * 0.25)
                        + (jaccardSimilarity * 0.25)
                        + (cosineSimilarity * 0.30)
                        + (structuralSimilarity * 0.20);
    }

    public String determineRiskLevel(
            double finalSimilarity) {

        if (finalSimilarity >= 70) {
            return "HIGH";
        }

        if (finalSimilarity >= 40) {
            return "MEDIUM";
        }

        return "LOW";
    }
}