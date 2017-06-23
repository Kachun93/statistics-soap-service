package nl.hu.iac.ws;

import java.util.List;

public final class Statistics {
    private static double lastResult;

    private Statistics() {}

    public static double getLastResult() {
        return lastResult;
    }

    public static double getMean(List<Double> data) {
        double sum = 0.0;

        for (double a : data) {
            sum += a;
        }

        double result = sum / data.size();
        lastResult = result;

        return result;
    }

    public static double getVariance(List<Double> data) {
        double mean = getMean(data);
        double temp = 0;

        for (double a : data) {
            temp += (a - mean) * (a - mean);
        }

        double result = temp / (data.size() - 1);
        lastResult = result;

        return result;
    }

    public static double getStandardDeviation(List<Double> data) {
        double result = Math.sqrt(getVariance(data));
        lastResult = result;

        return result;
    }
}
