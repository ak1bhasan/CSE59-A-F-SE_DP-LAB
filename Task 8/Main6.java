import java.util.LinkedList;
import java.util.Queue;

class PrintSpooler {
    private static volatile PrintSpooler instance;
    private Queue<String> printQueue;

    private PrintSpooler() {
        printQueue = new LinkedList<>();
    }

    public static PrintSpooler getInstance() {
        if (instance == null) {
            synchronized (PrintSpooler.class) {
                if (instance == null) {
                    instance = new PrintSpooler();
                }
            }
        }
        return instance;
    }

    public void addJob(String document) {
        printQueue.add(document);
        System.out.println("Added to global queue: " + document);
    }

    public void processJobs() {
        System.out.println("Processing Unified Queue");
        while(!printQueue.isEmpty()) {
            System.out.println("Printing : " + printQueue.poll());
        }
    }

}


public class Ex6 {
    public static void main(String[] args) {
        PrintSpooler.getInstance().addJob("Lab_Manual.pdf");

        PrintSpooler.getInstance().addJob("Grades.xlsx");

        PrintSpooler.getInstance().processJobs();
    }
}
