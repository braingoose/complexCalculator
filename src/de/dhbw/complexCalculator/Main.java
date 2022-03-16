package de.dhbw.complexCalculator;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            ComplexCalculatorGUI complexCalculatorGUI = new ComplexCalculatorGUI();
            complexCalculatorGUI.initializeComplexCalculator();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
