import java.util.Random;
import java.lang.Math;

public class SimpleNN {

    private double[] weights;

    public SimpleNN(int lengthOfInputVector) {
        Random randomNumGenerator = new Random(1);

        weights = new double[lengthOfInputVector];
        for (int i = 0; i < lengthOfInputVector; i++) {
            weights[i] = randomNumGenerator.nextDouble();
        }
    }

    private double sigmoid(double x) {
        return (1.0d / (1 + Math.exp(-x)));
    }

    private double sigmoidDerivative(double x) {
        return x * (1 - x);
    }

    public void train(double[][] inputs, double[] outputs, int numOfIterations) {
        if (inputs.length != outputs.length)
            throw new IllegalArgumentException(String.format("Number of inputs: %d and number of outputs: %d mismatch", 
                                                             inputs.length, outputs.length));

        if (weights.length != inputs[0].length) 
            throw new IllegalArgumentException(String.format("Input vector length %d doesn't match the length specified during construction", 
                                                             inputs[0].length));

        for (int i = 0; i < numOfIterations; i++) {
            for (int j = 0; j < inputs.length; j++) {
                double predictedOutput = guess(inputs[j]);
                double error = outputs[j] - predictedOutput;
                double errorDerivative = sigmoidDerivative(predictedOutput);
                for (int k = 0; k < weights.length; k++) {
                    weights[k] += error * inputs[j][k] * errorDerivative;
                }
            }
        }
    }

    public double guess(double[] input) {
        if (input.length != weights.length) 
            throw new IllegalArgumentException(String.format("Input dimension %d doesn't match weight vector dimension %d", 
                                                             input.length, weights.length));
        double weightedSumOfInputs = 0.0d;

        for (int i = 0; i < input.length; i++) {
            weightedSumOfInputs += weights[i] * input[i];
        }

        return sigmoid(weightedSumOfInputs);
    }

    public double[] getWeights() {
        return weights;
    }

}
