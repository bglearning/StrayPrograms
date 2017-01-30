import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        SimpleNN neuralNetwork = new SimpleNN(3);

        // The output only depends on the first column.
        double[][] trainingSetInputs = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}, {0, 1, 1}};
        double[] trainingSetOutputs = {0, 1, 1, 0};

        System.out.println(Arrays.toString(neuralNetwork.getWeights()));

        neuralNetwork.train(trainingSetInputs, trainingSetOutputs, 10000);

        System.out.println(Arrays.toString(neuralNetwork.getWeights()));

        // Expected Output: 1
        System.out.println(neuralNetwork.guess(new double[]{1, 0, 0}));
        System.out.println(neuralNetwork.guess(new double[]{1, 1, 0}));
        
        // Expected Output: 0
        System.out.println(neuralNetwork.guess(new double[]{0, 0, 0}));
        System.out.println(neuralNetwork.guess(new double[]{0, 1, 0}));
    }

}
