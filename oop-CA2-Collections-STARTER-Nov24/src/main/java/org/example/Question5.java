package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;



public class Question5 {

    public static void readFile(String fileName) throws FileNotFoundException {
        Map<String, Integer> identifierCountMap = new TreeMap<>();
        Map<String, List<String>> identifierLinesMap = new TreeMap<>();
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);

        int lineNumber = 1; // Track the current line number
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();

            // Extract identifiers using regex
            String[] tokens = line.split("[^A-Za-z0-9_]+");
            for (String token : tokens) {
                if (!token.isEmpty()) {
                    // Update identifier count map
                    identifierCountMap.put(token, identifierCountMap.getOrDefault(token, 0) + 1);
                    // Update identifier lines map
                    identifierLinesMap
                            .computeIfAbsent(token, k -> new ArrayList<>())
                            .add(lineNumber + ". " + line);
                }
            }

            lineNumber++;
        }
        
        System.out.println("Identifiers and their counts:");
        for (String identifier : identifierCountMap.keySet()) {
            System.out.println(identifier);
            System.out.println(identifierCountMap.get(identifier));
            List<String> lines = identifierLinesMap.get(identifier);
            if (lines != null) {
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("C:\\Users\\lukep\\IdeaProjects\\oop-ca-luke-clarke\\src\\main\\java\\Question1.java"); // Adjust the path to match the location of the source file
    }
}