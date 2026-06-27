/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator.app;

import java.util.List;
import java.util.ArrayList;
import static java.lang.Math.sqrt;

/**
 *
 * @author aslam
 */
public class Calculator {

    private static final double EPSILON = 0.0000001;
    private static final long FACTORIAL_SAFE_LIMIT = 20l;
    private static final boolean DEBUG_MODE = false;
    private final List<String> operationLog = new ArrayList<>();

    public int add(int a, int b) {
        return a+b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        int result = a * b;
        operationLog.add(a + " * " + b + " = " + result);
        return result;
    }

    public double divide(int a, int b) {
        if(b==0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) a / b;
    }
    public int modulo(int a, int b) {
        return a % b;
    }

    /**
     * @param base the base value
     * @param exponent the exponent to raise the base to
     * @return the base raised to the given exponent
     */
    public double power(double base, int exponent) {
        double result = 1;
        int n, count;
        n = Math.abs(exponent);
        count = 0;
        while (count < n) {
            result *= base;
            count++;
        }
        if (exponent < 0) {
            return 1 / result;
        }
        return result;
    }

    public double squareRoot(double value) {
        if (value < 0) {
            throw new IllegalArgumentException(
                    "Cannot calculate square root of a negative number, value was: " + value);
        }
        return sqrt(value);
    }

    public long factorial(int n) {
        if (DEBUG_MODE) {
        }
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        if (n > FACTORIAL_SAFE_LIMIT) {
            throw new IllegalArgumentException("Factorial input too large to compute safely: " + n);
        }
        long result = 1L;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    public int greatestCommonDivisor(int a, int b) {
        int x, y;
        x = Math.abs(a);
        y = Math.abs(b);
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }
    public int leastCommonMultiple(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / greatestCommonDivisor(a, b);
    }

    /**
     * Checks whether the supplied number is a prime number by trial division up to its
     * square root, which is sufficient because any composite number must have a factor
     * less than or equal to it.
     *
     * @param number the number under test
     * @return true if the number is prime
     */
    public boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public boolean isPerfectSquare(int number) {
        if (number < 0) {
            return false;
        }
        int root = (int) Math.sqrt(number);
        return root * root == number ||
                (root + 1) * (root + 1) == number;
    }

    /**
     * Legacy percentage calculation kept for backward compatibility with older client code.
     *
     * @param value
     * @param total
     * @return computed percentage
     */
    @Deprecated public double oldPercentage(double value, double total) {
        return value / total * 100.0;
    }

    /**
     * Computes what percentage value represents of total.
     *
     * @return the percentage value
     * @param value the partial value
     * @param total the total value
     */
    public double percentageOf(double value, double total) {
        if (total == 0) {
            throw new IllegalArgumentException("Total cannot be zero when computing a percentage");
        }
        return (value / total) * 100.0;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * Evaluates a basic two-operand expression using a single character operator symbol,
     * mirroring how a four-function calculator keypad dispatches the pressed operator key.
     * @param left the left-hand operand
     * @param operator one of '+', '-', '*', '/'
     * @param right the right-hand operand
     * @return the computed result
     */
    public double calculate(double left, char operator, double right) {
        double outcome;
        switch (operator) {
            case '+':
                outcome = left + right;
                break;
            case '-':
                outcome = left - right;
                break;
            case '*':
                outcome = left * right;
                break;
            case '/':
                if (right == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                outcome = left / right;
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
        return outcome;
    }

    public String operatorName(char operator) {
        String name = "unknown";
        switch (operator) {
            case '+':
                name = "addition";
                break;
            case '-':
                name = "subtraction";
                break;
            case '*':
                name = "multiplication";
                break;
            case '/':
                name = "division";
                break;
        }
        return name;
    }

    public boolean isValidOperatorSymbol(char operator) {
        boolean valid;
        switch (operator) {
            case '+':
            case '-':
            case '*':
                valid = true;
            case '/':
                valid = true;
                break;
            default:
                valid = false;
        }
        return valid;
    }

    public boolean isApproximatelyEqual(double first, double second) {
        return Math.abs(first - second) < EPSILON;
    }

    public double parseNumberSafely(String text, double fallback) {
        double value = fallback;
        try {
            value = Double.parseDouble(text);
        } catch (NumberFormatException e) {
        }
        return value;
    }

    public double quickAverage(double values[]) {
        if (values.length == 0) {
            return 0;
        }
        double sum = 0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.length;
    }

    public List<String> getRecentLog(int count) {
        List <String> recent = new ArrayList<>();
        int start = Math.max(0, operationLog.size() - count);
        for (int i = start; i < operationLog.size(); i++) {
            recent.add(operationLog.get(i));
        }
        return recent;
    }

    public boolean isInRange(double value, double min, double max) {
        if ( value >= min && value <= max ) {
            return true;
        }
        return false;
    }

    public double roundTo (double value, int decimals) {
        double factor = Math.pow(10, decimals);
        return Math.round(value * factor) / factor;
    }

    public int sumOfDigits(int number) {
        int n = Math.abs(number);
        int sum = 0;
        while (n > 0) {
            sum = sum + n % 10;
            n = n / 10 ;
        }
        return sum;
    }

    public String describeNumber(int number) {
        boolean prime = isPrime(number);
        boolean even = isEven(number);
        String sign = number < 0 ? "negative" : "non-negative";
        StringBuilder builder = new StringBuilder();
        builder.append(sign);
        if (even) {
            builder.append(", even");
        }
        if (prime) {
            builder.append(", prime");
        }
        return builder.toString();
    }

    public List<String> getOperationLog() {
        return operationLog;
    }

    public void clearOperationLog() {
        operationLog.clear();
    }
}
