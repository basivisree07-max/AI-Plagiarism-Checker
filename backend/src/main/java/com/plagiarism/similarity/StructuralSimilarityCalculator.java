package com.plagiarism.similarity;

public class StructuralSimilarityCalculator {

    public double calculate(
            String code1,
            String code2) {

        if (code1 == null || code2 == null
                || code1.isBlank()
                || code2.isBlank()) {

            return 0.0;
        }

        String structure1 =
                extractStructure(code1);

        String structure2 =
                extractStructure(code2);

        if (structure1.isEmpty()
                || structure2.isEmpty()) {

            return 0.0;
        }

        int maxLength =
                Math.max(
                        structure1.length(),
                        structure2.length()
                );

        int minLength =
                Math.min(
                        structure1.length(),
                        structure2.length()
                );

        return ((double) minLength
                / maxLength) * 100;
    }

    private String extractStructure(String code) {

        String lowerCode =
                code.toLowerCase();

        StringBuilder structure =
                new StringBuilder();

        String[] keywords = {
                "class",
                "if",
                "else",
                "for",
                "while",
                "switch",
                "case",
                "return",
                "try",
                "catch",
                "new"
        };

        for (String keyword : keywords) {

            int index = 0;

            while ((index =
                    lowerCode.indexOf(keyword, index))
                    != -1) {

                structure.append(keyword)
                        .append(" ");

                index += keyword.length();
            }
        }

        return structure.toString();
    }
}