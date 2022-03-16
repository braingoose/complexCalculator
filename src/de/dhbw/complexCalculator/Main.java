package de.dhbw.complexCalculator;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            ComplexCalculatorGUI complexCalculatorGUI = new ComplexCalculatorGUI();
            complexCalculatorGUI.initializeComplexCalculator();

            ComplexNumber z1 = new ComplexNumber(0, 0.3333333333333);
            ComplexNumber res = z1.mul(new ComplexNumber(3,0));
            System.out.println(res);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
