package stray.simplegradient;

import java.util.List;

public abstract class PointReader {

    protected final String filename;

    public PointReader(String filename) {
        this.filename = filename;
    }

    public abstract List<Point> readPoints();


}
