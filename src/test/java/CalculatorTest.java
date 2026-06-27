/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.calculator.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author aslam
 */
public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    void testAdd() {
        assertEquals(8, calc.add(5, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, calc.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(15, calc.multiply(5, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(5, 0));
    }

    @Test
    void testModulo() {
        assertEquals(1, calc.modulo(7, 3));
    }

    @Test
    void testPower() {
        assertEquals(1024.0, calc.power(2, 10));
    }

    @Test
    void testSquareRoot() {
        assertEquals(9.0, calc.squareRoot(81));
    }

    @Test
    void testSquareRootNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.squareRoot(-4));
    }

    @Test
    void testFactorial() {
        assertEquals(5040L, calc.factorial(7));
    }

    @Test
    void testFactorialNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }

    @Test
    void testGreatestCommonDivisor() {
        assertEquals(6, calc.greatestCommonDivisor(48, 18));
    }

    @Test
    void testLeastCommonMultiple() {
        assertEquals(12, calc.leastCommonMultiple(4, 6));
    }

    @Test
    void testIsPrimeTrue() {
        assertTrue(calc.isPrime(17));
    }

    @Test
    void testIsPrimeFalse() {
        assertFalse(calc.isPrime(18));
    }

    @Test
    void testIsEven() {
        assertTrue(Calculator.isEven(4));
        assertFalse(Calculator.isEven(7));
    }

    @Test
    void testIsPerfectSquare() {
        assertTrue(calc.isPerfectSquare(64));
        assertFalse(calc.isPerfectSquare(50));
    }

    @Test
    void testPercentageOf() {
        assertEquals(25.0, calc.percentageOf(5, 20));
    }

    @Test
    void testCalculateAddition() {
        assertEquals(7.0, calc.calculate(3, '+', 4));
    }

    @Test
    void testCalculateDivisionByZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> calc.calculate(1, '/', 0));
    }

    @Test
    void testOperatorName() {
        assertEquals("addition", calc.operatorName('+'));
    }

    @Test
    void testIsApproximatelyEqual() {
        assertTrue(calc.isApproximatelyEqual(1.0, 1.00000005));
    }

    @Test
    void testQuickAverage() {
        assertEquals(3.0, calc.quickAverage(new double[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testIsInRange() {
        assertTrue(calc.isInRange(5, 1, 10));
        assertFalse(calc.isInRange(15, 1, 10));
    }

    @Test
    void testRoundTo() {
        assertEquals(3.14, calc.roundTo(3.14159, 2));
    }

    @Test
    void testSumOfDigits() {
        assertEquals(6, calc.sumOfDigits(123));
    }
}
