package de.dhbw.complexCalculator;

public interface Calculations<T> {

    String display(T firstOperand, T secondOperator);

    T calculate(T firstOperand, T secondOperator);
}
