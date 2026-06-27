/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculator.app;

import java.util.Scanner;
import java.util.List;

/**
 *
 * @author aslam
 */
public class CalculatorApp {

    private static final Calculator CALC = new Calculator();
    private static final CalculatorMemory MEMORY = new CalculatorMemory();
    private static final CalculatorStatistics STATS = new CalculatorStatistics();
    private static final CalculationHistory HISTORY = new CalculationHistory();

    public static void main(String[] args) {
        BuildInfo buildInfo = BuildInfo.load();
        System.out.println(buildInfo.toBannerString());
        System.out.println("==============================================");

        runBasicArithmeticDemo();
        runScientificDemo();
        runMemoryDemo();
        runStatisticsDemo();
        printHistoryTranscript();

        if (args.length > 0 && "--interactive".equals(args[0])) {
            runInteractiveMenu();
        }
    }

    private static void runBasicArithmeticDemo() {
        int sum = CALC.add(5, 3);
        int difference = CALC.subtract(5, 3);
        int product = CALC.multiply(5, 3);
        System.out.println("5 + 3 = " + sum);
        System.out.println("5 - 3 = " + difference);
        System.out.println("5 * 3 = " + product);
        System.out.println("5 / 3 = " + CALC.divide(5, 3));
        System.out.println("5 % 3 = " + CALC.modulo(5, 3));
        HISTORY.record("5 + 3", sum);
        HISTORY.record("5 - 3", difference);
        HISTORY.record("5 * 3", product);
    }

    private static void runScientificDemo() {
        System.out.println("2 ^ 10 = " + CALC.power(2, 10));
        System.out.println("sqrt(81) = " + CALC.squareRoot(81));
        System.out.println("7! = " + CALC.factorial(7));
        System.out.println("gcd(48, 18) = " + CALC.greatestCommonDivisor(48, 18));
        System.out.println("lcm(4, 6) = " + CALC.leastCommonMultiple(4, 6));
        System.out.println("isPrime(17) = " + CALC.isPrime(17));
        System.out.println("isPerfectSquare(64) = " + CALC.isPerfectSquare(64));
        HISTORY.record("2 ^ 10", CALC.power(2, 10));
        HISTORY.record("sqrt(81)", CALC.squareRoot(81));
    }

    private static void runMemoryDemo() {
        MEMORY.memoryStore(100);
        MEMORY.memoryAdd(25);
        MEMORY.memorySubtract(5);
        System.out.println("Memory recall = " + MEMORY.memoryRecall());
        MEMORY.pushMemory(MEMORY.memoryRecall());
        MEMORY.pushMemory(42);
        System.out.println("Stack size = " + MEMORY.stackSize());
        System.out.println("Pop = " + MEMORY.popMemory());
    }

    private static void runStatisticsDemo() {
        double[] dataset = {4, 8, 15, 16, 23, 42};
        System.out.println("average = " + STATS.average(dataset));
        System.out.println("median = " + STATS.median(dataset));
        System.out.println("stddev = " + STATS.standardDeviation(dataset));
        System.out.println("max = " + STATS.max(dataset) + ", min = " + STATS.min(dataset));
    }

    private static void printHistoryTranscript() {
        System.out.println("---------------- history ---------------------");
        System.out.print(HISTORY.renderTranscript());
        System.out.println("Recent log: " + summarizeLog(CALC.getRecentLog(5)));
    }

    private static String summarizeLog(List<String> entries) {
        StringBuilder builder = new StringBuilder();
        for (String entry : entries) {
            builder.append(entry).append(" | ");
        }
        return builder.toString();
    }

    /**
     * Runs a simple text menu loop so the calculator can also be driven interactively
     * from a terminal, in addition to the canned demo that always runs on startup.
     */
    private static void runInteractiveMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Enter expression as: <a> <op> <b>  (op is + - * /), or 'quit'");
            String line = scanner.nextLine().trim();
            if ("quit".equalsIgnoreCase(line)) {
                running = false;
            } else {
                handleInteractiveLine(line);
            }
        }
        scanner.close();
    }

    private static void handleInteractiveLine(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length != 3) {
            System.out.println("Invalid input, expected 3 tokens");
            return;
        }
        try {
            double a = Double.parseDouble(parts[0]);
            char op = parts[1].charAt(0);
            double b = Double.parseDouble(parts[2]);
            double result = CALC.calculate(a, op, b);
            System.out.println("= " + result);
            HISTORY.record(parts[0] + " " + parts[1] + " " + parts[2], result);
        } catch (NumberFormatException e) {
            System.out.println("Could not parse numbers in: " + line);
        }
    }
}
