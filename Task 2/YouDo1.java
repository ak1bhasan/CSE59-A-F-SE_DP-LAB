class ContestStats {
    int [] scores = {85, 90, 95};
    
    void printFirstScore() {
        System.out.println(scores[0]);
    }
}

public class Main {
    public static void main(String[] args) {
        ContestStats contest = new ContestStats();
        contest.printFirstScore();
    }
}
