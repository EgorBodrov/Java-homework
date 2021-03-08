package com.company;

public class ComplexNumber {
    private double real_num;
    private double imaginary_unit;

    public ComplexNumber() {
        this.real_num = 0;
        this.imaginary_unit = 0;
    }

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

    public void sum(ComplexNumber source) {
        this.real_num += source.getRn();
        this.imaginary_unit += source.getIu();
    }

    public void sub(ComplexNumber source) {
        this.real_num -= source.getRn();
        this.imaginary_unit -= source.getIu();
    }

    public void mul(ComplexNumber source) {
        this.real_num = this.getRn() * source.getRn() - this.getIu() * source.getIu();
        this.imaginary_unit = this.getRn() * source.getIu() + this.getIu() * source.getRn();
    }

    public void div(ComplexNumber source) {
        double den = source.getRn() * source.getRn() + source.getIu() * source.getIu();
        double num1 = this.getRn() * source.getRn() + this.getIu() * source.getIu();
        double num2 = source.getRn() * this.getIu() - this.getRn() * source.getIu();
        this.real_num = num1 / den;
        this.imaginary_unit = num2 / den;
    }

    public double abs(ComplexNumber source) {
        return Math.sqrt(source.getRn() * source.getRn() + source.getIu() * source.getIu());
    }

    public String algebraic() {
        if (this.imaginary_unit > 0) {
            return ("(" + this.getRn() + " + " + this.getIu() + " * i)");
        } else {
            return ("( " + this.getRn() + " )");
        }
    }

    public String trigonometric() {
        double source_ = abs(this);
        return (source_ + " * (cos(" + Math.acos(this.getRn() / source_) + ") + i * sin(" +
                Math.asin(this.getIu() / source_) + "))");
    }
}
