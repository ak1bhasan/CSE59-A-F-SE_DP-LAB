interface MessageSender {
    
    void sendMessage(String message);
}


class EmailSender implements MessageSender {

    public void sendMessage(String message) {
        
        System.out.println("Sending email: " + message);
    }
}


class SMSSender implements MessageSender {

    public void sendMessage(String message) {
        
        System.out.println("Sending SMS: " + message);
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


public class Main {

    public static void main(String[] args) {

        MessageSender email = new EmailSender();
        
        NotificationService emailNotification = new NotificationService(email);

        emailNotification.alertUser("Your order has been shipped.");


        MessageSender sms = new SMSSender();
        
        NotificationService smsNotification = new NotificationService(sms);

        smsNotification.alertUser("Your OTP is 1234.");
    }
}
