package de.dhbw.complexCalculator;

public interface Calculations<T> {

    String getTerm(T firstOperand, T secondOperator);
    T calculate(T firstOperand, T secondOperator);
}
