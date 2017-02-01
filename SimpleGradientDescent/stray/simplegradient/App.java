package stray.simplegradient;

import java.util.List;

public class App {
    public static void main(String[] args) {

        PointCSVReader reader = new PointCSVReader("data/points.csv");

        GradientDescentLearner learner = new GradientDescentLearner(
                reader.readPoints(), 0.0d, 0.0d); 

        System.out.println(String.format("Error Before training: %.6f", learner.calculateError()));
        learner.train(1000, 0.0001);
        System.out.println(String.format("Error After training: %.6f", learner.calculateError()));

        System.out.println(String.format("Learner Guess for 47.0 => %.6f", learner.guess(47.0d)));
    }
}
