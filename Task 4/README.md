
# The Open/Closed Principle (O)

The Open/Closed Principle states that software entities should be **open for extension, but closed for modification**. This means you should be able to add new features to a system by writing *new* code, rather than altering *existing* code. This prevents the introduction of bugs into code that is already tested and working.

#### ❌ When O is Violated

Here, if we want to add a new customer type (like "Employee"), we have to open the `DiscountCalculator` class and modify the `calculateDiscount` method. This breaks the principle.

```java
public class DiscountCalculator {
    public double calculateDiscount(String customerType, double price) {
        if (customerType.equals("Regular")) {
            return price * 0.05; // 5% discount
        } else if (customerType.equals("VIP")) {
            return price * 0.20; // 20% discount
        }
        return 0; // No discount
    }
}

```

#### ✅ When O is Followed

By using an interface, the `DiscountCalculator` is now "closed" for modification. If we want to add an "Employee" discount, we simply create a new class `EmployeeDiscount` that implements `DiscountStrategy`. The existing calculator code never changes.

```java
// The Abstraction
public interface DiscountStrategy {
    double applyDiscount(double price);
}

// Extensions (New classes instead of modifying old ones)
public class RegularDiscount implements DiscountStrategy {
    public double applyDiscount(double price) { return price * 0.05; }
}

public class VIPDiscount implements DiscountStrategy {
    public double applyDiscount(double price) { return price * 0.20; }
}

// The closed calculator
public class DiscountCalculator {
    public double calculate(DiscountStrategy strategy, double price) {
        return strategy.applyDiscount(price);
    }
}

```

#### Student Task: Refactor for OCP

**The Goal for the Students:** Refactor this `AreaCalculator` so that adding a new shape (like a `Triangle`) doesn't require modifying the `calculateTotalArea` method.

**The Broken Code:**

```java
class Rectangle {
    public double length;
    public double width;
}

class Circle {
    public double radius;
}

public class AreaCalculator {
    public double calculateTotalArea(Object[] shapes) {
        double area = 0;
        for (Object shape : shapes) {
            if (shape instanceof Rectangle) {
                Rectangle r = (Rectangle) shape;
                area += r.length * r.width;
            } else if (shape instanceof Circle) {
                Circle c = (Circle) shape;
                area += Math.PI * (c.radius * c.radius);
            }
        }
        return area;
    }
}

```

*(Instructor Note: The solution should involve creating a `Shape` interface with a `calculateArea()` method. `Rectangle` and `Circle` should implement this interface, allowing `AreaCalculator` to simply call `shape.calculateArea()` in the loop without checking the type.)*

---

# The Liskov Substitution Principle (L)

LSP states that **subclasses should be substitutable for their base classes without altering the correctness of the program**. If a parent class guarantees certain behavior, a child class cannot suddenly refuse to perform that behavior or break the expected rules.

#### ❌ When L is Violated

Here, the `Ostrich` class inherits from `Bird`. However, because an ostrich cannot fly, it throws an exception. If a program expects a generic `Bird` and calls `fly()`, substituting an `Ostrich` will crash the system.

```java
public class Bird {
    public void fly() {
        System.out.println("Flapping wings and flying!");
    }
}

public class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches cannot fly!");
    }
}

```

#### ✅ When L is Followed

We must remodel the hierarchy so that no subclass promises a behavior it cannot fulfill.

```java
public class Bird {
    public void eat() {
        System.out.println("Pecking at food.");
    }
}

public class FlyingBird extends Bird {
    public void fly() {
        System.out.println("Flapping wings and flying!");
    }
}

public class Ostrich extends Bird {
    public void run() {
        System.out.println("Running really fast!");
    }
}

```

#### Student Task: Refactor for LSP

**The Goal for the Students:** Fix the class hierarchy. A `Contractor` is an employee, but they do not get company bonuses. Throwing an exception breaks LSP.

**The Broken Code:**

```java
public class Employee {
    protected String name;
    
    public Employee(String name) { this.name = name; }
    
    public void calculateBonus() {
        System.out.println("Calculating standard employee bonus...");
    }
}

public class Contractor extends Employee {
    public Contractor(String name) { super(name); }

    @Override
    public void calculateBonus() {
        // Contractors don't get bonuses, which breaks the parent's contract!
        throw new UnsupportedOperationException("Contractors do not receive bonuses.");
    }
}

```

*(Instructor Note: Students should introduce a broader base class or interface like `TeamMember` (which just holds the name), and then create specific interfaces/classes for `BonusEligible` employees versus `Contractors`.)*

---

# The Interface Segregation Principle (I)

ISP dictates that **clients should not be forced to depend on interfaces they do not use**. It is much better to have many small, highly specific interfaces than one massive, "fat" interface that tries to do everything.

#### ❌ When I is Violated

Here, we have a general `Worker` interface. A human can do all three, but a `RobotWorker` does not need to eat or sleep, forcing it to implement useless, empty methods.

```java
public interface Worker {
    void work();
    void eat();
    void sleep();
}

public class RobotWorker implements Worker {
    public void work() { System.out.println("Working at maximum efficiency."); }
    
    // Useless methods forced upon the class
    public void eat() { /* Robots don't eat */ }
    public void sleep() { /* Robots don't sleep */ }
}

```

#### ✅ When I is Followed

Break the large interface down into specific roles.

```java
public interface Workable { void work(); }
public interface Eatable { void eat(); }
public interface Sleepable { void sleep(); }

// A human implements all three
public class HumanWorker implements Workable, Eatable, Sleepable {
    public void work() { System.out.println("Working..."); }
    public void eat() { System.out.println("Eating lunch..."); }
    public void sleep() { System.out.println("Sleeping..."); }
}

// A robot only implements what it actually does
public class RobotWorker implements Workable {
    public void work() { System.out.println("Working at maximum efficiency."); }
}

```

#### Student Task: Refactor for ISP

**The Goal for the Students:** Refactor the `SmartDevice` interface so that the `BasicPrinter` isn't forced to implement scanning and faxing behaviors.

**The Broken Code:**

```java
public interface SmartDevice {
    void printDocument();
    void scanDocument();
    void faxDocument();
}

public class MultiFunctionCopier implements SmartDevice {
    public void printDocument() { System.out.println("Printing..."); }
    public void scanDocument() { System.out.println("Scanning..."); }
    public void faxDocument() { System.out.println("Faxing..."); }
}

public class BasicPrinter implements SmartDevice {
    public void printDocument() { System.out.println("Printing..."); }
    
    // Basic printers cannot scan or fax!
    public void scanDocument() { throw new UnsupportedOperationException(); }
    public void faxDocument() { throw new UnsupportedOperationException(); }
}

```

*(Instructor Note: The solution requires splitting `SmartDevice` into `Printer`, `Scanner`, and `FaxMachine` interfaces. `BasicPrinter` implements only `Printer`, while `MultiFunctionCopier` implements all three.)*

---

# The Dependency Inversion Principle (D)

DIP states that **high-level modules should not depend on low-level modules; both should depend on abstractions.** Furthermore, abstractions should not depend on details; details should depend on abstractions. Practically, this means avoiding the `new` keyword to hardcode dependencies inside a class.

#### ❌ When D is Violated

The high-level `Computer` class is tightly coupled to the low-level `StandardKeyboard` and `Monitor` classes. If we want to use a `MechanicalKeyboard` instead, we have to change the `Computer` class itself.

```java
public class StandardKeyboard {
    public void type() { System.out.println("Typing on standard keyboard."); }
}

public class Monitor {
    public void display() { System.out.println("Displaying pixels."); }
}

public class Computer {
    private StandardKeyboard keyboard;
    private Monitor monitor;

    public Computer() {
        // Tightly coupled! The Computer creates its own dependencies.
        this.keyboard = new StandardKeyboard();
        this.monitor = new Monitor();
    }
}

```

#### ✅ When D is Followed

We introduce interfaces (`Keyboard`, `Display`). The `Computer` class accepts these abstractions through its constructor (Dependency Injection). Now, we can plug any keyboard or display into the computer without changing the computer's code.

```java
// Abstractions
public interface Keyboard { void type(); }
public interface Display { void render(); }

// Low-level implementations
public class MechanicalKeyboard implements Keyboard {
    public void type() { System.out.println("Click clack typing."); }
}

public class LEDMonitor implements Display {
    public void render() { System.out.println("Rendering crisp LED image."); }
}

// High-level module
public class Computer {
    private Keyboard keyboard;
    private Display display;

    // Dependencies are injected, not created internally!
    public Computer(Keyboard keyboard, Display display) {
        this.keyboard = keyboard;
        this.display = display;
    }
}

```

#### Student Task: Refactor for DIP

**The Goal for the Students:** Refactor the `NotificationService` so it does not directly depend on the concretion of `EmailSender`. It should depend on a general abstraction so we can easily swap it to an `SMSSender` later.

**The Broken Code:**

```java
public class EmailSender {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class NotificationService {
    private EmailSender emailSender;

    public NotificationService() {
        // Direct dependency on a low-level module
        this.emailSender = new EmailSender();
    }

    public void alertUser(String msg) {
        emailSender.sendEmail(msg);
    }
}

```

*(Instructor Note: Students should create a `MessageSender` interface with a `sendMessage()` method. `EmailSender` implements this interface. Finally, `NotificationService` should accept a `MessageSender` via its constructor instead of instantiating `EmailSender` directly.)*
