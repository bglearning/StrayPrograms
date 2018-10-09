import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        double[][] mat = new double[][]{{0.0d, 1.0d}, {2.0d, 1.0d}};

        LUD lud = new LUD(mat);

        lud.decompose();

        System.out.println(Arrays.toString(lud.solve(new double[]{1.0d, -1.0d})));

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
