package stray.simplegradient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class PointCSVReader extends PointReader {

    public PointCSVReader(String filename) {
        super(filename);
    }

    @Override
    public List<Point> readPoints() {
        String line = "";
        String separator = ",";

        List<Point> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            while ((line = br.readLine()) != null) {
                String[] pointVals = line.split(separator);
                points.add(new Point(Double.parseDouble(pointVals[0]), Double.parseDouble(pointVals[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }
}
