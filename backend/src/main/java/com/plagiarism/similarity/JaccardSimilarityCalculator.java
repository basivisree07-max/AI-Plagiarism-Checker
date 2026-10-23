package com.plagiarism.similarity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JaccardSimilarityCalculator {

    public double calculate(
            List<String> tokens1,
            List<String> tokens2) {

        if (tokens1.isEmpty() || tokens2.isEmpty()) {
            return 0.0;
        }

        Set<String> set1 = new HashSet<>(tokens1);
        Set<String> set2 = new HashSet<>(tokens2);

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return ((double) intersection.size()
                / union.size()) * 100;
    }
}