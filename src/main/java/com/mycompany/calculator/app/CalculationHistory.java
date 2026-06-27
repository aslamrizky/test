package com.mycompany.calculator.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

/**
 *
 */
public class CalculationHistory {

    private final List<HistoryEntry> entries = new ArrayList<>();

    public void record(String expression, double result) {
        entries.add(new HistoryEntry(expression, result, LocalDateTime.now()));
    }

    public List<HistoryEntry> getAllEntries() {
        return Collections.unmodifiableList(entries);
    }

    public HistoryEntry getLastEntry() {
        if (entries.isEmpty()) {
            return null;
        }
        return entries.get(entries.size() - 1);
    }

    public int size() {
        return entries.size();
    }

    public void clear() {
        entries.clear();
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    /**
     * Builds a multi-line, human readable transcript of every recorded calculation so far,
     * formatted roughly the way a printing calculator's paper tape would render each entry.
     */
    public String renderTranscript() {
        StringBuilder transcript = new StringBuilder();
        for (HistoryEntry entry : entries) {
            transcript.append(entry.toString()).append(System.lineSeparator());
        }
        return transcript.toString();
    }

    /**
     * A single immutable record of one calculator operation: the expression that was
     * evaluated, the numeric result, and the moment it happened.
     */
    public static class HistoryEntry {

        private final String expression;
        private final double result;
        private final LocalDateTime timestamp;

        public HistoryEntry(String expression, double result, LocalDateTime timestamp) {
            this.expression = expression;
            this.result = result;
            this.timestamp = timestamp;
        }

        public String getExpression() {
            return expression;
        }

        public double getResult() {
            return result;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {
            return "[" + timestamp + "] " + expression + " = " + result;
        }
    }
}
