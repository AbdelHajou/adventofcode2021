package nl.abdel.aoc;

/**
 * Day 1
 */
public class SonarSweeper {

    public int countDepthMeasurementIncrements(int[] depthMeasurements) {
        if (depthMeasurements.length == 0) return 0;

        int increments = 0;
        for (int i = 0; i < depthMeasurements.length - 1; i++) {
            if (depthMeasurements[i + 1] > depthMeasurements[i]) {
                increments++;
            }
        }

        return increments;
    }

    public int countSlidingWindowIncrements(int[] depthMeasurements, int windowSize) {
        int leftWindowSum = 0;
        int rightWindowSum = 0;
        int increments = 0;

        int right = 1;
        for (int left = 0; left < depthMeasurements.length - 1; left++) {
            if (left >= windowSize) {
                leftWindowSum -= depthMeasurements[left - windowSize];
            }

            if (right >= windowSize + 1) {
                rightWindowSum -= depthMeasurements[right - windowSize];
            }

            leftWindowSum += depthMeasurements[left];
            rightWindowSum += depthMeasurements[right];

            if (left >= windowSize - 1 && right >= windowSize && rightWindowSum > leftWindowSum) {
                increments++;
            }

            right++;
        }

        return increments;
    }
}
