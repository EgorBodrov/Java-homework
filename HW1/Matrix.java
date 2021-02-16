package com.company;
import java.util.Scanner;

public class Matrix {
    private final int rows;
    private final int cols;
    private int[][] matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = 0;
            }
        }
    }

    public Matrix(int[][] source) {
        this.rows = source.length;
        this.cols = source[0].length;
        this.matrix = new int[this.rows][this.cols];
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

    public int getValue(int i, int j) {
        return this.matrix[i][j];
    }

    public void fillMatrix() {
        System.out.println("Fill matrix with " + rows + " rows and " + cols + " columns");
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
    }

    public void printMatrix() {
        System.out.println();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                System.out.print(getValue(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static Matrix sum(Matrix first, Matrix second) throws DifferentShape {
        if (first.getRows() == second.getRows() && first.getCols() == second.getCols()) {
            Matrix result = new Matrix(first.getRows(), second.getCols());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getCols(); j++) {
                    result.matrix[i][j] = first.getValue(i, j) + second.getValue(i, j);
                }
            }
            return result;
        }
        else {
            throw new DifferentShape("Different shape of matrixes");
        }
    }

    public static Matrix sub(Matrix first, Matrix second) throws DifferentShape {
        if (first.getRows() == second.getRows() && first.getCols() == second.getCols()) {
            Matrix result = new Matrix(first.getRows(), second.getCols());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getCols(); j++) {
                    result.matrix[i][j] = first.getValue(i, j) - second.getValue(i, j);
                }
            }
            return result;
        }
        else {
            throw new DifferentShape("Different shape of matrixes");
        }
    }

    public static Matrix mul(Matrix first, Matrix second) throws DifferentShape {
        if (first.getCols() == second.getRows()) {
            Matrix result = new Matrix(first.getRows(), second.getCols());
            int tmp;
            for (int i = 0; i < first.getRows(); i++) {
                for (int j = 0; j < second.getCols(); j++) {
                    tmp = 0;
                    for (int k = 0; k < second.getRows(); k++) {
                        tmp += first.getValue(i, k) * second.getValue(k, j);
                    }
                    result.matrix[i][j] = tmp;
                }
            }
            return result;
        }
        else {
            throw new DifferentShape("Different shape of matrix");
        }
    }

    private static void split(int[][] matrix, int[][] tmp, int row, int col, int shape) {
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

    public static int determinant(Matrix source) throws DifferentShape {
        if (source.getRows() == source.getCols()) {
            if (source.getRows() == 1) {
                return source.getValue(0, 0);
            }

            int result = 0;
            int sign = 1;
            int n = source.getRows();;

            for (int i = 0; i < n; i++) {
                int[][] tmp = new int[n - 1][n - 1];
                split(source.matrix, tmp, 0, i, n);
                result += sign * source.matrix[0][i] * determinant(new Matrix(tmp));
                sign = -sign;
            }
            return result;
        }
        else {
            throw new DifferentShape("Matrix should be square");
        }
    }

    public static Matrix transpose(Matrix source) {
        Matrix result = new Matrix(source.getCols(), source.getRows());
        for (int i = 0; i < source.getRows(); i++) {
            for (int j = 0; j < source.getCols(); j++) {
                result.matrix[j][i] = source.getValue(i, j);
            }
        }
        return result;
    }

    public static void main(String[] args) throws DifferentShape {
        try {
            Matrix a = new Matrix(2, 3);
            Matrix b = new Matrix(new int[][]{{1, 0, 3}, {2, 4, 5}, {2, 2, 0}});
            System.out.println(Matrix.determinant(b));
            Matrix c = Matrix.transpose(b);
            System.out.println(Matrix.determinant(c));
            Matrix.sum(b, c).printMatrix();
        }
        catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    public static class DifferentShape extends Exception {
        public DifferentShape(String error) {
            super(error);
        }
    }
}
