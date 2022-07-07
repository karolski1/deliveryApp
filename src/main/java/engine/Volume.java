package engine;

public class Volume {
    double length;
    double width;
    double height;



    public Volume(double length, double width, double height) {
        this.height = height;
        this.length = length;
        this.width = width;
    }
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
