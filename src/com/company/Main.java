package com.company;

import com.company.model.SimilarWordsResult;
import com.company.processor.SentenceProcessor;
import com.company.utility.FileIOUtility;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Main {

    public static void main(String[] args) {
        // Read lines from the input file located in the resources directory
        List<String> sentencesList = FileIOUtility.readLinesFromClasspath("resources/input.txt");

        // Initialize concurrent maps to store sentence groups and changing words
        ConcurrentMap<String, Set<String>> sentenceGroups = new ConcurrentHashMap<>();
        ConcurrentMap<String, Set<String>> changingWordsMap = new ConcurrentHashMap<>();

        // Process sentences in parallel
        sentencesList.parallelStream().forEach(sentence -> {
            // Find matching sentences and their changing words
            List<SimilarWordsResult> matchingWords = SentenceProcessor.findMatchingSentence(sentencesList, sentence);
            for (SimilarWordsResult matchingWord : matchingWords) {
                if (matchingWord != null) {
                    // Add matching sentences to the sentence groups
                    Set<String> sentencesSet = sentenceGroups.getOrDefault(matchingWord.getMatchedString(), new CopyOnWriteArraySet<>());
                    sentencesSet.addAll(matchingWord.getSentences());
                    sentenceGroups.put(matchingWord.getMatchedString(), sentencesSet);
                    // Add changing words to the changing words map
                    Set<String> changingWordSet = changingWordsMap.getOrDefault(matchingWord.getMatchedString(), new CopyOnWriteArraySet<>());
                    changingWordSet.addAll(matchingWord.getChangingWords());
                    changingWordsMap.put(matchingWord.getMatchedString(), changingWordSet);
                }
            }
        });

        // Write the formatted output to the output file
        FileIOUtility.writeLinesToClasspath("output.txt", FileIOUtility.formatOutput(sentenceGroups, changingWordsMap));
    }
}
