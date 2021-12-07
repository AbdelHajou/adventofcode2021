package nl.abdel.aoc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(7)
class SubmarineFleetTest {

    private static final String EXAMPLE_INPUT = "16,1,2,0,4,2,7,1,2,14";
    private static final int EXAMPLE_IDEAL_POSITION_WITH_NEW_ENGINES = 2;
    private static final int EXAMPLE_IDEAL_POSITION_WITH_OLD_ENGINES = 5;

    private SubmarineFleet fleet;

    private static String puzzleInput;

    @BeforeEach
    void setUp() throws IOException {
        puzzleInput = InputHelper.readFileToString("crab_fleet.txt");
        fleet = new SubmarineFleet();
    }

    @Test
    void shouldFindIdealPosition() {
        fleet.spawnSubmarines(EXAMPLE_INPUT, EngineType.IMPROVED);

        var actualIdealPosition = fleet.findIdealPosition();

        assertEquals(EXAMPLE_IDEAL_POSITION_WITH_NEW_ENGINES, actualIdealPosition);
    }

    @Test
    void shouldCalculateTotalFuel() {
        var expectedTotalFuel = 37;
        fleet.spawnSubmarines(EXAMPLE_INPUT, EngineType.IMPROVED);

        var actualTotalFuel = fleet.calculateTotalFuelSpentTowardsPosition(EXAMPLE_IDEAL_POSITION_WITH_NEW_ENGINES);

        assertEquals(expectedTotalFuel, actualTotalFuel);
    }

    @Test
    void shouldSolvePuzzleOne() {
        var expectedIdealPosition = 298;
        var expectedTotalFuel = 356958;
        fleet.spawnSubmarines(puzzleInput, EngineType.IMPROVED);

        var actualIdealPosition = fleet.findIdealPosition();
        var actualTotalFuel = fleet.calculateTotalFuelSpentTowardsPosition(actualIdealPosition);

        assertEquals(expectedIdealPosition, actualIdealPosition);
        assertEquals(expectedTotalFuel, actualTotalFuel);
        System.out.println("Day seven part one solution: " + actualTotalFuel);
    }

    @Test
    void shouldFindIdealPositionWithOldEngines() {
        fleet.spawnSubmarines(EXAMPLE_INPUT, EngineType.OLD);

        var actualIdealPosition = fleet.findIdealPosition();

        assertEquals(EXAMPLE_IDEAL_POSITION_WITH_OLD_ENGINES, actualIdealPosition);
    }

    @Test
    void shouldCalculateTotalFuelWithOldEngines() {
        var expectedTotalFuel = 168;
        fleet.spawnSubmarines(EXAMPLE_INPUT, EngineType.OLD);

        var actualTotalFuel = fleet.calculateTotalFuelSpentTowardsPosition(EXAMPLE_IDEAL_POSITION_WITH_OLD_ENGINES);

        assertEquals(expectedTotalFuel, actualTotalFuel);
    }

    @Test
    void shouldSolvePuzzleTwo() {
        var expectedIdealPosition = 464;
        var expectedTotalFuel = 105461913;
        fleet.spawnSubmarines(puzzleInput, EngineType.OLD);

        var actualIdealPosition = fleet.findIdealPosition();
        var actualTotalFuel = fleet.calculateTotalFuelSpentTowardsPosition(actualIdealPosition);

        assertEquals(expectedIdealPosition, actualIdealPosition);
        assertEquals(expectedTotalFuel, actualTotalFuel);
        System.out.println("Day seven part two solution: " + actualTotalFuel);
    }

}
