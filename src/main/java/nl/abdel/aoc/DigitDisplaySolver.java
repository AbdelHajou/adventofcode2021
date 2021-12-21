package nl.abdel.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DigitDisplaySolver {

    private static final int[] NUMBER_OF_SEGMENTS_PER_DIGIT = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    public String solveNotes(String notes) {
        Stream<String> noteEntries = notes.lines();
        Stream<String> outputValues = noteEntries.map(this::solveNoteEntry);

        return outputValues.collect(Collectors.joining(" "));
    }

    private String solveNoteEntry(String entry) {
        String[] signalPatterns = entry.split("\\|")[0].split(" ");
        String[] outputPatterns = entry.split("\\|")[1].split(" ");

        Map<String, List<Integer>> possibleDigitsPerPattern = findPossibleDigitsPerPattern(signalPatterns);
        return findOutputValue(outputPatterns, possibleDigitsPerPattern);
    }

    private String findOutputValue(String[] outputPatterns, Map<String, List<Integer>> possibleDigitsPerPattern) {
        StringBuilder outputValue = new StringBuilder();

        for (String outputPattern : outputPatterns) {
            for (String possiblePattern : possibleDigitsPerPattern.keySet()) {
                if (patternsAreEqual(outputPattern, possiblePattern)) {
                    List<Integer> possibleDigits = possibleDigitsPerPattern.get(possiblePattern);
                    outputValue.append(possibleDigits.get(0));
                }
            }
        }

        return outputValue.toString();
    }

    private Map<String, List<Integer>> findPossibleDigitsPerPattern(String[] signalPatterns) {
        Map<String, List<Integer>> possibleDigitsPerPattern = new HashMap<>();
        for (String signalPattern : signalPatterns) {
            for (int digit = 0; digit < NUMBER_OF_SEGMENTS_PER_DIGIT.length; digit++) {
                if (signalPattern.length() == NUMBER_OF_SEGMENTS_PER_DIGIT[digit]) {
                    if (!possibleDigitsPerPattern.containsKey(signalPattern)) {
                        possibleDigitsPerPattern.put(signalPattern, new ArrayList<>());
                    }
                    possibleDigitsPerPattern.get(signalPattern).add(digit);
                }
            }
        }
        return possibleDigitsPerPattern;
    }

    private static boolean patternsAreEqual(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        for (String character : a.split("")) {
            if (!b.contains(character)) {
                return false;
            }
        }

        return true;
    }
}
