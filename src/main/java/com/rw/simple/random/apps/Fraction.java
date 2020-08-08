package com.rw.simple.random.apps;

public class Fraction {
    private int numerator;
    private int denominator;


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
        simplify(resultantFraction);
        return resultantFraction;
    }

    public Fraction add(Fraction fraction) {
        Fraction resultantFraction = new Fraction((numerator * fraction.denominator) + (denominator * fraction.numerator), denominator * fraction.denominator);
        simplify(resultantFraction);
        return resultantFraction;
    }

    public double calculateFraction() {
        return doCalculate(this);
    }

    public boolean lessThan(Fraction fraction) {
        return doCalculate(this) < doCalculate(fraction);
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
    public String toString() {
        if (denominator == 1) {
            return "" + numerator;
        }
        return numerator + "/" + denominator;
    }

    private double doCalculate(Fraction fraction) {
        return (1.0 * fraction.numerator) / fraction.denominator;
    }

    private void simplify(Fraction fraction) {
        int k = gcd(fraction.numerator, fraction.denominator);
        fraction.numerator = fraction.numerator / k;
        fraction.denominator = fraction.denominator / k;
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
