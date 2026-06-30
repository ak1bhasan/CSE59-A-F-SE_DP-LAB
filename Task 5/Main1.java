interface Printer {
    void printDocument();
}

interface Scanner {
    void scanDocument();
}

interface FaxMachine {
    void faxDocument();
}

class BasicPrinter implements Printer {
    public void printDocument() {
        System.out.println("Printing...");
    }
}

class MultiFunctionCopier implements Printer, Scanner, FaxMachine {
    public void printDocument() {
        System.out.println("Printing...");
    }

    public void scanDocument() {
        System.out.println("Scanning...");
    }

    public void faxDocument() {
        System.out.println("Faxing...");
    }
}

public class Main1 {
    public static void main(String[] args) {
        Printer basicPrinter = new BasicPrinter();
        System.out.println("Basic Printer");
        basicPrinter.printDocument();

        MultiFunctionCopier mfc = new MultiFunctionCopier();
        System.out.println("Multi-Function Copier");
        mfc.printDocument();
        mfc.scanDocument();
        mfc.faxDocument();

    }
}
