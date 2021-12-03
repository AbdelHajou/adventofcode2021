package nl.abdel.aoc;

/**
 * Day 1
 */
public class SonarSweeper {

    public int countDepthMeasurementIncrements(int[] depthMeasurements) {
        if (depthMeasurements.length <= 1) return 0;

        int increments = 0;
        for (int i = 1; i < depthMeasurements.length; i++) {
            if (depthMeasurements[i] > depthMeasurements[i - 1]) {
                increments++;
            }
        }

        return increments;
    }

    public int countSlidingWindowIncrements(int[] depthMeasurements, int windowSize) {
        if (depthMeasurements.length <= windowSize) return 0;

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
