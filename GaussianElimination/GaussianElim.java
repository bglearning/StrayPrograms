public class GaussianElim {

    public void convertToRowEchelon(double[][] mat, int m, int n) {

        for (int k = 0; k < m; k++) {
            // Find the kth pivot:
            int maxI = maxPivot(mat, k, m);

            if (Double.compare(mat[maxI][k], 0.0d) == 0) 
                throw new IllegalArgumentException("Matrix is singular");
            
            // Swap rows if necessary
            if (maxI != k) {
                swapRows(mat, k, maxI);
            }
            
            // For All Rows Below
            
            for (int i = k + 1; i < m; i++) {
                double mult = mat[i][k] / mat[k][k];

                for (int j = k + 1; j < n; j++) {
                    mat[i][j] -= mat[k][j] * mult;
                }

                mat[i][k] = 0.0d;
            }
            
        }

    }

    private int maxPivot(double[][] mat, int k, int m) {

        int maxI = k;

        for (int i = k + 1; i < m; i++) {
            if (Double.compare(mat[maxI][k], mat[i][k]) < 0) {
                maxI = i;
            }
        }

        return maxI;
    }

    private void swapRows(double[][] mat, int i, int j) {
        for (int k = 0; k < mat[i].length; k++) {
            double temp = mat[i][k];
            mat[i][k] = mat[j][k];
            mat[j][k] = temp;
        }
    }

}
