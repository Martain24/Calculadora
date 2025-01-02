package com.mvilaboa;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum Operation {
    ADD("+", (num1, num2) -> num1.doubleValue() + num2.doubleValue()),
    SUBSTRACT("-", (num1, num2) -> num1.doubleValue() - num2.doubleValue()),
    MULTIPLY("*", (num1, num2) -> num1.doubleValue()*num2.doubleValue()),
    DIVIDE("/", (num1, num2) -> {
        if (num2.doubleValue() != 0) return num1.doubleValue()/num2.doubleValue();
        else throw new DivisionByZeroException("Division by zero is not allowed");
    }),
    EXPONENTIATE("**", (num1, num2) -> Math.pow(num1.doubleValue(), num2.doubleValue()));

    private String symbol;
    private BiFunction<Number, Number, Double> functionOperation;

    private Operation(String symbol, BiFunction<Number, Number, Double> functionOperation) {
        this.symbol = symbol;
        this.functionOperation = functionOperation;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public Double calculate(Number num1, Number num2) {
        return this.functionOperation.apply(num1, num2);
    }

    public String getStringCalculation(Number num1, Number num2) {
        return "%s %s %s = %s".formatted(num1, getSymbol(), num2, calculate(num1, num2));
    }

    public static boolean isValidSymbol(String symbol) {
        return Stream.of(Operation.values()).anyMatch(op -> op.getSymbol().equals(symbol));
    }

    public static Optional<Operation> getOperationFromSymbol(String symbol) {
        return Stream.of(Operation.values())
                .filter(op -> op.getSymbol().equals(symbol))
                .findFirst();
    }
}
