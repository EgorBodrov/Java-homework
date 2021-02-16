package com.company;

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
    }

    public static ComplexMatrix sum(ComplexMatrix first, ComplexMatrix second) throws DifferentShape {
        if (first.getRows() == second.getRows() && first.getCols() == second.getCols()) {
            ComplexMatrix result = new ComplexMatrix(first.getRows(), second.getCols());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getCols(); j++) {
                    result.matrix[i][j] = ComplexNumber.sum(first.getValue(i, j), second.getValue(i, j));
                }
            }
            return result;
        }
        else {
            throw new DifferentShape("Different shape of matrixes");
        }
    }

    public static ComplexMatrix sub(ComplexMatrix first, ComplexMatrix second) throws DifferentShape {
        if (first.getRows() == second.getRows() && first.getCols() == second.getCols()) {
            ComplexMatrix result = new ComplexMatrix(first.getRows(), second.getCols());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getCols(); j++) {
                    result.matrix[i][j] = ComplexNumber.sub(first.getValue(i, j), second.getValue(i, j));
                }
            }
            return result;
        }
        else {
            throw new DifferentShape("Different shape of matrixes");
        }
    }

    public static ComplexMatrix mul(ComplexMatrix first, ComplexMatrix second) throws DifferentShape {
        if (first.getCols() == second.getRows()) {
            ComplexMatrix result = new ComplexMatrix(first.getRows(), second.getCols());
            ComplexNumber tmp;
            for (int i = 0; i < first.getRows(); i++) {
                for (int j = 0; j < second.getCols(); j++) {
                    tmp = new ComplexNumber(0);
                    for (int k = 0; k < second.getRows(); k++) {
                        tmp = ComplexNumber.sum(tmp,
                                ComplexNumber.mul(first.getValue(i, k), second.getValue(k, j)));
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

    private static void split(ComplexNumber[][] matrix, ComplexNumber[][] tmp,
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

    public static ComplexNumber determinant(ComplexMatrix source) throws DifferentShape {
        if (source.getRows() == source.getCols()) {
            if (source.getRows() == 1) {
                return source.getValue(0, 0);
            }

            ComplexNumber result = new ComplexNumber(0);
            ComplexNumber sign = new ComplexNumber(1);
            int n = source.getRows();;

            for (int i = 0; i < n; i++) {
                ComplexNumber[][] tmp = new ComplexNumber[n - 1][n - 1];
                split(source.matrix, tmp, 0, i, n);
                ComplexNumber q = ComplexNumber.mul(sign, source.getValue(0, i));
                result = ComplexNumber.sum(result, ComplexNumber.mul(q, determinant(new ComplexMatrix(tmp))));
                sign = ComplexNumber.sub(sign, ComplexNumber.mul(new ComplexNumber(2), sign));
            }
            return result;
        }
        else {
            throw new DifferentShape("Matrix should be square");
        }
    }

    public static ComplexMatrix transpose(ComplexMatrix source) {
        ComplexMatrix result = new ComplexMatrix(source.getCols(), source.getRows());
        for (int i = 0; i < source.getRows(); i++) {
            for (int j = 0; j < source.getCols(); j++) {
                result.matrix[j][i] = new ComplexNumber(source.getValue(i, j).getRn(),
                        source.getValue(i, j).getIu());
            }
        }
        return result;
    }

    public static void main(String[] args) throws Matrix.DifferentShape {
        try {
            ComplexNumber[][] a = new ComplexNumber[2][2];
            a[0][0] = new ComplexNumber(3, 2);
            a[0][1] = new ComplexNumber(1, 1);
            a[1][0] = new ComplexNumber(3, 0);
            a[1][1] = new ComplexNumber(4, 2);

            ComplexMatrix b = new ComplexMatrix(a);
            b.printMatrix();
            ComplexMatrix c = ComplexMatrix.transpose(b);
            c.printMatrix();
            ComplexNumber x = ComplexMatrix.determinant(c);
            ComplexNumber y = ComplexMatrix.determinant(b);
            System.out.println("\n" + x.algebraic() + "  " + y.algebraic());

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
