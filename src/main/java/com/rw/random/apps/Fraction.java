package com.rw.random.apps;

import java.util.Objects;


public class Fraction {
    private final int numerator;
    private final int denominator;


    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
    }

    public Fraction(Fraction fraction) {
        this.numerator = fraction.numerator;
        this.denominator = fraction.denominator;
    }


    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction multiply(Fraction fraction) {
        Fraction resultantFraction = new Fraction(numerator * fraction.numerator, denominator * fraction.denominator);
        resultantFraction = simplify(resultantFraction);
        return resultantFraction;
    }

    public Fraction divide(Fraction fraction) {
        Fraction reciprocalFraction = new Fraction(fraction.denominator, fraction.numerator);
        return multiply(reciprocalFraction);
    }

    public Fraction add(Fraction fraction) {
        Fraction resultantFraction = new Fraction((numerator * fraction.denominator) + (denominator * fraction.numerator), denominator * fraction.denominator);
        resultantFraction = simplify(resultantFraction);
        return resultantFraction;
    }

    public Fraction subtract(Fraction fraction) {
        Fraction resultantFraction = new Fraction((numerator * fraction.denominator) - (denominator * fraction.numerator), denominator * fraction.denominator);
        resultantFraction = simplify(resultantFraction);
        return resultantFraction;
    }

    public double calculateFraction() {
        return doCalculate(this);
    }

    public boolean lessThan(Fraction fraction) {
        return doCalculate(this) < doCalculate(fraction);
    }

    public boolean greaterThan(Fraction fraction) {
        return doCalculate(this) > doCalculate(fraction);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Fraction) {
            Fraction that = (Fraction) o;
            return doCalculate(this) == doCalculate(that);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return Integer.toString(numerator);
        }
        return numerator + "/" + denominator;
    }

    private double doCalculate(Fraction fraction) {
        return (1.0 * fraction.numerator) / fraction.denominator;
    }

    private Fraction simplify(Fraction fraction) {
        int k = gcd(fraction.numerator, fraction.denominator);
        return new Fraction(fraction.numerator / k, fraction.denominator / k);
    }

    private int gcd(int a, int b) {
        while ((a < b) || ((a % b) != 0)) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return b;
    }
}
