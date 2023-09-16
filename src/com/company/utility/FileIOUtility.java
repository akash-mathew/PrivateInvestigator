package com.company.utility;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileIOUtility {

    /**
     * Reads lines from a file located on the classpath.
     *
     * @param filePath The path to the file on the classpath.
     * @return A list of lines read from the file.
     */
    public static List<String> readLinesFromClasspath(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            InputStream inputStream = FileIOUtility.class.getClassLoader().getResourceAsStream(filePath);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Writes lines to a file located on the classpath.
     *
     * @param filePath The path to the file on the classpath.
     * @param lines    A list of lines to write to the file.
     */
    public static void writeLinesToClasspath(String filePath, List<String> lines) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(filePath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats sentence groups and changing words map into a list of strings.
     *
     * @param sentenceGroups    A map of sentence groups.
     * @param changingWordsMap  A map of changing words.
     * @return A list of formatted output strings.
     */
    public static List<String> formatOutput(Map<String, Set<String>> sentenceGroups, Map<String, Set<String>> changingWordsMap) {
        List<String> formattedOutput = new ArrayList<>();
        for (String key : sentenceGroups.keySet()) {
            for (String value : sentenceGroups.get(key)) {
                formattedOutput.add(value);
            }
            formattedOutput.add("The changing word was: " + String.join(",", changingWordsMap.get(key)));
        }
        return formattedOutput;
    }
}

