package nl.abdel.aoc;

import nl.abdel.aoc.helper.InputHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiagnosticReportTest {

    private final String exampleReport = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
            """;

    private static String puzzleReport;

    @BeforeEach
    void setUp() throws IOException {
        puzzleReport = InputHelper.readFileToString("diagnostic_report.txt");
    }

    @Test
    void shouldCalculateGammaRate() {
        var expectedGammaRate = 22;

        var diagnosticReport = new DiagnosticReport(exampleReport);

        assertEquals(expectedGammaRate, diagnosticReport.getGammaRate());
    }

    @Test
    void shouldCalculateEpsilon() {
        var expectedEpsilon = 9;

        var diagnosticReport = new DiagnosticReport(exampleReport);

        assertEquals(expectedEpsilon, diagnosticReport.getEpsilonRate());
    }

    @Test
    void shouldCalculatePowerConsumption() {
        var expectedPowerConsumption = 198;

        var diagnosticReport = new DiagnosticReport(exampleReport);

        assertEquals(expectedPowerConsumption, diagnosticReport.getPowerConsumption());
    }

    @Test
    void shouldSolvePuzzleOne() {
        var expectedGamma = 349;
        var expectedEpsilon = 3746;
        var expectedPowerConsumption = 1307354;

        var diagnosticReport = new DiagnosticReport(puzzleReport);

        assertEquals(expectedGamma, diagnosticReport.getGammaRate());
        assertEquals(expectedEpsilon, diagnosticReport.getEpsilonRate());
        assertEquals(expectedPowerConsumption, diagnosticReport.getPowerConsumption());
        System.out.println("Day three part one solution: " + diagnosticReport.getPowerConsumption());
    }

    @Test
    void shouldCalculateOxygenGeneratorRating() {
        var expectedOxygenGeneratorRating = 23;

        var diagnosticReport = new DiagnosticReport(exampleReport);

        assertEquals(expectedOxygenGeneratorRating, diagnosticReport.getOxygenGeneratorRating());
    }

    @Test
    void shouldCalculateCo2ScrubberRating() {
        var expectedCo2ScrubberRating = 10;

        var diagnosticReport = new DiagnosticReport(exampleReport);

        assertEquals(expectedCo2ScrubberRating, diagnosticReport.getCo2ScrubberRating());
    }

    @Test
    void shouldCalculateLifeSupportRating() {
        var expectedLifeSupportRating = 230;

        var diagnosticReport = new DiagnosticReport(exampleReport);

        assertEquals(expectedLifeSupportRating, diagnosticReport.getLifeSupportRating());
    }

    @Test
    void shouldSolvePuzzleTwo() {
        var expectedOxygenGeneratorRating = 125;
        var expectedCo2ScrubberRating = 3860;
        var expectedLifeSupportRating = 482500;

        var diagnosticReport = new DiagnosticReport(puzzleReport);

        assertEquals(expectedOxygenGeneratorRating, diagnosticReport.getOxygenGeneratorRating());
        assertEquals(expectedCo2ScrubberRating, diagnosticReport.getCo2ScrubberRating());
        assertEquals(expectedLifeSupportRating, diagnosticReport.getLifeSupportRating());
        System.out.println("Day three part two solution: " + diagnosticReport.getLifeSupportRating());
    }
}
