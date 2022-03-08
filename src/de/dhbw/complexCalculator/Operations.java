package de.dhbw.complexCalculator;

public enum Operations implements Calculations<ComplexNumber> {

    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    ANGLE("∠"),
    ABS("|z|"),
    SIN("sin(z)"),
    COS("cos(z)"),
    TAN("tan(z)"),
    COSH("cosh(z)"),
    SINH("sinh(z)"),
    LOG("ln(z)"),
    CEIL("⌈z⌉"),
    FLOOR("⌊z⌋"),
    PERCENTAGE("%"),
    CONJUGATION("z∗"),
    SQRT("√"),
    RANDOM("Rand"),
    POWER("z1^z2"),
    INVERSE("1/z"),
    FACTORIAL("x!");

    private final String operation;

    Operations(String operation) {
        this.operation = operation;
    }

    // Static hier erlaubt?
    public static Operations stringToOperation(String operator) {
        for (Operations operation : Operations.values()) {
            if (operation.operation.equals(operator)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Received a fishy operator: " + operator);
    }

    @Override
    public String display(Operations operator, ComplexNumber firstOperand, ComplexNumber secondOperator) {
        String resultString = "";

        switch (operator) {
            case ANGLE:
                resultString = "∠" + "(" + firstOperand + ") = " + firstOperand.getAngle();
                break;
            case ABS:
                resultString = "|" + firstOperand + "| = " + firstOperand.getAbs();
                break;
            case SIN:
                resultString = "sin(" + firstOperand + ") = " + firstOperand.sin();
                break;
            case COS:
                resultString = resultString = "cos(" + firstOperand + ") = " + firstOperand.cos();
                break;
            case TAN:
                resultString = resultString = "tan(" + firstOperand + ") = " + firstOperand.tan();
                break;
            case LOG:
                resultString  = "tan(" + firstOperand + ") = " + firstOperand.tan();
                break;
            case CEIL:
                resultString = "⌈" + firstOperand + "⌉ = " + firstOperand.ceil();
                break;
            case FLOOR:
                resultString = "⌊" + firstOperand + "⌋ = " + firstOperand.floor();
                break;
            case PERCENTAGE:
                resultString = "%(" + firstOperand + ") = " + firstOperand.percentage();
                break;
            case CONJUGATION:
                resultString = "(" + firstOperand + ")* = " + firstOperand.conjugate();
                break;
            case SQRT:
                resultString = "√(" + firstOperand + ") = ±(" + firstOperand.sqrt() + ")";
                break;
            case RANDOM:
                resultString = "Random = " + ComplexNumber.random();
                break;
            case PLUS:
                resultString = firstOperand + " + " + secondOperator + " = " + firstOperand.add(secondOperator);
                break;
            case MINUS:
                resultString = firstOperand + " - " + secondOperator + " = " + firstOperand.sub(secondOperator);
                break;
            case MUL:
                resultString = firstOperand + " * " + secondOperator + " = " + firstOperand.mul(secondOperator);
                break;
            case DIV:
                resultString = firstOperand + " / " + secondOperator + " = " + firstOperand.div(secondOperator);
                break;
            case POWER:
                resultString = firstOperand + " ^ " + secondOperator + " = " + firstOperand.power(secondOperator);
                break;
            case INVERSE:
                resultString = "1/(" + firstOperand  + ") = " + firstOperand.inverse();
                break;
            case COSH:
                resultString = "cosh(" + firstOperand + ") = " + firstOperand.cosh();
                break;
            case SINH:
                resultString = resultString = "sinh(" + firstOperand + ") = " + firstOperand.sinh();
                break;
            case FACTORIAL:
                resultString =  "(" + firstOperand + ")! = " + firstOperand.factorial();
                break;
            default:
                throw new EmptyArgumentExcecption("Don't press \"=\" when Screen is empty");
        }
        return resultString;
    }

    @Override
    public ComplexNumber calculate(Operations operator, ComplexNumber firstOperand, ComplexNumber secondOperator) {
        ComplexNumber complexResult = new ComplexNumber(0, 0);

        switch (operator) {
            case ANGLE:
                complexResult = new ComplexNumber(firstOperand.getAngle(), 0);
                break;
            case ABS:
                complexResult = new ComplexNumber(firstOperand.getAbs(), 0);
                break;
            case SIN:
                complexResult = firstOperand.sin();
                break;
            case COS:
                complexResult = firstOperand.cos();
                break;
            case TAN:
                complexResult = firstOperand.tan();
                break;
            case LOG:
                complexResult = firstOperand.logarithm();
                break;
            case CEIL:
                complexResult = firstOperand.ceil();
                break;
            case FLOOR:
                complexResult = firstOperand.floor();
                break;
            case PERCENTAGE:
                complexResult = firstOperand.percentage();
                break;
            case CONJUGATION:
                complexResult = firstOperand.conjugate();
                break;
            case SQRT:
                complexResult = firstOperand.sqrt();
                break;
            case RANDOM:
                complexResult = firstOperand.random();
                break;
            case PLUS:
                complexResult = firstOperand.add(secondOperator);
                break;
            case MINUS:
                complexResult = firstOperand.sub(secondOperator);
                break;
            case MUL:
                complexResult = firstOperand.mul(secondOperator);
                break;
            case DIV:
                complexResult = firstOperand.div(secondOperator);
                break;
            case POWER:
                complexResult = firstOperand.power(secondOperator);
                break;
            case INVERSE:
                complexResult = firstOperand.inverse();
                break;
            case COSH:
                complexResult = firstOperand.cosh();
                break;
            case SINH:
                complexResult = firstOperand.sinh();
                break;
            case FACTORIAL:
                complexResult = firstOperand.factorial();
                break;
            default:
                throw new EmptyArgumentExcecption("Don't press \"=\" when Screen is empty");
        }
        return complexResult;
    }
}

