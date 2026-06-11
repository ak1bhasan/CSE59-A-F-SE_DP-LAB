interface clickable {
    void onClick();
}

class Button implements Clickable {
    @override
    public void onClick() {
        System.out.println("Button was clicked!");
    }
}

public class Main {
    public static void main(String[] args) {
        Button button = new Button()
    }
}



