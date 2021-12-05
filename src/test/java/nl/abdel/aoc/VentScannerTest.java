package nl.abdel.aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(5)
class VentScannerTest {

    private static final String exampleInput = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
            """;

    private static final int[][] firstExampleDiagram = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 1, 1, 2, 1, 1, 1, 2, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {2, 2, 2, 1, 1, 1, 0, 0, 0, 0},
    };

    private static final int[][] secondExampleDiagram = new int[][]{
            {1, 0, 1, 0, 0, 0, 0, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 2, 0, 0},
            {0, 0, 2, 0, 1, 0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0, 2, 0, 2, 0, 0},
            {0, 1, 1, 2, 3, 1, 3, 2, 1, 1},
            {0, 0, 0, 1, 0, 2, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {2, 2, 2, 1, 1, 1, 0, 0, 0, 0},
    };

    private VentScanner ventScanner;

    private static String puzzleInput;

    @BeforeEach
    void setUp() throws IOException {
        ventScanner = new VentScanner();
        puzzleInput = InputHelper.readFileToString("hydro_vents.txt");

    }

    @Test
    void shouldScanHorizontalLineSegment() {
        var expectedDiagram = new int[][]{
                {0, 0, 0, 0, 0, 1, 1, 1, 1}
        };
        var lineSegment = "5,0 -> 8,0";

        var actualDiagram = ventScanner.scanLineSegments(lineSegment, false);

        assertArrayEquals(expectedDiagram, actualDiagram);
    }

    @Test
    void shouldScanVerticalLineSegment() {
        var expectedDiagram = new int[][]{
                {0},
                {1},
                {1},
                {1},
                {1},
                {1}
        };
        var lineSegment = "0,1 -> 0,5";

        var actualDiagram = ventScanner.scanLineSegments(lineSegment, false);

        assertArrayEquals(expectedDiagram, actualDiagram);
    }

    @Test
    void shouldSolveExampleDiagram() {
        var actualDiagram = ventScanner.scanLineSegments(exampleInput, false);

        assertArrayEquals(firstExampleDiagram, actualDiagram);
    }

    @Test
    void shouldSolvePuzzleOne() {
        var expectedNumberOfOverlappingPoints = 6548;

        var actualDiagram = ventScanner.scanLineSegments(puzzleInput, false);
        var actualNumberOfOverlappingPoints = findNumberOfOverlappingPoints(actualDiagram);

        assertEquals(expectedNumberOfOverlappingPoints, actualNumberOfOverlappingPoints);
        System.out.println("Day five part one solution: " + expectedNumberOfOverlappingPoints);
    }

    @Test
    void shouldScanDiagonalLines() {
        var actualDiagram = ventScanner.scanLineSegments(exampleInput, true);

        assertArrayEquals(secondExampleDiagram, actualDiagram);
    }

    @Test
    void shouldSolvePuzzleTwo() {
        var expectedNumberOfOverlappingPoints = 19663;

        var actualDiagram = ventScanner.scanLineSegments(puzzleInput, true);
        var actualNumberOfOverlappingPoints = findNumberOfOverlappingPoints(actualDiagram);

        assertEquals(expectedNumberOfOverlappingPoints, actualNumberOfOverlappingPoints);
        System.out.println("Day five part two solution: " + expectedNumberOfOverlappingPoints);
    }

    private int findNumberOfOverlappingPoints(int[][] diagram) {
        int numberOfOverlappingPoints = 0;

        for (int[] row : diagram) {
            for (int point : row) {
                if (point >= 2) {
                    numberOfOverlappingPoints++;
                }
            }
        }

        return numberOfOverlappingPoints;
    }
}
