package com.mvilaboa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Calculadora {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n**CALCULADORA**\n");
        Double num1 = getDoubleFromUser(scanner, "Write the first number: ");
        Double num2 = getDoubleFromUser(scanner, "Write the second number: ");
        Operation userOperation = getOperationFromUser(scanner);
        System.out.println("Output: ");
        System.out.println(userOperation.getStringCalculation(num1, num2));
        scanner.close();
    }


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

    public static void showOperations() {
        Stream.of(Operation.values()).forEach(op -> {
            int indexOp = List.of(Operation.values()).indexOf(op) + 1;
            System.out.println(
                "%s. Write '%s' to %s.".formatted(indexOp, op.getSimbol(), op.toString())
            );
        });
    }

    public static Operation getOperationFromUser(Scanner scanner) {
        System.out.println("Pick one of the following valid operations:");
        showOperations();
        while (true) {
            System.out.print("-> ");
            Optional<Operation> operationOpt = Operation.getOperationFromSimbol(
                scanner.nextLine()
            );
            if (operationOpt.isPresent()) return operationOpt.get();
            else System.out.println("== Please, write a valid simbol. ==");

        }
    }
}
