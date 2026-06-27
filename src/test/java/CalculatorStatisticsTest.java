

import com.mycompany.calculator.app.CalculatorStatistics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author aslam
 */
public class CalculatorStatisticsTest {

    CalculatorStatistics stats = new CalculatorStatistics();
    double[] dataset = {4, 8, 15, 16, 23, 42};

    @Test
    void testAverage() {
        assertEquals(18.0, stats.average(dataset));
    }

    @Test
    void testMax() {
        assertEquals(42.0, stats.max(dataset));
    }

    @Test
    void testMin() {
        assertEquals(4.0, stats.min(dataset));
    }

    @Test
    void testMedianEvenCount() {
        assertEquals(15.5, stats.median(dataset));
    }

    @Test
    void testMedianOddCount() {
        assertEquals(8.0, stats.median(new double[]{4, 8, 15}));
    }

    @Test
    void testRange() {
        assertEquals(38.0, stats.range(dataset));
    }

    @Test
    void testEmptyDatasetThrows() {
        assertThrows(IllegalArgumentException.class, () -> stats.average(new double[]{}));
    }

    @Test
    void testStandardDeviationIsNonNegative() {
        double deviation = stats.standardDeviation(dataset);
        assertEquals(deviation, Math.abs(deviation));
    }
}
