import java.util.Random;
import java.lang.Math;

public class NeuralNetwork {

    private class Layer {
        int units;

        double[][] weights;

        double[] outputs; 

        double[] errors;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Num of Units: %d\n", units));
            for (int i = 0; i < weights.length; i++) {
                for (int j = 0; j < weights[i].length; j++) {
                    sb.append(weights[i][j] + " ");
                }
                sb.append("\n");
            }

            return sb.toString();
        }
    }

    private Layer[] layers;

    private double learningRate = 0.1d;

    public NeuralNetwork(int... numOfUnits) {

        layers = new Layer[numOfUnits.length];

        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer();
        }
            
        Random randomNumGenerator = new Random(1);

        layers[0].units = numOfUnits[0];
        layers[0].weights = new double[0][0];
        layers[0].outputs = new double[numOfUnits[0]];
        layers[0].errors = new double[numOfUnits[0]];

        for (int i = 1; i < layers.length; i++) {
            layers[i].units = numOfUnits[i];
            layers[i].weights = new double[numOfUnits[i]][numOfUnits[i - 1] + 1];
            layers[i].outputs = new double[numOfUnits[i]];
            layers[i].errors = new double[numOfUnits[i]];

            for (int j = 0; j < layers[i].weights.length; j++) {
                for (int k = 0; k < layers[i].weights[j].length; k++) {
                    layers[i].weights[j][k] = randomNumGenerator.nextDouble();
                }
            }
        }

    }

    private void setInputs(double[] inputs) {
        System.arraycopy(inputs, 0, layers[0].outputs, 0, inputs.length);
    }

    private void forwardPropagate() {
        for (int i = 0; i < layers.length - 1; i++) {
            propagate(layers[i], layers[i + 1]);
        }
    }

    private void propagate(Layer lower, Layer upper) {
        for (int i = 0; i < upper.units; i++) {
            double sum = 0.0d;
            for (int j = 0; j < lower.units; j++) {
                sum += upper.weights[i][j] * lower.outputs[j];
            }

            //Bias
            sum += upper.weights[i][lower.units] * 1.0d;
            upper.outputs[i] = sigmoid(sum);
        }
    } 

    private void backPropagate(double[] targetOutputs) {
        for (int i = 0; i < layers[layers.length - 1].units; i++) {
            layers[layers.length - 1].errors[i] = targetOutputs[i] - layers[layers.length - 1].outputs[i];
        }

        for (int i = layers.length - 1; i > 0; i--) {
            backPropagate(layers[i], layers[i - 1]);
        }
    }

    private void backPropagate(Layer upper, Layer lower) {
        double[] delta = new double[upper.errors.length];
        for (int u = 0; u < delta.length; u++) {
            delta[u] = upper.errors[u] * sigmoidDerivative(upper.outputs[u]);
        }

        for (int l = 0; l < lower.units; l++) {
            lower.errors[l] = 0.0d; 
            for (int u = 0; u < upper.units; u++) {
                lower.errors[l] += delta[u] * upper.weights[u][l];
            }
        }

        for (int u = 0; u < upper.units; u++) {
            for (int l = 0; l < lower.units; l++) {
                upper.weights[u][l] += delta[u] * lower.outputs[l];
            }
            upper.weights[u][lower.units] += delta[u] * 1.0d;
        }
    }

    public void train(double[][] inputs, double[][] outputs, double iterations) {
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < inputs.length; j++) {
                setInputs(inputs[j]);
                forwardPropagate();
                backPropagate(outputs[j]);
            }
        }
    }

    public double[] evaluate(double[] inputs) {
        setInputs(inputs);
        forwardPropagate();
        return layers[layers.length - 1].outputs;
    }


    private static double sigmoid(double x) {
        return (1.0d / (1 + Math.exp(-x)));
    }

    private static double sigmoidDerivative(double x) {
        return x * (1 - x);
    }

    public void printNetwork() {
        for (int i = 0; i < layers.length; i++) {
            System.out.println(layers[i]);
        }
    }

}
