public class Rectangle {

    private double width;
    private double height;
    private final int id;
    private static int idGen = 0;

    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
        this.id = ++idGen;
    }

    public Rectangle(double width, double height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public int getId() { return id; }

    public void setWidth(double width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be > 0");
        }
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public String toString() {
        return "Rectangle [id=" + id + ", width=" + width + ", height=" + height + ", area=" + area() + ", perimeter=" + perimeter() + "]";
    }
}