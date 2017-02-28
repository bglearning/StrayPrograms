import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2,2,1);

        double[][] inputs = new double[][]{
            { 0.0d, 0.0d},
            { 0.0d, 1.0d},
            { 1.0d, 0.0d},
            { 1.0d, 1.0d}
        };

        double[][] outputs = new double[][]{{0.0d}, {1.0d}, {1.0d}, {0.0d}};

        nn.train(inputs, outputs, 50000);

        for (int j = 0; j < 4; j++) {
            System.out.println("Input: " + Arrays.toString(inputs[j]));
            System.out.println("Output: " + Arrays.toString(nn.evaluate(inputs[j])));
        }

    }
}

