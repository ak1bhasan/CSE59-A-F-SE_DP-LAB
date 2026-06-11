# **The Starting Point: Your First Java Program**

Every Java application requires an entry point. This is where the Java Virtual Machine (JVM) begins executing your code.

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("System initialized. Ready for execution.");
    }
}

```

**Breaking down `public static void main(String[] args)`:**

* **`public`**: An access modifier. It makes the method universally visible so the JVM can find and execute it from outside the class.
* **`static`**: Indicates this method belongs to the `Main` class itself, not to a specific object instance. The JVM can run it without needing to instantiate the `Main` class first.
* **`void`**: The return type. It signifies that this method performs an action but does not hand back any value upon completion.
* **`main`**: The exact, case-sensitive name of the method the JVM searches for to start the program.
* **`String[] args`**: An array of strings that captures any command-line arguments passed to the application upon startup.

**How to run it using the terminal:**

1. Save the exact code above in a file named `Main.java`.
2. Open your terminal and navigate (`cd`) to the directory containing the file.
3. **Compile**: Run `javac Main.java`. This invokes the Java compiler to translate your human-readable code into bytecode, generating a `Main.class` file.
4. **Execute**: Run `java Main`. The JVM will read the bytecode and print the output to your console.

---

## **1. Data Type, Variable, Array**

Java relies on strict typing. Variables hold state, and arrays allow you to group multiple values of the same type.

```java
class AcademicProfile {
    // Primitive Data Types
    int semester = 8;
    double cgpa = 3.82;
    boolean isGraduating = true;
    char section = 'A';

    // Reference Data Type
    String major = "Computer Science and Engineering";

    // 1D and 2D Arrays
    int[] creditHours = {3, 1, 3, 3, 2}; 
    double[][] historicalGrades = {
        {4.0, 3.7, 4.0}, 
        {3.8, 3.9, 4.0}  
    };

    void displayDetails() {
        System.out.println("Major: " + major + " | Current CGPA: " + cgpa);
        System.out.println("First course credits: " + creditHours[0]);
        System.out.println("Year 2, Course 1 Grade: " + historicalGrades[1][0]);
    }
}

public class Main {
    public static void main(String[] args) {
        AcademicProfile profile = new AcademicProfile();
        profile.displayDetails();
    }
}

```

**YouDo 1 (Lab Task):** Create a `ContestStats` class. Declare an `int` array containing three scores (e.g., `{85, 90, 95}`). Write a `printFirstScore()` method that prints the value at the 0th index. In `Main`, create a `ContestStats` object and call the method.

---

## **2. Operator**

Operators manipulate data. This example covers arithmetic, relational, logical, and assignment operators.

```java
class HardwareAllocator {
    void evaluateResources(int availableRAM, int requiredRAM, int availableCores) {
        // Arithmetic & Assignment
        int remainingRAM = availableRAM - requiredRAM;
        remainingRAM += 2; 
        
        int memoryPerCore = availableRAM / availableCores;
        int unallocatedMemory = availableRAM % availableCores; 

        // Relational & Logical Operators
        boolean hasEnoughRAM = availableRAM >= requiredRAM;
        boolean isOptimal = hasEnoughRAM && (availableCores > 2);
        boolean requiresUpgrade = !hasEnoughRAM || (availableCores <= 1);

        System.out.println("Memory per core: " + memoryPerCore + "GB");
        System.out.println("Unallocated modulo: " + unallocatedMemory + "GB");
        System.out.println("System is optimal for deployment: " + isOptimal);
        System.out.println("Hardware upgrade required: " + requiresUpgrade);
    }
}

public class Main {
    public static void main(String[] args) {
        HardwareAllocator allocator = new HardwareAllocator();
        allocator.evaluateResources(16, 12, 4);
    }
}

```

**YouDo 2 (Lab Task):** Create a `NumberChecker` class with a method `check(int num)`. Inside the method, use the modulo operator (`%`) and a logical operator (`&&`) to print `true` if the number is **even** AND **greater than 10**, otherwise print `false`. Instantiate it in `Main` and test it with the number 14.

---

## **3. Control Statements**

Control flow dictates execution paths, utilizing `switch` statements alongside `for`, `while`, and `do-while` loops.

```java
class NetworkDiagnostics {
    
    void runDiagnosticTest(String protocol, int packetsToSend) {
        // Switch Statement (String support)
        System.out.println("--- Protocol Check ---");
        switch (protocol) {
            case "TCP":
                System.out.println("Establishing reliable connection...");
                break;
            case "UDP":
                System.out.println("Broadcasting datagrams...");
                break;
            default:
                System.out.println("Unknown protocol. Defaulting to ICMP (Ping).");
        }

        // For Loop: Exact number of iterations
        System.out.println("\n--- Sending Packets (For Loop) ---");
        for (int i = 1; i <= packetsToSend; i++) {
            System.out.println("Transmitting packet " + i + " of " + packetsToSend);
        }

        // While Loop: Condition checked before execution
        System.out.println("\n--- Awaiting Acknowledgement (While Loop) ---");
        int acksReceived = 0;
        while (acksReceived < packetsToSend) {
            acksReceived++;
            System.out.println("ACK " + acksReceived + " received.");
        }

        // Do-While Loop: Executes at least once regardless of condition
        System.out.println("\n--- Closing Connection (Do-While Loop) ---");
        int retryAttempts = 0;
        boolean connectionClosed = false;
        do {
            retryAttempts++;
            System.out.println("Attempting to close port... Attempt " + retryAttempts);
            if (retryAttempts == 2) {
                connectionClosed = true;
            }
        } while (!connectionClosed && retryAttempts < 3);
    }
}

public class Main {
    public static void main(String[] args) {
        NetworkDiagnostics diag = new NetworkDiagnostics();
        diag.runDiagnosticTest("TCP", 3);
    }
}

```

**YouDo 3 (Lab Task):** Create a `Countdown` class. Write a `start()` method that uses a `for` loop to print the numbers from 5 down to 1. After the loop finishes, print "Go!". Create the object in `Main` and call `start()`.

---

## **4. Class and Methods**

Classes encapsulate data (variables) and behavior (methods).

```java
class EdgeDevice {
    String deviceId;
    boolean isAwake;

    EdgeDevice(String id) {
        deviceId = id;
        isAwake = false; 
    }

    void togglePower() {
        isAwake = !isAwake;
        System.out.println("Device " + deviceId + " power state: " + (isAwake ? "ON" : "OFF"));
    }
}

class QuantizationModule {
    double[] applyScaling(double[] weights, double scaleFactor) {
        double[] quantizedWeights = new double[weights.length];
        for (int i = 0; i < weights.length; i++) {
            quantizedWeights[i] = weights[i] * scaleFactor;
        }
        return quantizedWeights;
    }
}

public class Main {
    public static void main(String[] args) {
        // Basic Object Iteration
        EdgeDevice sensorNode = new EdgeDevice("STM_Nano_01");
        sensorNode.togglePower();

        // Advanced Method Calls
        QuantizationModule qm = new QuantizationModule();
        double[] originalWeights = {0.85, -0.42, 0.91};
        double[] newWeights = qm.applyScaling(originalWeights, 100.0);
        System.out.println("First scaled weight: " + newWeights[0]);
    }
}

```

**YouDo 4 (Lab Task):** Create a `Sensor` class with a `String name` and an `int batteryLevel`. Add a `charge()` method that sets the `batteryLevel` to 100 and prints "Sensor fully charged". In `Main`, create a `Sensor` object, call `charge()`, and print its new battery level to verify.

---

## **5. Method Overloading**

Overloading allows a class to perform similar actions with different input types or quantities, improving API readability.

```java
class VisionProcessor {
    // Overload 1: Matrix filter
    void applyFilter(int[][] imageMatrix) {
        System.out.println("Applying grayscale filter to 2D image matrix.");
    }
    
    // Overload 2: RGB Pixel filter
    void applyFilter(int r, int g, int b) {
        int gray = (r + g + b) / 3;
        System.out.println("Applying grayscale to single RGB pixel. Result: " + gray);
    }
    
    // Overload 3: Matrix filter with explicit intensity
    void applyFilter(int[][] imageMatrix, double intensity) {
        System.out.println("Applying filter to matrix with intensity level: " + intensity);
    }
}

public class Main {
    public static void main(String[] args) {
        VisionProcessor vp = new VisionProcessor();
        int[][] dummyImage = {{255, 200}, {100, 50}};
        
        vp.applyFilter(dummyImage);
        vp.applyFilter(120, 150, 180);
        vp.applyFilter(dummyImage, 0.85);
    }
}

```

**YouDo 5 (Lab Task):** Create a `Printer` class. Write one `printData(String text)` method that prints the text. Write an overloaded `printData(int number)` method that prints the number. Instantiate `Printer` in `Main` and call both methods.

---

## **6. Inheritance**

Inheritance models "is-a" relationships, allowing derived classes to inherit fields and methods from base classes.

```java
class Hardware {
    void supplyPower() { 
        System.out.println("Power supplied to mainboard."); 
    }
}

class Microcontroller extends Hardware {
    void flashFirmware() { 
        System.out.println("Flashing C++ binaries via serial port."); 
    }
}

class STM32Board extends Microcontroller {
    void configurePins() { 
        System.out.println("Setting up GPIO pins for external sensors."); 
    }
}

public class Main {
    public static void main(String[] args) {
        // Multilevel Inheritance Test
        STM32Board myBoard = new STM32Board();
        myBoard.supplyPower();    // Inherited from Hardware
        myBoard.flashFirmware();  // Inherited from Microcontroller
        myBoard.configurePins();  // Native to STM32Board
    }
}

```

**YouDo 6 (Lab Task):** Create a base class `Applet` with a `launch()` method that prints "Applet launched". Create a derived class `GameApplet` that inherits `Applet` and adds a `play()` method that prints "Playing game". In `Main`, create a `GameApplet` object and call both `launch()` and `play()`.

---

## **7. Interface**

Interfaces define a contract. Any class implementing an interface must provide the specific behavior defined within it.

```java
interface Optimizable {
    void pruneModel(double sparsity);
    void convertToEngine();
}

class TensorRTDeployment implements Optimizable {
    String modelName;

    TensorRTDeployment(String name) {
        this.modelName = name;
    }

    @Override
    public void pruneModel(double sparsity) {
        System.out.println("Pruning " + modelName + " to achieve " + (sparsity * 100) + "% sparsity.");
    }

    @Override
    public void convertToEngine() {
        System.out.println("Serializing " + modelName + " into an optimized TensorRT engine file.");
    }
}

public class Main {
    public static void main(String[] args) {
        TensorRTDeployment deployer = new TensorRTDeployment("VisionModel_v1");
        deployer.pruneModel(0.35);
        deployer.convertToEngine();
    }
}

```

**YouDo 7 (Lab Task):** Create an interface `Clickable` with a single empty method `onClick()`. Implement this interface in a `Button` class, providing the logic to print "Button was clicked!". In `Main`, create a `Button` object and call `onClick()`.

---

## **FINAL: Course Management System Simulation**

This integrates the OOP concepts into a functional, interactive terminal application simulating university scheduling.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// --- 1. User Class ---
class User {
    private String name;
    private String role;

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
}

// --- 2. Course Class ---
class Course {
    private String courseCode;
    private String courseName;
    private User teacher;
    private List<User> enrolledStudents;

    public Course(String courseCode, String courseName, User teacher) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.teacher = teacher;
        this.enrolledStudents = new ArrayList<>();
    }

    public void addStudent(User student) {
        if (student.getRole().equalsIgnoreCase("Student")) {
            enrolledStudents.add(student);
        }
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public User getTeacher() { return teacher; }
    public List<User> getEnrolledStudents() { return enrolledStudents; }
}

// --- 3. Schedule Class ---
class Schedule {
    private List<Course> allCourses = new ArrayList<>();

    public void addCourse(Course course) {
        allCourses.add(course);
    }

    public void searchByTeacher(String teacherName) {
        System.out.println("\n>>> Querying schedule for Instructor: " + teacherName);
        boolean found = false;
        for (Course c : allCourses) {
            if (c.getTeacher().getName().equalsIgnoreCase(teacherName)) {
                System.out.println("  -> [" + c.getCourseCode() + "] " + c.getCourseName());
                found = true;
            }
        }
        if (!found) System.out.println("  [!] No courses found assigned to this instructor.");
    }

    public void searchByStudent(String studentName) {
        System.out.println("\n>>> Querying enrollments for Student: " + studentName);
        boolean found = false;
        for (Course c : allCourses) {
            for (User student : c.getEnrolledStudents()) {
                if (student.getName().equalsIgnoreCase(studentName)) {
                    System.out.println("  -> [" + c.getCourseCode() + "] " + c.getCourseName());
                    found = true;
                    break; 
                }
            }
        }
        if (!found) System.out.println("  [!] Student is not enrolled in any active courses.");
    }
}

// --- Main Runner ---
public class Main {
    public static void main(String[] args) {
        // System Data Initialization
        User t1 = new User("Dr. Mohammad Shahidur Rahman", "Teacher");
        User t2 = new User("Sanjoy Das", "Teacher");
        
        User s1 = new User("Fahim", "Student");
        User s2 = new User("Ayesha", "Student");
        User s3 = new User("Rahim", "Student");

        Course algo = new Course("CSE331", "Algorithm Analysis", t1);
        algo.addStudent(s1);
        algo.addStudent(s2);

        Course ml = new Course("CSE401", "Machine Learning", t2);
        ml.addStudent(s1);
        ml.addStudent(s3);

        Schedule uniSchedule = new Schedule();
        uniSchedule.addCourse(algo);
        uniSchedule.addCourse(ml);

        // Interactive Terminal Interface
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("   University Course Management System   ");
        System.out.println("=========================================");

        boolean systemActive = true;
        
        while (systemActive) {
            System.out.println("\nSelect an Operation:");
            System.out.println("  1. Search by Teacher");
            System.out.println("  2. Search by Student");
            System.out.println("  3. Exit System");
            System.out.print("Enter choice (1-3): ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter full Instructor Name: ");
                    String tName = scanner.nextLine();
                    uniSchedule.searchByTeacher(tName);
                    break;
                case "2":
                    System.out.print("Enter Student Name: ");
                    String sName = scanner.nextLine();
                    uniSchedule.searchByStudent(sName);
                    break;
                case "3":
                    System.out.println("Shutting down terminal. Goodbye.");
                    systemActive = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
            }
        }
        scanner.close();
    }
}

```

---

## **FINAL EXERCISE: YouDo 8 - E-Commerce Lite (Lab Version)**

This consolidates object instantiation and basic state management into a final, time-appropriate lab task.

**Requirements:**

1. **`Item` Class:** * Add a `String name` and a `double price`.
* Create a constructor to set these values when the object is created.


2. **`Order` Class:** * Declare two `Item` variables: `item1` and `item2`.
* Create a constructor that takes two `Item` objects and assigns them.
* Create a `void printBill()` method that adds the prices of both items together and prints the total cost.


3. **`Main` Class:**
* Create two `Item` objects (e.g., a "Mouse" for 20.0 and a "Keyboard" for 45.0).
* Create one `Order` object, passing your two items into its constructor.
* Call `printBill()` on the order to see the final output.
