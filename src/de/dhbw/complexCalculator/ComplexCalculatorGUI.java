package de.dhbw.complexCalculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Taschenrechner für komplexe Zahlen.
 * Der Nutzer hat die Möglichkeit über Buttons Operanden sowie
 * Operationen einzugeben.
 */
public class ComplexCalculatorGUI extends JFrame implements ActionListener {

    private ComplexNumber firstComplexOperand = new ComplexNumber(0, Double.NaN);
    private ComplexNumber secondComplexOperand = new ComplexNumber(0, Double.NaN);
    private ComplexNumber complexResult = new ComplexNumber(0, Double.NaN);

    // Operator
    private boolean onlyOneOperator = false;
    //Erlaubt?
    private ComplexNumbersOperations operator = ComplexNumbersOperations.RANDOM;

    // Java Swing Komponenten
    // Panel in Felder aufteilen
    private final JPanel calculatorPanel = new JPanel(new BorderLayout());

    // Panel für die Buttons
    private final JPanel buttonPanel = new JPanel(new GridLayout(Constants.BUTTON_ROWS,
            Constants.BUTTON_COLUMNS, Constants.BUTTON_GAP, Constants.BUTTON_GAP));
    //Ausgabescreen
    private final JTextField resultTextField = new JTextField(Constants.SCREEN_COLUMNS);

    // Buttons
    private final JButton[] calculatorButtons = new JButton[Constants.BUTTON_AMOUNT];

    // Bildschirm des Rechners
    private final ScreenPanel screenPanel = new ScreenPanel(new BorderLayout());

    // Icon
    private final ImageIcon imageIcon = new ImageIcon(Constants.LOGO_PATH);

    // Gedrückten Button auswerten
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pressedButton = e.getActionCommand();

            switch (pressedButton) {
                case "CE":
                    screenPanel.setScreenText("");
                    break;
                case "<-":
                    if (!screenPanel.getScreenText().isEmpty()) {
                        screenPanel.setScreenText(screenPanel.getScreenText().substring(0,
                                screenPanel.getScreenText().length() - 1));
                    }
                    break;
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case ".":
                    screenPanel.setScreenText(screenPanel.getScreenText() + pressedButton);
                    break;
                case "i":
                    if(!screenPanel.getScreenText().contains("i")) {
                        screenPanel.setScreenText(screenPanel.getScreenText() + pressedButton);
                    }
                    break;
                case "π":
                    screenPanel.setScreenText(screenPanel.getScreenText() + Constants.PI);
                    break;
                case "e":
                    screenPanel.setScreenText(screenPanel.getScreenText() + Constants.EULERS_NUMBER);
                    break;
                case "∠":
                case "|z|":
                case "sin(z)":
                case "cos(z)":
                case "tan(z)":
                case "ln(z)":
                case "⌈z⌉":
                case "⌊z⌋":
                case "%":
                case "z∗":
                case "Rand":
                case "x!":
                case "√":
                case "1/z":
                case "sinh(z)":
                case "cosh(z)":
                    evaluateOperator(true, pressedButton);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "z1^z2":
                    evaluateOperator(false, pressedButton);
                    break;
                case "+/-":
                    if (!screenPanel.getScreenText().isEmpty()) {
                        firstComplexOperand.convert(screenPanel.getScreenText());
                        firstComplexOperand.setRealPart(-1 * firstComplexOperand.getRealPart());
                        screenPanel.setScreenText(String.valueOf(firstComplexOperand));
                    }
                    break;
                case "ℂ: +/-":
                    setSignOfImaginaryPart();
                    break;
                case "=":
                    if (!onlyOneOperator) {
                        secondComplexOperand.convert(screenPanel.getScreenText());
                    }
                    complexResult = operator.calculate(firstComplexOperand, secondComplexOperand);
                    printComplexResult();
                    break;
                default:
                    throw new IllegalArgumentException("Something went wrong.");
            }
        } catch (Exception ex) {
            screenPanel.setScreenText("");
            resultTextField.setText("");
            firstComplexOperand = new ComplexNumber(Double.NaN, Double.NaN);
            secondComplexOperand = new ComplexNumber(Double.NaN, Double.NaN);
            complexResult = new ComplexNumber(Double.NaN, Double.NaN);
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void initializeComplexCalculator() {
        //Titel + Icon
        setTitle(Constants.TITLE);
        setIconImage(imageIcon.getImage()); //Anzeige bearbeiten

        // Panels bearbeiten
        calculatorPanel.setBackground(Color.BLACK);
        buttonPanel.setBackground(Color.GRAY);

        // Ausgabe
        resultTextField.setBackground(Color.WHITE);

        //Buttons initialisieren
        for (int k = 0; k < calculatorButtons.length; k++) {

            calculatorButtons[k] = new JButton(Constants.BUTTON_SYMBOLS[k]);
            calculatorButtons[k].setOpaque(false);
            calculatorButtons[k].setBorderPainted(true);
            calculatorButtons[k].setBorder(new LineBorder(Color.WHITE));
            calculatorButtons[k].setBackground(Color.GRAY);
            calculatorButtons[k].setForeground(Color.WHITE);
            calculatorButtons[k].addActionListener(this);
            buttonPanel.add(calculatorButtons[k]);
        }

        //Komponenten auf das Panel
        screenPanel.initializeScreenPanel();
        calculatorPanel.add(screenPanel, BorderLayout.NORTH);
        calculatorPanel.add(buttonPanel, BorderLayout.CENTER);
        calculatorPanel.add(resultTextField, BorderLayout.SOUTH);

        //Calc Panel auf Frame
        add(calculatorPanel);
        setSize(Constants.CALCULATOR_WINDOW_WIDTH, Constants.CALCULATOR_WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void evaluateOperator(boolean oneOperator, String selectedOperator) {
        operator = ComplexNumbersOperations.stringToOperation(selectedOperator);
        onlyOneOperator = oneOperator;
        firstComplexOperand.convert(screenPanel.getScreenText());
        screenPanel.setScreenText("");
    }

    private void printComplexResult() {
        screenPanel.setScreenText(String.valueOf(complexResult));
        resultTextField.setText(operator.getTerm(firstComplexOperand, secondComplexOperand));
    }

    // Vorzeichen für den Imaginärteil setzen
    private void setSignOfImaginaryPart() {
        if (!screenPanel.getScreenText().isEmpty()) {
            if (screenPanel.getScreenText().charAt(0) == '-') {
                if(screenPanel.getScreenText().equals("- i")) {
                    screenPanel.setScreenText("i");
                } else if (!screenPanel.getScreenText().contains("+") && !screenPanel.getScreenText().substring(1).contains("-")) {
                    screenPanel.setScreenText(screenPanel.getScreenText() + " + ");
                } else if (screenPanel.getScreenText().substring(1).contains("-")) {
                    screenPanel.setScreenText("-" + screenPanel.getScreenText().substring(1).replace(" - ", " + "));
                } else {
                    screenPanel.setScreenText("-" + screenPanel.getScreenText().substring(1).replace(" + ", " - "));
                }
            } else {
                if (!screenPanel.getScreenText().contains("+") && !screenPanel.getScreenText().contains("-")) {
                    if(screenPanel.getScreenText().charAt(0) == 'i') {
                        screenPanel.setScreenText("- i");
                    } else {
                        screenPanel.setScreenText(screenPanel.getScreenText() + " + ");
                    }
                } else if (screenPanel.getScreenText().contains("-")) {
                    screenPanel.setScreenText(screenPanel.getScreenText().replace(" - ", " + "));
                } else {
                    screenPanel.setScreenText(screenPanel.getScreenText().replace(" + ", " - "));
                }
            }
        }
    }
}
