package de.dhbw.complexCalculator;

public class Constants {

    public static final String[] BUTTON_SYMBOLS = {
            "CE", "<-", "%", "/", "√", "sin(z)", "1/z", "Rand",
            "7", "8", "9", "*", "⌈z⌉", "cos(z)", "z1^z2", "ln(z)",
            "4", "5", "6", "-", "⌊z⌋", "tan(z)", "∠", "π",
            "1", "2", "3", "+", "x!", "sinh(z)", "i", "e",
            "0", ".", "+/-", "=", "|z|", "cosh(z)", "ℂ: +/-", "z∗"
    };

    public static final int BUTTON_ROWS = 5;
    public static final int BUTTON_COLUMNS = 7;
    public static final int BUTTON_AMOUNT = BUTTON_COLUMNS * BUTTON_ROWS + BUTTON_ROWS;
    public static final int BUTTON_GAP = 2;

    public static final int SCREEN_ROWS = 5;
    public static final int SCREEN_COLUMNS = 100;

    public static final String LOGO_PATH = "pics/complexCalculatorLogo.png";
    public static final String TITLE = "Complex Calculator";

    public static final double PI = Math.PI;
    public static final double EULERS_NUMBER = Math.E;

    public static final int CALCULATOR_WINDOW_HEIGHT = 600;
    public static final int CALCULATOR_WINDOW_WIDTH = 400;

    public static final int SCREEN_FONT_SIZE = 16;
    public static final int RAD_LABEL_FONT_SIZE = 12;

    // https://mrob.com/pub/ries/lanczos-gamma.html
    public static final double[] COEFFICIENTS_GAMMA_FUNCTION = {
            0.99999999999980993, 676.5203681218851, -1259.1392167224028,
            771.32342877765313, -176.61502916214059, 12.507343278686905,
            -0.13857109526572012, 9.9843695780195716e-6, 1.5056327351493116e-7
    };

    public static final int G_GAMMA_FUNCTION = 7;
}
