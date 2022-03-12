package de.dhbw.complexCalculator;

public interface Calculations<T> {

    String display(Operations operator, T firstOperand, T secondOperator);

    T calculate(Operations operator, T firstOperand, T secondOperator);
}
