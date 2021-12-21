package nl.abdel.aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Order(8)
class DigitDisplaySolverTest {

    private static final int[] NUMBER_OF_SEGMENTS_PER_DIGIT = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    List<Integer> digitsWithUniqueSegments = List.of(1, 4, 7, 8);

    public static final String EXAMPLE_NOTE_ENTRY = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf";

    public static final String LARGER_EXAMPLE_NOTES = """
            be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
            edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
            fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
            fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
            aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
            fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
            dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
            bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
            egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
            gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
            """;

    private static String puzzleInput;

    private DigitDisplaySolver digitDisplaySolver;

    @BeforeEach
    void setUp() throws IOException {
        digitDisplaySolver = new DigitDisplaySolver();
        puzzleInput = InputHelper.readFileToString("digit_display_signals.txt");
    }

    @Test
    void shouldOnlyContainDigitsWithFiveSegments() {
        var numberOfSegments = 5;

        var outputValue = digitDisplaySolver.solveNotes(EXAMPLE_NOTE_ENTRY);

        assertNumberOfDigits(4, outputValue);
        for (int digit = 0; digit < NUMBER_OF_SEGMENTS_PER_DIGIT.length; digit++) {
            if (NUMBER_OF_SEGMENTS_PER_DIGIT[digit] != numberOfSegments) {
                assertFalse(outputValue.contains(Integer.toString(digit)));
            }
        }
    }

    @Test
    void shouldContainTwoUniqueFiveSegmentDigits() {
        var numberOfSegments = 5;

        var outputValue = digitDisplaySolver.solveNotes(EXAMPLE_NOTE_ENTRY);

        assertNumberOfDigits(4, outputValue);
        int numberOfUniqueFiveSegmentDigits = 0;
        for (String digit : outputValue.split("")) {
            if (NUMBER_OF_SEGMENTS_PER_DIGIT[Integer.parseInt(digit)] == numberOfSegments) {
                numberOfUniqueFiveSegmentDigits++;
            }
        }

        assertTrue(numberOfUniqueFiveSegmentDigits >= 2);
    }

    @Test
    void shouldContain26DigitsWithUniqueNumberOfSegments() {
        var expectedLength = 49;
        var expectedNumberOfDigitsWithUniqueSegments = 26;

        var outputValue = digitDisplaySolver.solveNotes(LARGER_EXAMPLE_NOTES);

        assertEquals(expectedLength, outputValue.length());
        assertNumberOfDigitsWithUniqueSegments(expectedNumberOfDigitsWithUniqueSegments, outputValue);
    }

    @Test
    void shouldSolvePuzzleOne() {
        var expectedLength = 999;
        var expectedNumberOfDigitsWithUniqueSegments = 381;

        var outputValue = digitDisplaySolver.solveNotes(puzzleInput);

        assertEquals(expectedLength, outputValue.length());
        assertNumberOfDigitsWithUniqueSegments(expectedNumberOfDigitsWithUniqueSegments, outputValue);
    }

    private static void assertNumberOfDigits(int expectedNumberOfDigits, String outputValue) {
        assertEquals(expectedNumberOfDigits, outputValue.length(), "Output value does not have 4 digits");
        for (String digitString : outputValue.split("")) {
            try {
                Integer.parseInt(digitString);
            } catch (NumberFormatException e) {
                fail("Output value contains non-numeric character");
            }
        }
    }

    private void assertNumberOfDigitsWithUniqueSegments(int expectedNumberOfDigitsWithUniqueSegments, String outputValue) {
        int actualNumberOfDigitsWithUniqueSegments = 0;
        for (String digit : outputValue.replaceAll(" ", "").split("")) {
            if (digitsWithUniqueSegments.contains(Integer.parseInt(digit))) {
                actualNumberOfDigitsWithUniqueSegments++;
            }
        }
        assertEquals(expectedNumberOfDigitsWithUniqueSegments, actualNumberOfDigitsWithUniqueSegments);
    }

}
