class User{
    private String username;
    private String email;
    
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
}

class EmailValidator {
    public static boolean isValidEmail(String Email) {
        if( Email != null && Email.contains("@") ) {
            return true;
        }
        System.out.println("Invalid email format!");
        return false;
    }
}


class UserRepository {
    public void saveToDatabase( User user ) {
        if( EmailValidator.isValidEmail(user.getEmail()) ) {
            System.out.println("Connecting to Database...");
            System.out.println("Saving User " + user.getUsername() + " to the user table.");
        }
        else {
            System.out.println("Database saved failed: Invalid User data.");
        }
    }
} 


public class Main {
    public static void main(String[] args) {
        UserRepository repo = new UserRepository();

        System.out.println("Executing test 1 : ");
    User validUser = new User("Akib Hasan", "akibhasan011@gmail.com");
    repo.saveToDatabase(validUser);
    
    System.out.println("Executing test 2 : ");
    User invalidUser = new User("Navid", "navid121.com");
    repo.saveToDatabase(invalidUser);
    }
}
