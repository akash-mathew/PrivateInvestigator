package com.company.processor;

import com.company.model.SimilarWordsResult;
import java.util.ArrayList;
import java.util.List;

public class SentenceProcessor {

    /**
     * Finds matching sentences in a list of sentences for a given sentence.
     *
     * @param sentencesList A list of sentences to search within.
     * @param sentence      The sentence for which to find matches.
     * @return A list of SimilarWordsResult objects representing matching sentences and changing words.
     */
    public static List<SimilarWordsResult> findMatchingSentence(List<String> sentencesList, String sentence) {
        List<SimilarWordsResult> matchingSentences = new ArrayList<>();
        for (String existingSentence : sentencesList) {
            SimilarWordsResult result = areSentencesSimilar(existingSentence, sentence);
            if (result != null) {
                matchingSentences.add(result);
            }
        }
        return matchingSentences;
    }

    /**
     * Compares two sentences and identifies if they are similar.
     *
     * @param line1 The first sentence to compare.
     * @param line2 The second sentence to compare.
     * @return A SimilarWordsResult object if the sentences are similar, otherwise null.
     */
    private static SimilarWordsResult areSentencesSimilar(String line1, String line2) {
        String sentence1 = extractSentence(line1);
        String sentence2 = extractSentence(line2);
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");

        List<String> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);

        if (words1.length != words2.length) {
            return null; // Sentences have different word counts
        }

        List<String> changingWords = new ArrayList<>();
        String matchedString = null;
        int diff = 0;
        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i])) {
                changingWords.add(words1[i]);
                changingWords.add(words2[i]);
                diff++;
            } else {
                if (matchedString == null) {
                    matchedString = words1[i];
                } else {
                    matchedString += " " + words1[i];
                }
            }
            if (diff > 1) {
                return null;
            }
        }

        return diff == 1 ? new SimilarWordsResult(matchedString, changingWords, lines) : null;
    }


    /**
     * Extracts the sentence part from a line (removes the date).
     *
     * @param line The input line containing a date and sentence.
     * @return The extracted sentence without the date.
     */
    private static String extractSentence(String line) {
        String sentence = line.substring(19); // Extract the sentence without the date
        return sentence;
    }
}
