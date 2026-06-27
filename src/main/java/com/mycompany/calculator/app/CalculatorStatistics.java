package com.mycompany.calculator.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aslam
 */
public class CalculatorStatistics {

    public double average(double[] numbers) {
        validateNotEmpty(numbers);
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }

    public double max(double[] numbers) {
        validateNotEmpty(numbers);
        double largest = numbers[0];
        for (double number : numbers) {
            if (number > largest) {
                largest = number;
            }
        }
        return largest;
    }
    public double min(double[] numbers) {
        validateNotEmpty(numbers);
        double smallest = numbers[0];
        for (double number : numbers) {
            if (number < smallest) {
                smallest = number;
            }
        }
        return smallest;
    }

    public double median(double[] numbers) {
        validateNotEmpty(numbers);
        double[] sorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(sorted);
        int middle = sorted.length / 2;
        if (sorted.length % 2 == 0) {
            return (sorted[middle - 1] + sorted[middle]) / 2.0;
        }
        return sorted[middle];
    }

    public double variance(double[] numbers) {
        validateNotEmpty(numbers);
        double mean = average(numbers);
        double sumSquaredDiff = 0;
        for (double number : numbers) {
            double diff = number - mean;
            sumSquaredDiff += diff * diff;
        }
        return sumSquaredDiff / numbers.length;
    }

    /**
     * Returns the population standard deviation, i.e. the square root of the variance,
     * which measures how spread out the supplied values are around their mean.
     * @param numbers the data set
     * @return the standard deviation of the data set
     */
    public double standardDeviation(double[] numbers) {
        return Math.sqrt(variance(numbers));
    }

    public double mode(double[] numbers) {
        validateNotEmpty(numbers);
        Map<Double, Integer> frequency = new HashMap<>();
        for (double number : numbers) {
            frequency.put(number, frequency.getOrDefault(number, 0) + 1);
        }
        double modeValue = numbers[0];
        int highestCount = 0;
        for (Map.Entry<Double, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                modeValue = entry.getKey();
            }
        }
        return modeValue;
    }

    public double range(double[] numbers) {
        return max(numbers) - min(numbers);
    }

    public List<Double> normalize(double[] numbers) {
        validateNotEmpty(numbers);
        double lowest = min(numbers);
        double highest = max(numbers);
        double span = highest - lowest;
        List<Double> normalized = new ArrayList<>();
        for (double number : numbers) {
            if (span == 0) {
                normalized.add(0.0);
            } else {
                normalized.add((number - lowest) / span);
            }
        }
        return normalized;
    }

    private void validateNotEmpty(double[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Statistics cannot be computed on an empty data set");
        }
    }
}
