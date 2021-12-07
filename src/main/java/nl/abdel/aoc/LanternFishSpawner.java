package nl.abdel.aoc;

import java.util.Arrays;

public class LanternFishSpawner {

    private static final int INITIAL_DAYS_LEFT = 8;
    private static final int DELAY_BETWEEN_BIRTHS = 6;
    private long[] fishPerDaysLeft = new long[INITIAL_DAYS_LEFT + 1];

    public void spawn(String initialState) {
        Arrays.stream(initialState.split(","))
                .map(Integer::parseInt)
                .forEach(timer -> fishPerDaysLeft[timer]++);
    }

    public void passDays(int numberOfDays) {
        for (int i = 0; i < numberOfDays; i++) {
            passDay();
        }
    }

    public void passDay() {
        long[] shifted = new long[fishPerDaysLeft.length];
        long newFishToSpawn = fishPerDaysLeft[0];

        for (int i = 0; i < fishPerDaysLeft.length ; i++) {
            if (i == 0) {
                shifted[INITIAL_DAYS_LEFT] = fishPerDaysLeft[i];
            } else {
                shifted[i - 1] = fishPerDaysLeft[i];
            }
        }

        shifted[DELAY_BETWEEN_BIRTHS] += newFishToSpawn;

        fishPerDaysLeft = shifted;
    }

    public Long getNumberOfFishSpawned() {
        long total = 0;
        for (long numberOfFish : fishPerDaysLeft) {
            total += numberOfFish;
        }
        return total;
    }

    public long getFishPerDaysLeft(int daysLeft) {
        if (daysLeft >= fishPerDaysLeft.length) return 0;

        return fishPerDaysLeft[daysLeft];
    }
}
