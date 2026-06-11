class Printer {
    
    void printData(String text) {
        System.out.println(text);
    }
    void printData(int number) {
        System.out.println(number);
    }
}

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        
        printer.printData("Hello, World!");
        printer.printData(123);
    }
}
