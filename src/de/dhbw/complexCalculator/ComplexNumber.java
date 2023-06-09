package de.dhbw.complexCalculator;

import java.util.Random;

/**
 * Stellt eine komplexe Zahl dar.
 * Ermöglicht einige Berechnungen mit komplexen Zahlen.
 */
final public class ComplexNumber implements MathematicalObject<ComplexNumber> {

    private double realPart;
    private double imaginaryPart;

    public ComplexNumber(double realPart, double complexPart) {
        this.realPart = realPart;
        this.imaginaryPart = complexPart;
    }

    public String toString() {
        if (realPart == 0.0 && imaginaryPart == 0.0) {
            return "0.0";
        } else if (realPart == 0.0) {
            return imaginaryPart + "i";
        } else if (imaginaryPart == 0.0) {
            return realPart + "";
        } else if (imaginaryPart < 0) {
            return realPart + " - " + Math.abs(imaginaryPart) + "i";
        } else if (Double.isNaN(realPart) || Double.isNaN(imaginaryPart)
                || Double.isInfinite(realPart) || Double.isInfinite(imaginaryPart)) {
            throw new IllegalComplexNumberException("No complex number");
        } else {
            return realPart + " + " + imaginaryPart + "i";
        }
    }

    //Inspiriert durch: https://stackoverflow.com/questions/20248695/regex-for-dividing-complex-number-string-in-java
    public void convert(String complexNumberString) {
        try {
            complexNumberString = complexNumberString.replaceAll("\\s+", ""); // Leerzeichen entfernen
            double tmpRealPart = 0, tmpImaginaryPart = 0;

            if (complexNumberString.equals("i")) {
                tmpRealPart = 0.0;
                tmpImaginaryPart = 1.0;
            } else if (complexNumberString.equals("NaN")) {
                throw new IllegalComplexNumberException("Something went wrong during the conversation");
            } else {
                String[] complexNumberParts = complexNumberString.split("[+-]");
                int pos = -1;

                for (String complexPart : complexNumberParts) {
                    if (pos != -1) {
                        complexPart = complexNumberString.charAt(pos) + complexPart;
                    } else {
                        pos = 0;
                        if("".equals(complexPart)) {
                            continue;
                        }
                    }

                    pos += complexPart.length();
                    if (complexPart.lastIndexOf('i') == -1) {
                        if (!"+".equals(complexPart) && !"-".equals(complexPart)) {
                            tmpRealPart += Double.parseDouble(complexPart);
                        }
                    } else {
                        complexPart = complexPart.replace("i", "");
                        if("+".equals(complexPart)) {
                            tmpImaginaryPart++;
                        } else if("-".equals(complexPart)) {
                            tmpImaginaryPart = -1;
                        } else {
                            tmpImaginaryPart += Double.parseDouble(complexPart);
                        }
                    }
                }
            }
            realPart = tmpRealPart;
            imaginaryPart = tmpImaginaryPart;
        } catch(Exception e) {
            throw new IllegalComplexNumberException(e.getMessage());
        }
    }

    @Override
    public ComplexNumber add(ComplexNumber operand1) {
        return new ComplexNumber(realPart + operand1.realPart,
                imaginaryPart + operand1.imaginaryPart);
    }

    @Override
    public ComplexNumber sub(ComplexNumber operand) {
        return new ComplexNumber(realPart - operand.realPart,
                imaginaryPart - operand.imaginaryPart);
    }

    @Override
    public ComplexNumber mul(ComplexNumber operand) {
        return new ComplexNumber(realPart * operand.realPart - imaginaryPart * operand.imaginaryPart,
                realPart * operand.imaginaryPart + imaginaryPart * operand.realPart);
    }

    @Override
    public ComplexNumber div(ComplexNumber operand) {
        double num = (realPart * operand.realPart + imaginaryPart * operand.imaginaryPart) /
                (operand.realPart * operand.realPart + operand.imaginaryPart * operand.imaginaryPart);
        double den = (imaginaryPart * operand.realPart - realPart * operand.imaginaryPart) /
                (operand.realPart * operand.realPart + operand.imaginaryPart * operand.imaginaryPart);
        if (Double.isNaN(num) && Double.isNaN(den)) {
            throw new IllegalComplexNumberException("Do not divide by zero");
        } else {
            return new ComplexNumber(num, den);
        }
    }

    public ComplexNumber logarithm() {
        return new ComplexNumber(Math.log(getAbs()), getAngle());
    }

    public ComplexNumber sin() {
        return new ComplexNumber((float) (Math.sin(realPart) * Math.cosh(imaginaryPart)),
                (float) (Math.cos(realPart) * Math.sinh(imaginaryPart)));
    }

    public ComplexNumber cos() {
        return new ComplexNumber((float) (Math.cos(realPart) * Math.cosh(imaginaryPart)),
                (float) (-1 * Math.sin(realPart) * Math.sinh(imaginaryPart)));
    }

    public ComplexNumber tan() {
        return (sin()).div(cos());
    }

    public ComplexNumber sinh() {
        return new ComplexNumber(Math.sinh(realPart) * Math.cos(imaginaryPart),
                Math.cosh(realPart) * Math.sin(imaginaryPart));
    }

    public ComplexNumber cosh() {
        return new ComplexNumber(Math.cosh(realPart) * Math.cos(imaginaryPart),
                Math.sinh(realPart) * Math.sin(imaginaryPart));
    }

    public ComplexNumber ceil() {
        return new ComplexNumber(Math.ceil(realPart), Math.ceil(imaginaryPart));
    }

    public ComplexNumber floor() {
        return new ComplexNumber(Math.floor(realPart), Math.floor(imaginaryPart));
    }

    public double getAngle() {
        double phaseAngle;
        //Fallunterscheidung der Winkel
        if(imaginaryPart == 0 && realPart < 0 ) {
            phaseAngle = Constants.PI;
        } else if (realPart == 0 && imaginaryPart < 0) {
            phaseAngle =  3 * Constants.PI / 2;
        } else if (realPart == 0 && imaginaryPart > 0) {
            phaseAngle = Constants.PI / 2;
        } else if (realPart == 0) {
            phaseAngle = 0;
        } else if(realPart > 0 && imaginaryPart > 0) {
            phaseAngle = Math.atan(imaginaryPart / realPart) + 2 * Constants.PI;
        } else if (realPart < 0) {
            phaseAngle = Math.atan(imaginaryPart / realPart) + Constants.PI;
        } else {
            phaseAngle = Math.atan(imaginaryPart / realPart);
        }
        return phaseAngle;
    }

    public double getAbs() {
        double a = realPart * realPart;
        double b = imaginaryPart * imaginaryPart;
        return Math.sqrt(a + b);
    }

    public ComplexNumber power(ComplexNumber operand1) {
        if (realPart == 0 && imaginaryPart == 0) {
            return new ComplexNumber(0,0);
        }

        ComplexNumber a = new ComplexNumber(Math.pow(Constants.EULERS_NUMBER,
                operand1.realPart * Math.log(getAbs()) - operand1.imaginaryPart * getAngle()),
                0);
        ComplexNumber b = new ComplexNumber(Math.cos(operand1.imaginaryPart * Math.log(getAbs()) + operand1.realPart * getAngle()),
                Math.sin(operand1.imaginaryPart * Math.log(getAbs()) + operand1.realPart * getAngle()));
        return a.mul(b);
    }

    public ComplexNumber sqrt() {
        if (realPart < 0 && imaginaryPart == 0) {
            return new ComplexNumber(0, Math.sqrt((getAbs())));
        } else {
            return new ComplexNumber(Math.sqrt((getAbs() + realPart) / 2),
                    Math.signum(imaginaryPart) * Math.sqrt((getAbs() - realPart) / 2));
        }
    }

    public static ComplexNumber random() {
        Random random = new Random();
        return new ComplexNumber(random.nextDouble(), random.nextDouble());
    }

    public ComplexNumber factorial() throws IllegalArgumentException {
        ComplexNumber result;
        SpecialMathematicalFunctions gammaFunction = new SpecialMathematicalFunctions();

        if (imaginaryPart == 0.0) {
            result = new ComplexNumber(gammaFunction.gammaFunction(realPart), 0);
            if (realPart == 0) {
                result.setRealPart(1);
                return result;
            } else if (!Double.isNaN(result.realPart)) {
                return new ComplexNumber(realPart * result.realPart, 0);
            } else {
                throw new IllegalArgumentException("Result is out of bounds");
            }
        } else {
            throw new IllegalArgumentException("Gammafunction is not implemented for complex values");
        }
    }

    public ComplexNumber percentage() {
        return new ComplexNumber(realPart / 100.0, imaginaryPart / 100.0);
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(realPart, -imaginaryPart);
    }

    public ComplexNumber inverse() {
        return new ComplexNumber(1,0).div(this);
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getComplexPart() {
        return imaginaryPart;
    }

    public void setComplexPart(double complexPart) {
        this.imaginaryPart = complexPart;
    }
}

