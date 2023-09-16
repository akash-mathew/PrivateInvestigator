package com.company.model;

import java.util.List;

public class SimilarWordsResult {
    private String matchedString;
    private List<String> changingWords;
    private List<String> sentences;

    public SimilarWordsResult(String matchedString, List<String> changingWords, List<String> sentences) {
        this.matchedString = matchedString;
        this.changingWords = changingWords;
        this.sentences = sentences;
    }

    public String getMatchedString() {
        return matchedString;
    }

    public List<String> getChangingWords() {
        return changingWords;
    }

    public List<String> getSentences() {
        return sentences;
    }
}
