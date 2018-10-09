public class App {
    public static void main(String[] args) {
        double[][] mat = new double[][]{{1.0d, 4.0d, -3.0d}, {-2.0d, 8.0d, 5.0d}, {3.0d, 4.0d, 7.0d}};

        GaussianElim gaussianElim = new GaussianElim();

        printMatrix(mat);

        gaussianElim.convertToRowEchelon(mat, mat.length, mat[0].length);

        printMatrix(mat);


    }

    public static void printMatrix(double[][] mat) {
        System.out.println();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
