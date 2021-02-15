package com.company;

public class ComplexNumber {
    private double real_num;
    private double imaginary_unit;

    public ComplexNumber(double rn, double iu) {
        this.real_num = rn;
        this.imaginary_unit = iu;
    }

    public ComplexNumber(double rn) {
        this.real_num = rn;
        this.imaginary_unit = 0;
    }

    public double getRn() {
        return this.real_num;
    }

    public double getIu() {
        return this.imaginary_unit;
    }

    public static ComplexNumber sum(ComplexNumber first, ComplexNumber second) {
        return new ComplexNumber(first.getRn() + second.getRn(), first.getIu() + second.getIu());
    }

    public static ComplexNumber sub(ComplexNumber first, ComplexNumber second) {
        return new ComplexNumber(first.getRn() - second.getRn(), first.getIu() - second.getIu());
    }

    public static ComplexNumber mul(ComplexNumber first, ComplexNumber second) {
        return new ComplexNumber(first.getRn() * second.getRn() - first.getIu() * second.getIu(),
                first.getRn() * second.getIu() + first.getIu() * second.getRn());
    }

    public static ComplexNumber div(ComplexNumber first, ComplexNumber second) {
        double den = second.getRn() * second.getRn() + second.getIu() * second.getIu();
        double num1 = first.getRn() * second.getRn() + first.getIu() * second.getIu();
        double num2 = second.getRn() * first.getIu() - first.getRn() * second.getIu();
        return new ComplexNumber(num1 / den, num2 / den);
    }

    public static double abs(ComplexNumber source) {
        return Math.sqrt(source.getRn() * source.getRn() + source.getIu() * source.getIu());
    }

    public String algebraic() {
        return ("(" + this.getRn() + " + " + this.getIu() + " * i)");
    }

    public String trigonometric() {
        double source_ = abs(this);
        return (source_ + " * (cos(" + Math.acos(this.getRn() / source_) + ") + i * sin(" +
                Math.asin(this.getIu() / source_) + "))");
    }
}
