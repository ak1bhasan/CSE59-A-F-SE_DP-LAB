
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

#### YouDo1: Refactor for ISP

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

#### YouDo2: Refactor for DIP

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





# SOLID Repeated:

### Task 1: Single Responsibility Principle (SRP)

**Domain:** Healthcare & Vaccination Platform
**The Problem:** The `VaccineRegistration` class is juggling too many jobs. It holds data, validates IDs, and handles notifications.

```java
public class VaccineRegistration {
    private String patientName;
    private String nationalId;

    public VaccineRegistration(String patientName, String nationalId) {
        this.patientName = patientName;
        this.nationalId = nationalId;
    }

    public boolean validateId() {
        // Checks if ID is exactly 10 or 17 digits
        return nationalId.length() == 10 || nationalId.length() == 17;
    }

    public void sendSmsConfirmation() {
        if (validateId()) {
            System.out.println("Sending SMS to " + patientName + ": Registration successful.");
        }
    }
}

```

* **YouDO3:** Split this into three distinct classes: one for patient data (`Patient`), one for validation logic (`IdValidator`), and one for notifications (`SmsService`).

---

### Task 2: Open/Closed Principle (OCP)

**Domain:** AI Model Optimization & Edge Computing
**The Problem:** The `InferenceEngine` requires modification every time a new machine learning model architecture is added.

```java
public class InferenceEngine {
    public void quantizeModel(String modelType) {
        if (modelType.equals("ResNet18")) {
            System.out.println("Applying int8 quantization for ResNet-18.");
        } else if (modelType.equals("MobileNet")) {
            System.out.println("Applying dynamic quantization for MobileNet.");
        } 
        // We have to keep modifying this file for new models!
    }
}

```

* **YouDO4:** Refactor this by creating an `OptimizableModel` interface with a `quantize()` method. Then, create separate `ResNet18` and `MobileNet` classes that implement this interface, allowing the engine to accept any model without changing its core logic.

---

### Task 3: Liskov Substitution Principle (LSP)

**Domain:** University Academic System
**The Problem:** An undergraduate student is being forced to inherit a behavior they don't support, causing the application to crash when polymorphic iteration occurs.

```java
public class UniversityMember {
    public void attendLecture() {
        System.out.println("Attending a lecture.");
    }

    public void conductLabResearch() {
        System.out.println("Publishing papers and running experiments.");
    }
}

public class UndergraduateStudent extends UniversityMember {
    @Override
    public void conductLabResearch() {
        throw new UnsupportedOperationException("Undergrads do not conduct formal lab research yet.");
    }
}

```

* **YouDo5:** Restructure the hierarchy. Create a generic `UniversityMember`, but extract `conductLabResearch()` into a separate `Researcher` interface so that only Graduate Research Assistants (GRAs) or Professors implement it.

---

### Task 4: Interface Segregation Principle (ISP)

**Domain:** Full-Stack Backend Infrastructure
**The Problem:** A monolithic interface forces clients to implement methods they have absolutely no use for.

```java
public interface IBackendInfrastructure {
    void handleHttpRequest();
    void executeSqlStatement();
    void restartDockerContainer();
}

public class WebController implements IBackendInfrastructure {
    public void handleHttpRequest() {
        System.out.println("Routing traffic to endpoint.");
    }

    public void executeSqlStatement() {
        // Dummy implementation - Controller shouldn't touch the DB directly!
    }

    public void restartDockerContainer() {
        // Dummy implementation - Controller has no Docker access!
    }
}

```

* **YouDo6:** Break `IBackendInfrastructure` down into smaller, highly cohesive interfaces like `HttpService`, `DatabaseManager`, and `ContainerOps`. Have the `WebController` only implement what it actually needs.

---

### Task 5: Dependency Inversion Principle (DIP)

**Domain:** Disaster Response IoT Network
**The Problem:** A high-level emergency alert system is tightly coupled to a low-level, concrete networking class.

```java
public class BluetoothMeshNetwork {
    public void broadcastMessage(String msg) {
        System.out.println("Broadcasting via Bluetooth Mesh: " + msg);
    }
}

public class DisasterAlertSystem {
    private BluetoothMeshNetwork network; // Tightly coupled!

    public DisasterAlertSystem() {
        this.network = new BluetoothMeshNetwork();
    }

    public void triggerAlert(String alertData) {
        network.broadcastMessage(alertData);
    }
}

```

* **YouDo7:** Create an `INetworkProtocol` interface. Make `BluetoothMeshNetwork` implement it, and inject the interface into `DisasterAlertSystem` via its constructor. This way, the system could easily switch to a `WiFiDirectNetwork` without breaking.

---

