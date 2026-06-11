class Countdown {
    
    void start() {
        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
        }
        System.out.println("Go!");
    }
}

public class Main {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();
        countdown.start();
    }
}
