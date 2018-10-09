
public class LUD {

    private double[][] mat; 

    public LUD(double[][] mat) {
        this.mat = mat;
    }

    public double[][] getMat() {
        return mat;
    }

    public double[][] getL() {
        double[][] lowerMat = new double[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < i; j++) {
                lowerMat[i][j] = mat[i][j]; 
            }
            lowerMat[i][i] = 1.0d;
        }

        return lowerMat;
    }

    public double[][] getU() {
        double[][] upperMat = new double[mat.length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = i + 1; j < mat.length; j++) {
                upperMat[i][j] = mat[i][j]; 
            }
            upperMat[i][i] = 1.0d;
        }

        return upperMat;
    }

    public double[][] getDU() {
        double[][] upperMat = new double[mat.length][mat.length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = i; j < mat.length; j++) {
                upperMat[i][j] = mat[i][j]; 
            }
        }

        return upperMat;
    }

    public double[][] getD() {
        double[][] diagMat = new double[mat.length][mat.length];

        for (int i = 0; i < mat.length; i++ ){
            diagMat[i][i] = mat[i][i];
        }

        return diagMat;
    }

    public double getDeterminant() {
        double det = 1.0d;
        for (int i = 0; i < mat.length; i++) {
            det *= mat[i][i];
        }
        return det;
    }

    public void decompose() {
        int s = mat.length; 

        for (int k = 0; k < s; k++) {
            // Find the kth pivot:
            int maxJ = maxPivot(k, s);

            if (Double.compare(mat[maxJ][k], 0.0d) == 0) 
                throw new IllegalArgumentException("Matrix is singular");
            
            // Swap rows if necessary
            if (maxJ != k) {
                swapRows(k, maxJ);
            }

            for (int j = k + 1; j < s; j++) {
                double div = mat[j][k] / mat[k][k];
                for (int i = k; i < s; i++) {
                    mat[j][i] -= mat[k][i] * div;
                }
                mat[j][k] = div;
            }
        }
    }

    public double[] solve(double[] output) {
        return backSubstitution(forwardSubstitution(output));
    }

    private double[] forwardSubstitution(double[] output) {

        if (output.length != mat.length) {
            throw new IllegalArgumentException(String.format(
                        "Size Mismatch: matSize: %d, vecSize: %d", mat.length, output.length));
        }

        double[][] lMat = this.getL();
        double[] solution = new double[output.length];

        for (int row = 0; row < output.length; row++) {
            double val = output[row];
            for (int i = 0; i < row; i++) {
                val -= lMat[row][i] * solution[i];
            }
            solution[row] = val / lMat[row][row];
        }

        return solution;

    }

    private double[] backSubstitution(double[] output) {

        if (output.length != mat.length) {
            throw new IllegalArgumentException(String.format(
                        "Size Mismatch: matSize: %d, vecSize: %d", mat.length, output.length));
        }

        double[][] duMat = this.getDU();
        double[] solution = new double[output.length];

        for (int row = output.length - 1; row >= 0; row--) {
            double val = output[row];
            for (int i = output.length - 1; i > row; i--) {
                val -= duMat[row][i] * solution[i];
            }
            solution[row] = val / duMat[row][row];
        }

        return solution;

    }

    private int maxPivot(int k, int m) {

        int maxI = k;

        for (int i = k + 1; i < m; i++) {
            if (Double.compare(mat[maxI][k], mat[i][k]) < 0) {
                maxI = i;
            }
        }

        return maxI;
    }

    private void swapRows(int i, int j) {
        for (int k = 0; k < mat[i].length; k++) {
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }
}
