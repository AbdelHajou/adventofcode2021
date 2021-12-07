package nl.abdel.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubmarineFleet {

    record CrabSubmarine(int position, EngineType engineType) {
        int calculateFuelTowardsPosition(int targetPosition) {
            return switch (engineType) {
                case OLD -> increasingFuel(Math.abs(targetPosition - position));
                case IMPROVED -> Math.abs(targetPosition - position);
            };
        }

        private int increasingFuel(int steps) {
            int totalFuel = 0;
            int fuelExpenditure = 1;
            for (int i = 0; i < steps; i++) {
                totalFuel += fuelExpenditure++;
            }
            return totalFuel;
        }
    }

    private final List<CrabSubmarine> submarines = new ArrayList<>();

    public void spawnSubmarines(String horizontalPositions, EngineType engineType) {
        submarines.addAll(
                Arrays.stream(horizontalPositions.split(","))
                        .map(Integer::parseInt)
                        .map(position -> new CrabSubmarine(position, engineType))
                        .toList()
        );
    }

    public Integer findIdealPosition() {
        int maxSubmarinePosition = submarines.stream().mapToInt(crab -> crab.position).max().getAsInt();

        Integer cheapestPosition = null;
        int cheapestTotalFuel = 0;
        for (int pos = 0; pos <= maxSubmarinePosition; pos++) {
            int totalFuelTowardsPosition = calculateTotalFuelSpentTowardsPosition(pos);
            if (cheapestPosition == null || totalFuelTowardsPosition < cheapestTotalFuel) {
                cheapestPosition = pos;
                cheapestTotalFuel = totalFuelTowardsPosition;
            }
        }
        return cheapestPosition;
    }

    public int calculateTotalFuelSpentTowardsPosition(int targetPosition) {
        int totalFuel = 0;
        for (CrabSubmarine crabSubmarine : submarines) {
            totalFuel += crabSubmarine.calculateFuelTowardsPosition(targetPosition);
        }
        return totalFuel;
    }
}

enum EngineType {
    OLD,
    IMPROVED
}
