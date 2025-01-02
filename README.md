# Calculator

## Table of Contents

1. [Description](#description)
2. [How I solved the project](#how-i-solved-the-project)
3. [What I learned](#what-i-learned)

## Description

This is a simple calculator program written in Java. It allows users to perform basic arithmetic operations like addition, subtraction, multiplication, division, and exponentiation through a command-line interface. The user is prompted to enter two numbers and choose an operation to perform, after which the result is displayed.

## How I solved the project

To ensure the calculator is scalable and maintainable, I used **Enums** to represent the available operations. Each operation is defined as an enum constant with its corresponding logic. This approach allows new operations to be added easily without changing the rest of the program.

### Operations with Enums

Each operation in the `Operation` enum is paired with a **functional interface** (`BiFunction<Number, Number, Double>`) to perform the calculation. For example, the addition operation is defined as follows:

```java
ADD("+", (num1, num2) -> num1.doubleValue() + num2.doubleValue())
```

Here, the enum constant `ADD` is associated with a lambda expression that defines how the addition is performed. The benefit of using this pattern is that the logic for each operation is encapsulated within the enum constant, making the code modular and easy to extend.

### Handling User Input

To ensure that the user provides valid numeric inputs, the method `getDoubleFromUser()` is used. It continuously prompts the user until a valid number is entered. This is achieved by wrapping the input inside a `try-catch` block:

```java
public static Double getDoubleFromUser(Scanner scanner, String petition) {
    while (true) {
        try {
            System.out.print(petition);
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("== Please, write a valid number. ==");
        }
    }
}
```

This method provides a user-friendly way of handling invalid inputs without breaking the program.

### Displaying Operations

The available operations are displayed using the `showOperations()` method, which iterates through all the enum constants and prints them along with their symbolic representation and description. This allows the user to see all available operations before making a selection.

```java
public static void showOperations() {
    Stream.of(Operation.values()).forEach(op -> {
        int indexOp = List.of(Operation.values()).indexOf(op) + 1;
        System.out.println(
            "%s. Write '%s' to %s.".formatted(indexOp, op.getSimbol(), op.toString())
        );
    });
}
```

### User-Selected Operation

After the user is prompted to pick an operation, the program uses `Optional<Operation>` to safely handle the user input and ensure that a valid operation symbol is entered. This prevents issues related to invalid symbols:

```java
public static Operation getOperationFromUser(Scanner scanner) {
    while (true) {
        System.out.print("-> ");
        Optional<Operation> operationOpt = Operation.getOperationFromSimbol(scanner.nextLine());
        if (operationOpt.isPresent()) return operationOpt.get();
        else System.out.println("== Please, write a valid symbol. ==");
    }
}
```

## What I learned

### **Using Enums in Java**

The project provided valuable insights into how **Enums** can be used to represent constants that have behavior associated with them. In this case, each operation in the calculator is an enum constant that encapsulates both the operation symbol and the logic to perform the calculation. This makes the code cleaner, more maintainable, and less error-prone.

### **Scalability and Extendibility**

The use of **Enums** makes the calculator highly extendable. To add a new operation (e.g., modulus or square root), you simply need to add a new enum constant with the corresponding logic, without modifying the rest of the code.

For example, adding a "modulus" operation would look like this:

```java
MODULUS("%", (num1, num2) -> num1.doubleValue() % num2.doubleValue())
```

This encapsulation ensures that adding new functionality is straightforward and does not require modifying other parts of the program.

### **Optional and Null Safety**

By using **`Optional<Operation>`**, the program safely handles user input. If an invalid symbol is entered, the program keeps prompting the user until a valid one is provided. This eliminates the possibility of null references and makes the program more robust.

### **Functional Programming with BiFunction**

Using **`BiFunction<Number, Number, Double>`** to define the operation logic leverages Java's functional programming capabilities. This pattern ensures that each operation is defined in a clean and reusable way, minimizing code duplication and improving the modularity of the program.

### **Error Handling (Division by Zero)**

A custom exception, `DivisionByZeroException`, was implemented to handle cases where division by zero is attempted. This ensures that the program does not crash and provides the user with a clear error message:

```java
public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
```

This exception handling improves the user experience by preventing runtime errors from disrupting the flow of the program.

In conclusion, this project reinforced key Java concepts like Enums, functional interfaces, and error handling, and provided valuable lessons in building scalable, maintainable applications.