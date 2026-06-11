class NumberChecker {
    void check (int num) {
        System.out.println(num % 2 == 0 && num > 10);
    }
}

public class Main {
    public static void main(String[] args) {
        NumberChecker checker = new NumberChecker();
        checker.check(14);
    }
}
