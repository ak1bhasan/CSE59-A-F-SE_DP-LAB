interface MessageSender {
    void sendMessage(String message);
}

class EmailSender implements MessageSender {
    public void sendMessage(String message) {
        System.out.println("Sending email : " + message);
    }
}

class NotificationService {
    private MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }

    public void alertUser(String msg) {
        sender.sendMessage(msg);
    }
}

public class Main2 {
    public static void main(String[] args) {
        MessageSender emailSender = new EmailSender();
        NotificationService service = new NotificationService(emailSender);

        System.out.println("Notification Service");
        service.alertUser("Hello! Your DIP refactoring is complete.");
    }
}
