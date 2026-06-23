interface Shape {
    double calculateArea();
}

class Rectangle implements Shape {
    private double length;
    private double width;
    
    public Rectangle( double length, double width ) {
        this.length = length;
        this.width = width;
    }
    
    // @Override
    public double calculateArea() {
        return length * width;
    }
}

class Circle implements Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    // @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
}

class Triangle implements Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    // @Overrride
    public double calculateArea() {
        return 0.5 * base * height;
    }
    
}

class AreaCalculator{
    public double calculateTotalArea(Shape[] shapes) {
        double totalArea = 0;
        
        for(Shape shape : shapes) {
            totalArea += shape.calculateArea();
        }
        return totalArea;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Rectangle(10, 5),
            new Circle(7),
            new Triangle(6, 4)
        };
        
        AreaCalculator calculator = new AreaCalculator();
        
        System.out.println(
            "Total Area " + calculator.calculateTotalArea(shapes)
            );
    }
}




