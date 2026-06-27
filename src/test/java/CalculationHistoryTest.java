

import com.mycompany.calculator.app.CalculationHistory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author aslam
 */
public class CalculationHistoryTest {

    CalculationHistory history = new CalculationHistory();

    @Test
    void testEmptyHistory() {
        assertTrue(history.isEmpty());
        assertNull(history.getLastEntry());
    }

    @Test
    void testRecordAddsEntry() {
        history.record("2 + 2", 4);
        assertEquals(1, history.size());
        assertEquals("2 + 2", history.getLastEntry().getExpression());
        assertEquals(4, history.getLastEntry().getResult());
    }

    @Test
    void testClear() {
        history.record("1 + 1", 2);
        history.clear();
        assertTrue(history.isEmpty());
    }

    @Test
    void testRenderTranscriptContainsExpression() {
        history.record("3 * 3", 9);
        String transcript = history.renderTranscript();
        assertTrue(transcript.contains("3 * 3"));
    }
}
