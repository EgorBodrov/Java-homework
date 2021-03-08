package com.company;
import java.util.Scanner;

public class ComplexMatrix {
    private final int rows;
    private final int cols;
    private ComplexNumber[][] matrix;

    public ComplexMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new ComplexNumber[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = new ComplexNumber(0);
            }
        }
    }

    public ComplexMatrix(int[][] source) {
        this.rows = source.length;
        this.cols = source[0].length;
        this.matrix = new ComplexNumber[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                matrix[i][j] = new ComplexNumber(source[i][j]);
            }
        }
    }

    public ComplexMatrix(ComplexNumber[][] source) {
        this.rows = source.length;
        this.cols = source[0].length;
        this.matrix = new ComplexNumber[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            System.arraycopy(source[i], 0, matrix[i], 0, this.cols);
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public ComplexNumber getValue(int i, int j) {
        return this.matrix[i][j];
    }

    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                System.out.print(this.matrix[i][j].algebraic() + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillMatrix() {
        System.out.println("Fill matrix with " + rows + " rows and " + cols + " columns");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = new ComplexNumber(in.nextInt(), in.nextInt());
            }
        }
    }

    public void sum(ComplexMatrix source) throws DifferentShape {
        if (this.getRows() == source.getRows() && this.getCols() == source.getCols()) {
            for (int i = 0; i < source.getRows(); i++) {
                for (int j = 0; j < source.getCols(); j++) {
                    this.matrix[i][j].sum(source.getValue(i, j));
                }
            }
        }
        else {
            throw new DifferentShape("Different shape of matrixes");
        }
    }

    public void sub(ComplexMatrix source) throws DifferentShape {
        if (this.getRows() == source.getRows() && this.getCols() == source.getCols()) {
            for (int i = 0; i < source.getRows(); i++) {
                for (int j = 0; j < source.getCols(); j++) {
                    source.matrix[i][j].sub(source.getValue(i, j));
                }
            }
        }
        else {
            throw new DifferentShape("Different shape of matrixes");
        }
    }

    public void mul(ComplexMatrix source) throws DifferentShape {
        if (this.getCols() == source.getRows()) {
            ComplexNumber tmp;
            for (int i = 0; i < source.getRows(); i++) {
                for (int j = 0; j < source.getCols(); j++) {
                    tmp = new ComplexNumber(0);
                    for (int k = 0; k < source.getRows(); k++) {
                        ComplexNumber tmp2 = new ComplexNumber(this.getValue(i, k).getRn(), this.getValue(i, k).getIu());
                        tmp2.mul(source.getValue(k, j));
                        tmp.sum(tmp2);
                    }
                    this.matrix[i][j] = tmp;
                }
            }
        }
        else {
            throw new DifferentShape("Different shape of matrix");
        }
    }

    private void split(ComplexNumber[][] matrix, ComplexNumber[][] tmp,
                              int row, int col, int shape) {
        int i = 0;
        int j = 0;
        for (int x = 0; x < shape; x++) {
            for (int y = 0; y < shape; y++) {
                if (x != row && y != col) {
                    tmp[i][j] = matrix[x][y];
                    j++;
                    if (j == shape - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public ComplexNumber determinant() throws DifferentShape {
        if (this.getRows() == this.getCols()) {
            if (this.getRows() == 1) {
                return this.getValue(0, 0);
            }

            ComplexNumber result = new ComplexNumber(0);
            ComplexNumber sign = new ComplexNumber(1);
            int n = this.getRows();;

            for (int i = 0; i < n; i++) {
                ComplexNumber[][] tmp = new ComplexNumber[n - 1][n - 1];
                split(this.matrix, tmp, 0, i, n);
                ComplexNumber q = new ComplexNumber(1);
                q.mul(this.getValue(0, i));
                q.mul(new ComplexMatrix(tmp).determinant());
                result.sum(q);
                sign.mul(new ComplexNumber(-1));
            }
            return result;
        }
        else {
            throw new DifferentShape("Matrix should be square");
        }
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.getCols(), this.getRows());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                result.matrix[j][i] = new ComplexNumber(this.getValue(i, j).getRn(),
                        this.getValue(i, j).getIu());
            }
        }
        return result;
    }

    public static class DifferentShape extends Exception {
        public DifferentShape(String error) {
            super(error);
        }
    }
}
