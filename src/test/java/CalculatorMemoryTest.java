

import com.mycompany.calculator.app.CalculatorMemory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author aslam
 */
public class CalculatorMemoryTest {

    CalculatorMemory memory = new CalculatorMemory();

    @Test
    void testStoreAndRecall() {
        memory.memoryStore(42);
        assertEquals(42, memory.memoryRecall());
    }

    @Test
    void testMemoryAdd() {
        memory.memoryStore(10);
        memory.memoryAdd(5);
        assertEquals(15, memory.memoryRecall());
    }

    @Test
    void testMemorySubtract() {
        memory.memoryStore(10);
        memory.memorySubtract(4);
        assertEquals(6, memory.memoryRecall());
    }

    @Test
    void testMemoryClear() {
        memory.memoryStore(99);
        memory.memoryClear();
        assertEquals(0, memory.memoryRecall());
    }

    @Test
    void testPushAndPopMemory() {
        memory.pushMemory(1);
        memory.pushMemory(2);
        assertEquals(2, memory.popMemory());
        assertEquals(1, memory.popMemory());
    }

    @Test
    void testPopEmptyStackThrows() {
        assertThrows(IllegalStateException.class, memory::popMemory);
    }

    @Test
    void testIsStackEmpty() {
        assertTrue(memory.isStackEmpty());
        memory.pushMemory(1);
        assertFalse(memory.isStackEmpty());
    }

    @Test
    void testHasStoredValue() {
        assertFalse(memory.hasStoredValue());
        memory.memoryStore(7);
        assertTrue(memory.hasStoredValue());
    }
}
