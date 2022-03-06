package de.dhbw.complexCalculator;

public class Main {

    public static void main(String[] args) {

        try {
            ComplexCalculatorGUI complexCalculatorGUI = new ComplexCalculatorGUI();
            complexCalculatorGUI.initializeComplexCalculator();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
