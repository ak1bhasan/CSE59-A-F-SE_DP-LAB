# Design Pattern:

Types of Design Patterns in Java

Java design patterns are divided into three categories –

●  Creational,

●  Structural, and

●  Behavioral design patterns.




#  Creational Patterns – Mastering Object Creation

**Objective:** Understand how to decouple object creation from object usage. By the end of this lab, you will be able to construct complex objects step-by-step (Builder) and manage global, thread-safe application states (Singleton).

---

## Part 1: The Builder Pattern

When a class has many attributes (especially optional ones), constructors become messy, unreadable, and hard to manage. This is known as the "Telescoping Constructor" anti-pattern. The Builder Pattern solves this by moving object construction to a separate builder class using method chaining.

### Example 1: HTTP Request Builder (Web Development)

When building API clients, HTTP requests have many optional parts: headers, body, query parameters, etc.

#### ❌ The Anti-Pattern (Telescoping Constructor)

```java
class BadHttpRequest {
    private String url;
    private String method;
    private String headers;
    private String body;

    public BadHttpRequest(String url, String method, String headers, String body) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }
}

public class Main {
    public static void main(String[] args) {
        // Unreadable: What do the nulls mean? If we add more fields, this gets worse.
        BadHttpRequest req = new BadHttpRequest("https://api.com/data", "GET", null, null);
    }
}

```

#### ✅ The Pattern (Builder)

```java
class HttpRequest {
    private final String url;
    private final String method;
    private final String headers;
    private final String body;

    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.body = builder.body;
    }

    public void send() {
        System.out.println("Sending " + method + " request to " + url);
    }

    public static class Builder {
        private String url;
        private String method = "GET"; // Default
        private String headers = "";
        private String body = "";

        public Builder(String url) { this.url = url; }
        public Builder setMethod(String method) { this.method = method; return this; }
        public Builder addHeader(String header) { this.headers += header + ";"; return this; }
        public Builder setBody(String body) { this.body = body; return this; }

        public HttpRequest build() { return new HttpRequest(this); }
    }
}

public class Main {
    public static void main(String[] args) {
        HttpRequest req = new HttpRequest.Builder("https://api.example.com/data")
                             .setMethod("POST")
                             .addHeader("Auth: Bearer token123")
                             .setBody("{'user_id': '404'}")
                             .build();
        req.send();
    }
}

```

### Example 2: Neural Network Configuration (Machine Learning)

Setting up hyperparameters for an ML model often involves dozens of optional settings.

#### ❌ The Anti-Pattern (Messy Setters & Incomplete States)

```java
class BadNeuralNetConfig {
    public int hiddenLayers;
    public double learningRate;
    public String optimizer;
    public int epochs;
    
    // We have to rely on the user remembering to call all setters
}

public class Main {
    public static void main(String[] args) {
        BadNeuralNetConfig config = new BadNeuralNetConfig();
        config.hiddenLayers = 5;
        // Forgot to set optimizer and learning rate! The object is in an invalid state.
        config.epochs = 100;
    }
}

```

#### ✅ The Pattern (Builder)

```java
class NeuralNetConfig {
    private final int hiddenLayers;
    private final double learningRate;
    private final String optimizer;
    private final int epochs;

    private NeuralNetConfig(Builder builder) {
        this.hiddenLayers = builder.hiddenLayers;
        this.learningRate = builder.learningRate;
        this.optimizer = builder.optimizer;
        this.epochs = builder.epochs;
    }

    public void printSummary() {
        System.out.println("Model: " + hiddenLayers + " layers, " + optimizer + ", Epochs: " + epochs);
    }

    public static class Builder {
        private int hiddenLayers = 1;
        private double learningRate = 0.01;
        private String optimizer = "SGD";
        private int epochs = 10;

        public Builder setHiddenLayers(int hiddenLayers) { this.hiddenLayers = hiddenLayers; return this; }
        public Builder setLearningRate(double learningRate) { this.learningRate = learningRate; return this; }
        public Builder setOptimizer(String optimizer) { this.optimizer = optimizer; return this; }
        public Builder setEpochs(int epochs) { this.epochs = epochs; return this; }

        public NeuralNetConfig build() { return new NeuralNetConfig(this); }
    }
}

public class Main {
    public static void main(String[] args) {
        NeuralNetConfig config = new NeuralNetConfig.Builder()
                                     .setHiddenLayers(5)
                                     .setOptimizer("Adam")
                                     .build(); // Automatically uses defaults for unassigned fields
        config.printSummary();
    }
}

```

### Example 3: SQL Query Builder (Database Systems)

Constructing SQL queries using standard string concatenation is error-prone.

#### ❌ The Anti-Pattern (String Concatenation)

```java
public class Main {
    public static void main(String[] args) {
        String columns = "name, age";
        String table = "users";
        String condition = "age > 18";
        
        // Very easy to miss a space or make a syntax error
        String query = "SELECT " + columns + "FROM " + table + " WHERE" + condition; 
        System.out.println(query); // Outputs invalid SQL: SELECT name, ageFROM users WHEREage > 18
    }
}

```

#### ✅ The Pattern (Builder)

```java
class SqlQuery {
    private final String query;

    private SqlQuery(Builder builder) {
        this.query = "SELECT " + builder.select + " FROM " + builder.from + 
                     (builder.where != null ? " WHERE " + builder.where : ";");
    }

    public String getQuery() { return query; }

    public static class Builder {
        private String select = "*";
        private String from;
        private String where;

        public Builder from(String table) { this.from = table; return this; }
        public Builder select(String columns) { this.select = columns; return this; }
        public Builder where(String condition) { this.where = condition; return this; }

        public SqlQuery build() {
            if (this.from == null) throw new IllegalStateException("Error: FROM required.");
            return new SqlQuery(this);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SqlQuery query = new SqlQuery.Builder()
                             .select("name, registration_num")
                             .from("students")
                             .where("batch >= 20")
                             .build();
                             
        System.out.println(query.getQuery()); // Guaranteed safe spacing and syntax
    }
}

```

---

## Part 2: The Singleton Pattern

Sometimes, you only want exactly **one** instance of a class to exist across your entire application. We use a private constructor and a static `getInstance()` method.

### Example 1: Application Configuration Manager

You only want to parse configuration files from the disk once.

#### ❌ The Anti-Pattern (Multiple Instances)

```java
class BadConfigManager {
    public BadConfigManager() {
        System.out.println("Reading heavy config file from disk...");
    }
}

public class Main {
    public static void main(String[] args) {
        // Module A creates an instance
        BadConfigManager config1 = new BadConfigManager();
        
        // Module B creates ANOTHER instance, wasting memory and disk I/O
        BadConfigManager config2 = new BadConfigManager(); 
    }
}

```

#### ✅ The Pattern (Thread-Safe Singleton)

```java
class ConfigurationManager {
    private static volatile ConfigurationManager instance;

    private ConfigurationManager() {
        System.out.println("Reading heavy config file from disk... (Only happens once)");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) { // Double-Checked Locking
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();
        
        System.out.println("Same instance in memory? " + (config1 == config2)); // Output: true
    }
}

```

### Example 2: Deep Learning Model Cache (Resource Management)

Loading a massive model into RAM should only happen once.

#### ❌ The Anti-Pattern (Memory Leak)

```java
class BadModelCache {
    public BadModelCache() {
        System.out.println("Loading 2GB model weights into memory...");
    }
}

public class Main {
    public static void main(String[] args) {
        BadModelCache cache1 = new BadModelCache(); // Uses 2GB RAM
        BadModelCache cache2 = new BadModelCache(); // Uses another 2GB RAM! Server crashes.
    }
}

```

#### ✅ The Pattern (Thread-Safe Singleton)

```java
class ModelWeightsCache {
    private static volatile ModelWeightsCache instance;

    private ModelWeightsCache() {
        System.out.println("Loading 2GB model weights into memory... (Cached successfully)");
    }

    public static ModelWeightsCache getInstance() {
        if (instance == null) {
            synchronized (ModelWeightsCache.class) {
                if (instance == null) {
                    instance = new ModelWeightsCache();
                }
            }
        }
        return instance;
    }

    public void predict(String dataSample) {
        System.out.println("Running inference on: " + dataSample);
    }
}

public class Main {
    public static void main(String[] args) {
        ModelWeightsCache cache1 = ModelWeightsCache.getInstance();
        cache1.predict("image_01.png");

        ModelWeightsCache cache2 = ModelWeightsCache.getInstance();
        cache2.predict("image_02.png"); // Uses the already loaded model
    }
}

```

### Example 3: Centralized Print Spooler (Hardware Access)

If multiple parts of a program send documents to a printer simultaneously, the outputs would mix if they aren't sharing a single queue.

#### ❌ The Anti-Pattern (Decentralized State)

```java
import java.util.LinkedList;
import java.util.Queue;

class BadPrintSpooler {
    private Queue<String> printQueue = new LinkedList<>();

    public void addJob(String doc) { printQueue.add(doc); }
    public void process() { System.out.println("Printing: " + printQueue.poll()); }
}

public class Main {
    public static void main(String[] args) {
        BadPrintSpooler spooler1 = new BadPrintSpooler();
        BadPrintSpooler spooler2 = new BadPrintSpooler();
        
        spooler1.addJob("Doc 1");
        spooler2.addJob("Doc 2");
        
        // They are processing entirely separate queues. The hardware gets confused.
        spooler1.process();
        spooler2.process(); 
    }
}

```

#### ✅ The Pattern (Thread-Safe Singleton)

```java
import java.util.LinkedList;
import java.util.Queue;

class PrintSpooler {
    private static volatile PrintSpooler instance;
    private Queue<String> printQueue;

    private PrintSpooler() {
        printQueue = new LinkedList<>();
    }

    public static PrintSpooler getInstance() {
        if (instance == null) {
            synchronized (PrintSpooler.class) {
                if (instance == null) {
                    instance = new PrintSpooler();
                }
            }
        }
        return instance;
    }

    public void addJob(String document) {
        printQueue.add(document);
        System.out.println("Added to global queue: " + document);
    }

    public void processJobs() {
        System.out.println("--- Processing Unified Queue ---");
        while (!printQueue.isEmpty()) {
            System.out.println("Printing: " + printQueue.poll());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Module A adds a job
        PrintSpooler.getInstance().addJob("Lab_Manual.pdf");
        
        // Module B adds a job
        PrintSpooler.getInstance().addJob("Grades.xlsx");

        // Processing hardware handles them sequentially from one source
        PrintSpooler.getInstance().processJobs();
    }
}

```
