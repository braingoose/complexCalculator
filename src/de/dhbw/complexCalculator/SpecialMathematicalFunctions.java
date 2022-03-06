package de.dhbw.complexCalculator;

public class SpecialMathematicalFunctions {

    // Inspiriert durch: https://rosettacode.org/wiki/Gamma_function#Java

    public double gammaFunction(double x){

        if (x < 0.5) {
            return Constants.PI / (Math.sin(Constants.PI * x) * gammaFunction(1 - x));
        }

        x -= 1;
        double a = Constants.COEFFICIENTS_GAMMA_FUNCTION[0];
        double t = x + Constants.G_GAMMA_FUNCTION + 0.5;

        for (int k = 1; k < Constants.COEFFICIENTS_GAMMA_FUNCTION.length; k++){
            a += Constants.COEFFICIENTS_GAMMA_FUNCTION[k] / (x + k);
        }

        return Math.sqrt(2 * Constants.PI) * Math.pow(t, x + 0.5) * Math.exp(-t) * a;
    }
    // Platz für weitere besondere Funktionen
}
