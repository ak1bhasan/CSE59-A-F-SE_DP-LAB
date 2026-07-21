
# Taking Input Using Java `Scanner` Utility

Most interactive Java programs require input from the user. Java provides the **`Scanner`** class (from the `java.util` package) to read data from the keyboard.

Before using `Scanner`, import it:

```java
import java.util.Scanner;
```

Create a `Scanner` object:

```java
Scanner sc = new Scanner(System.in);
```

The object `sc` can now read integers, strings, characters, arrays, and many other types of input.

---

# 1. Taking a Single Integer Input

The `nextInt()` method reads an integer entered by the user.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        System.out.println(number);

        sc.close();
    }
}
```

### Sample Input

```text
25
```

### Sample Output

```text
25
```

### YouDo 1 (Lab Task)

Create a program that reads an integer representing a student's age and prints it.

---

# 2. Taking a Single Word Input

The `next()` method reads a single word. Input stops when a whitespace (space, tab, or newline) is encountered.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String word = sc.next();

        System.out.println(word);

        sc.close();
    }
}
```

### Sample Input

```text
Java
```

### Sample Output

```text
Java
```

### YouDo 2 (Lab Task)

Read a single word representing your favorite programming language and print it.

---

# 3. Taking Multiple Space-Separated Words

Read the number of words first, then use `next()` inside a loop.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        String[] words = new String[n];

        for(int i = 0; i < n; i++){
            words[i] = sc.next();
        }

        for(String word : words){
            System.out.println(word);
        }

        sc.close();
    }
}
```

### Sample Input

```text
4
Java Python C++ Kotlin
```

### Sample Output

```text
Java
Python
C++
Kotlin
```

### YouDo 3 (Lab Task)

Read **5 space-separated colors** and print the **last color**.

---

# 4. Taking a Full Line Input

The `nextLine()` method reads an entire line, including spaces.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String sentence = sc.nextLine();

        System.out.println(sentence);

        sc.close();
    }
}
```

### Sample Input

```text
Java is an object oriented programming language.
```

### Sample Output

```text
Java is an object oriented programming language.
```

### YouDo 4 (Lab Task)

Read a complete sentence and print it.

---

# 5. Taking an Integer Array Input with Size

First read the array size, then use a loop to read each element.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        for(int num : arr){
            System.out.print(num + " ");
        }

        sc.close();
    }
}
```

### Sample Input

```text
5
2 4 6 8 10
```

### Sample Output

```text
2 4 6 8 10
```

### YouDo 5 (Lab Task)

Read an array of **6 integers** and print only the **last element**.

---

# 6. Taking Two Integer Inputs and an Integer Array

Read two integers first, then read an integer array of size `n`.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println(n);
        System.out.println(target);

        for(int num : arr){
            System.out.print(num + " ");
        }

        sc.close();
    }
}
```

### Sample Input

```text
5 10
1 2 3 4 5
```

### Sample Output

```text
5
10
1 2 3 4 5
```

### YouDo 6 (Lab Task)

Take an integer **n**, another integer **target**, and then an integer array of size **n**. Print the target followed by all array elements.

---

# 7. Taking Two Integer Inputs and a String Array

Read two integers first, then read a string array of size `n`.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = sc.nextInt();

        String[] words = new String[n];

        for(int i = 0; i < n; i++){
            words[i] = sc.next();
        }

        System.out.println(n);
        System.out.println(x);

        for(String word : words){
            System.out.print(word + " ");
        }

        sc.close();
    }
}
```

### Sample Input

```text
4 2
Apple Mango Orange Banana
```

### Sample Output

```text
4
2
Apple Mango Orange Banana
```

### YouDo 7 (Lab Task)

Take two integers **n** and **x**, then read **n space-separated country names** and print them in the same order.

---

# 8. Taking a Character Array

Characters are read using `next().charAt(0)`.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        char[] letters = new char[n];

        for(int i = 0; i < n; i++){
            letters[i] = sc.next().charAt(0);
        }

        for(char c : letters){
            System.out.print(c + " ");
        }

        sc.close();
    }
}
```

### Sample Input

```text
5
a b c d e
```

### Sample Output

```text
a b c d e
```

### YouDo 8 (Lab Task)

Read a character array of size **4** and print the **first and last characters**.

---

# 9. Modify a Character Array

Convert characters after input (example: uppercase conversion).

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        char[] letters = new char[n];

        for(int i = 0; i < n; i++){
            letters[i] = sc.next().charAt(0);
        }

        for(int i = 0; i < n; i++){
            letters[i] = Character.toUpperCase(letters[i]);
        }

        for(char c : letters){
            System.out.print(c + " ");
        }

        sc.close();
    }
}
```

### Sample Input

```text
4
a b c d
```

### Sample Output

```text
A B C D
```

### YouDo 9 (Lab Task)

Take a character array and convert every letter to **lowercase** before printing it.

---

# 10. String to Integer and Integer to String Conversion

Java provides built-in methods for type conversion.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        String str = Integer.toString(number);

        int converted = Integer.parseInt(str);

        System.out.println(str);
        System.out.println(converted);

        sc.close();
    }
}
```

### Sample Input

```text
250
```

### Sample Output

```text
250
250
```

### YouDo 10 (Lab Task)

Read an integer, convert it into a string, print its length, then convert it back into an integer.

---

# 11. Taking a Two-Dimensional Integer Array

Read matrix dimensions first, then take input using nested loops.

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
```

### Sample Input

```text
2 3
1 2 3
4 5 6
```

### Sample Output

```text
1 2 3
4 5 6
```

### YouDo 11 (Lab Task)

Write a program that:

- Takes matrix size `m × n`
- Reads an integer matrix
- Prints the **sum of all elements**

---

# FINAL EXERCISE: YouDo 12 - Student Record Input System

This exercise combines multiple `Scanner` concepts into one program.

## Requirements

1. Read student name  
2. Read student ID  
3. Read number of subjects  
4. Store marks in an integer array  
5. Calculate total and average  
6. Print formatted report  

---

### Sample Input

```text
Alice
221-15-1234
4
80 75 90 85
```

---

### Sample Output

```text
========= Student Report =========
Name    : Alice
ID      : 221-15-1234
Subjects: 4
Marks   : 80 75 90 85
Total   : 330
Average : 82.5
==================================
```

---
