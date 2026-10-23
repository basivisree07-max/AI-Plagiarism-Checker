package com.plagiarism.similarity;

import java.util.ArrayList;
import java.util.List;

public class CodeTokenizer {

    public List<String> tokenize(String code) {

        List<String> tokens = new ArrayList<>();

        if (code == null || code.isBlank()) {
            return tokens;
        }

        String cleanedCode = code
                .replaceAll("//.*", "")
                .replaceAll("/\\*.*?\\*/", " ");

        String[] rawTokens = cleanedCode.split(
                "[^a-zA-Z0-9_]+"
        );

        for (String token : rawTokens) {

            if (!token.isBlank()) {
                tokens.add(token.toLowerCase());
            }
        }

        return tokens;
    }
}