package com.rw.simple.random.apps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FractionTest {
    public FractionTest() {
    }


    @Test
    public void testFractionToString() {
        Fraction fraction1 = new Fraction(3);
        Assertions.assertEquals("3", fraction1.toString());
        Fraction fraction2 = new Fraction(1, 2);
        Assertions.assertEquals("1/2", fraction2.toString());
    }

    @Test
    public void testAddFractions() {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(3, 4);
        Fraction addResultFraction = fraction1.add(fraction2);
        Assertions.assertEquals(5, addResultFraction.getNumerator());
        Assertions.assertEquals(4, addResultFraction.getDenominator());
    }

    @Test
    public void testMultiplyFractions() {
        Fraction fraction1 = new Fraction(2, 6);
        Fraction fraction2 = new Fraction(2, 1);
        Fraction multiplyResultFraction = fraction1.multiply(fraction2);
        Assertions.assertEquals(2, multiplyResultFraction.getNumerator());
        Assertions.assertEquals(3, multiplyResultFraction.getDenominator());
    }

    @Test
    public void testEqualFractions() {
        Fraction fraction1 = new Fraction(1, 6);
        Fraction fraction2 = new Fraction(3, 18);
        Assertions.assertEquals(fraction1, fraction2);
    }

    @Test
    public void testLessThanFraction() {
        Fraction fraction1 = new Fraction(1, 9);
        Fraction fraction2 = new Fraction(1, 7);
        Assertions.assertTrue(fraction1.lessThan(fraction2));
    }
}
