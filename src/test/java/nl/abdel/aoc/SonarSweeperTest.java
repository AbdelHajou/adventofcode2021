package nl.abdel.aoc;

import nl.abdel.aoc.helper.InputHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SonarSweeperTest {

    private SonarSweeper sonarSweeper = new SonarSweeper();

    private static int[] puzzleInput;

    @BeforeAll
    static void setUp() throws IOException {
        puzzleInput = InputHelper.readLinesToIntegers("depth_measurements.txt");
    }

    @ParameterizedTest
    @MethodSource("provideMeasurementsForPuzzleOne")
    void shouldCountCorrectNumberOfLinearIncreasements(int[] depthMeasurements, int expected) {
        var actual = sonarSweeper.countDepthMeasurementIncrements(depthMeasurements);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideMeasurementsForPuzzleTwo")
    void shouldCountCorrectNumberOfSlidingWindowIncreasements(int[] depthMeasurements, int expected) {
        var actual = sonarSweeper.countSlidingWindowIncrements(depthMeasurements, 3);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideMeasurementsForPuzzleOne() {
        return Stream.of(
                Arguments.of(new int[]{ 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 }, 7),
                Arguments.of(new int[]{ }, 0),
                Arguments.of(new int[]{ 199 }, 0),
                Arguments.of(puzzleInput, 1292)
        );
    }

    private static Stream<Arguments> provideMeasurementsForPuzzleTwo() {
        return Stream.of(
                Arguments.of(new int[]{ 199, 200, 208, 210, 200, 207, 240, 269, 260, 263 }, 5),
                Arguments.of(new int[]{ }, 0),
                Arguments.of(new int[]{ 199, 200, 208 }, 0),
                Arguments.of(new int[]{ 199, 200, 208, 210 }, 1),
                Arguments.of(puzzleInput, 1262)
        );
    }
}