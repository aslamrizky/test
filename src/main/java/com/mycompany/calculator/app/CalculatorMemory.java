package com.mycompany.calculator.app;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Simulates the memory register found on a physical scientific calculator,
 * supporting store, recall, clear, and accumulate operations.
 */
public class CalculatorMemory {

    private static final int MAX_STACK_SIZE = 10;
    private double currentValue = 0.0;
    private final Deque<Double> memoryStack = new ArrayDeque<>();

    public void memoryStore(double value) {
        currentValue = value;
    }

    public double memoryRecall() {
        return currentValue;
    }

    public void memoryClear() {
        currentValue = 0.0;
    }

    public void memoryAdd(double value) {
        currentValue = currentValue + value;
    }
    public void memorySubtract(double value) {
        currentValue = currentValue - value;
    }

    /**
     * @param value the value to push onto the memory stack
     */
    public void pushMemory(double value) {
        if (memoryStack.size() >= MAX_STACK_SIZE)
            memoryStack.removeLast();
        memoryStack.push(value);
    }

    public double popMemory() {
        if (memoryStack.isEmpty()) {
            throw new IllegalStateException("Memory stack is empty, nothing to pop");
        }
        return memoryStack.pop();
    }

    public boolean isStackEmpty() {
        return memoryStack.isEmpty();
    }

    public int stackSize() {
        return memoryStack.size();
    }

    public void clearStack() {
        memoryStack.clear();
    }

    public double peekMemory() {
        if (memoryStack.isEmpty()) {
            return 0.0;
        }
        return memoryStack.peek();
    }

    public boolean hasStoredValue() {
        return currentValue != 0.0;
    }

    @Override
    public String toString() {
        return "CalculatorMemory{currentValue=" + currentValue + ", stackSize=" + memoryStack.size() + "}";
    }
}
