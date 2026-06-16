
# SOLID Principles:

SOLID is an acronym that represents five core design principles of object-oriented programming (OOP). Introduced by Robert C. Martin (often called "Uncle Bob"), these principles serve as a guideline for designing software that is easy to maintain, understand, and scale. When codebases grow, poorly designed classes become rigid and fragile. The SOLID principles help developers avoid technical debt by keeping code modular and decoupled. Java, being a strictly object-oriented language, is an excellent medium for demonstrating these concepts.

---

### What Each Letter Means

* **S - Single Responsibility Principle (SRP):** A class should have one, and only one, reason to change. This means a class should only have one job or responsibility.
* **O - Open/Closed Principle (OCP):** Software entities (classes, modules, functions) should be open for extension but closed for modification. You should be able to add new functionality without rewriting existing code.
* **L - Liskov Substitution Principle (LSP):** Objects of a superclass should be replaceable with objects of its subclasses without breaking the application. A subclass must behave in a way that the parent class is expected to behave.
* **I - Interface Segregation Principle (ISP):** A client should never be forced to implement an interface that it doesn't use. It is better to have many small, specific interfaces rather than one large, general-purpose one.
* **D - Dependency Inversion Principle (DIP):** High-level modules should not depend on low-level modules. Both should depend on abstractions (e.g., interfaces). Furthermore, abstractions should not depend on details; details should depend on abstractions.

---

### Deep Dive: The Single Responsibility Principle (S)

The core idea of SRP is **cohesion**. If a class handles data logic, presentation logic, and database routing all at once, a change in how the database works could accidentally break the presentation logic.

#### ❌ When S is Violated

In this example, the `Report` class is doing too much. It holds the state, formats the data, and interacts with the file system. It has *three* distinct reasons to change.

```java
import java.io.FileWriter;
import java.io.IOException;

public class Report {
    private String title;
    private String content;

    public Report(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Responsibility 1: Data formatting
    public String formatToJson() {
        return String.format("{\"title\": \"%s\", \"content\": \"%s\"}", this.title, this.content);
    }

    // Responsibility 2: Data persistence (File I/O)
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(this.formatToJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

#### ✅ When S is Followed

To fix this, we break the responsibilities into distinct classes. Now, if we want to change how reports are saved (e.g., to a SQL database instead of a text file), we don't have to touch the `Report` data class or the `ReportFormatter`.

```java
import java.io.FileWriter;
import java.io.IOException;

// Responsibility 1: Holding the data (POJO)
public class Report {
    private String title;
    private String content;

    public Report(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
}

// Responsibility 2: Formatting the data
public class ReportFormatter {
    public static String formatToJson(Report report) {
        return String.format("{\"title\": \"%s\", \"content\": \"%s\"}", 
                             report.getTitle(), report.getContent());
    }
}

// Responsibility 3: Saving the data
public class ReportSaver {
    public static void saveToFile(String data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

---

### Student Task: Refactor for SRP

You can hand this snippet to your students. Explain that this `User` class is currently handling user properties, input validation, and database operations all in one place.

**The Goal for the Students:** Refactor this code into separate classes so that it strictly adheres to the Single Responsibility Principle.

**The Broken Code:**

```java
public class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public boolean isValidEmail() {
        // Checks if the email contains an '@' symbol
        if (this.email != null && this.email.contains("@")) {
            return true;
        }
        System.out.println("Invalid email format.");
        return false;
    }

    public void saveToDatabase() {
        if (this.isValidEmail()) {
            System.out.println("Connecting to database...");
            System.out.println("Saving user " + this.username + " to the users table.");
        }
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}

```

*(Instructor Note: The ideal student solution should separate this into a `User` class for the state, an `EmailValidator` class containing a static method for the validation logic, and a `UserRepository` or `DatabaseManager` class for the mock database print statements.)*
