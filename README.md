# Private Investigator

## Overview of the Solution

This application processes a list of sentences to find similar sentences. Sentences that differ by only one word are considered similar. It groups similar sentences and identifies changing words within them.

### Logic overview

The application follows these key steps to process and group similar sentences:

1. **Reading Input**: The program reads input sentences from an input text file, which is specified as `resources/input.txt`. It uses a utility class, `FileIOUtility`, to handle file I/O operations.

2. **Sentence Processing**: For each sentence in the input, the application employs parallel processing using Java Streams. It compares the current sentence with all other sentences in the input file to identify similarities. Sentences that differ by only one word are considered similar.

3. **Similarity Detection**: To detect similarities, the code splits each sentence into words, and then it iterates over corresponding words in different sentences. If it finds two sentences that differ by only one word, it groups them together.

4. **Data Structures**: To efficiently group similar sentences, the application uses concurrent data structures: `ConcurrentMap` to store groups of similar sentences (`sentenceGroups`) and `CopyOnWriteArraySet` to store changing words (`changingWordsMap`). These data structures are thread-safe for concurrent access.

5. **Output Formatting**: After processing all sentences, the application formats the results. It generates an output file, `output.txt`, which contains groups of similar sentences and the corresponding changing words.

6. **Performance Considerations**: Parallel processing is utilized to improve performance. However, the complexity of the code is approximately O(n^2), where `n` is the number of sentences, due to the comparison of all sentences with each other.

7. **Error Handling**: Basic error handling is in place, such as file I/O exceptions. Further improvements can be made in error handling for robustness.

## Complexity

1. **Time Complexity**: The application has a time complexity of O(n^2), where n is the number of sentences. This is because, for each sentence, it compares with all other sentences to find similarities.

2. **Space Complexity**: The space complexity is O(m), where m is the number of distinct similar phrases found in the input sentences. This is because the application stores these phrases in memory.

## Scalability

The current implementation may not scale efficiently for a large number of sentences. The time complexity grows quadratically with the number of sentences, which can lead to performance issues for large datasets.
Using parallel processing we have reduced this largely.

## Two Weeks' Enhancement

If there were more time available, several enhancements could be made:

- **Optimizations**: Optimize the similarity comparison algorithm for better performance.
- **Unit Tests**: Develop comprehensive unit tests for robustness.
- **Logging**: Add logging to improve debugging.
- **Exception Handling**: Implement more robust exception handling.
- **Configurability**: Make parameters and file paths configurable.
- **User Interface**: Create a simple user interface for better usability.
- **Performance Testing**: Conduct performance testing on large datasets to identify bottlenecks and optimize performance.
- **Code Structure**: Review and refactor code structure for better organization
