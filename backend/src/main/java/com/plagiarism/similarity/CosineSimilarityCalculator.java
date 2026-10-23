package com.plagiarism.similarity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class CosineSimilarityCalculator {

    public double calculate(
            List<String> tokens1,
            List<String> tokens2) {

        if (tokens1.isEmpty() || tokens2.isEmpty()) {
            return 0.0;
        }

        Map<String, Integer> frequency1 =
                buildFrequencyMap(tokens1);

        Map<String, Integer> frequency2 =
                buildFrequencyMap(tokens2);

        Set<String> allTokens = new HashSet<>();

        allTokens.addAll(frequency1.keySet());
        allTokens.addAll(frequency2.keySet());

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for (String token : allTokens) {

            int value1 = frequency1.getOrDefault(token, 0);
            int value2 = frequency2.getOrDefault(token, 0);

            dotProduct += value1 * value2;

            magnitude1 += value1 * value1;
            magnitude2 += value2 * value2;
        }

        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0;
        }

        double cosine =
                dotProduct /
                        (Math.sqrt(magnitude1)
                                * Math.sqrt(magnitude2));

        return cosine * 100;
    }

    private Map<String, Integer> buildFrequencyMap(
            List<String> tokens) {

        Map<String, Integer> frequencyMap =
                new HashMap<>();

        for (String token : tokens) {

            frequencyMap.put(
                    token,
                    frequencyMap.getOrDefault(token, 0) + 1
            );
        }

        return frequencyMap;
    }
}