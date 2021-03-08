package com.company;

public class Main {

    public static void main(String[] args) throws Matrix.DifferentShape {
        try {
            ComplexNumber[][] a = new ComplexNumber[2][2];
            a[0][0] = new ComplexNumber(3, 2);
            a[0][1] = new ComplexNumber(1, 1);
            a[1][0] = new ComplexNumber(3, 0);
            a[1][1] = new ComplexNumber(4, 2);

            ComplexMatrix b = new ComplexMatrix(a);
            b.printMatrix();
            ComplexNumber x = b.determinant();
            System.out.println(x.algebraic());
            ComplexMatrix y = b.transpose();
            y.printMatrix();
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
}
