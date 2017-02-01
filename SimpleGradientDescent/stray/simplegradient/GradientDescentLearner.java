package stray.simplegradient;

import java.util.List;
import java.lang.Math;

public class GradientDescentLearner {

    private double b;
    private double m;
    private List<Point> points;

    public GradientDescentLearner(List<Point> points, double b, double m) {
        this.points = points;
        this.b = b;
        this.m = m;
    }

    public double calculateError() {
        double sumOfSquaredError = 0.0d;
        for (int i = 0; i < points.size(); i++) {
            double predictedY = m * points.get(i).getX() + b;
            double error = points.get(i).getY() - predictedY;
            sumOfSquaredError += error * error;
        }
        return sumOfSquaredError / points.size();
    }

    private void updateParameters(double learningRate) {
        double bGradient = 0.0d;
        double mGradient = 0.0d;

        for (int i = 0; i < points.size(); i++) {
            bGradient += points.get(i).getY() - (m * points.get(i).getX() + b);
            mGradient += -points.get(i).getX() * (points.get(i).getY() - (m * points.get(i).getX() + b));
        }
        bGradient *= 2.0d / points.size();
        mGradient *= 2.0d / points.size();

        b += learningRate * -bGradient;
        m += learningRate * -mGradient;
    }

    public void train(int numOfIterations, double learningRate) {
        for (int i = 0; i < numOfIterations; i++) {
            updateParameters(learningRate);
        }
    }

    public double guess(double x) {
        return m * x + b;
    }

}
