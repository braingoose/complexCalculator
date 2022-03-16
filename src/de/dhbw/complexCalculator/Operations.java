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
        for(Operations operation : Operations.values()) {
            if(operation.operation.equals(operator)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Received a fishy operator: " + operator);
    }

    @Override
    public String display(ComplexNumber firstOperand, ComplexNumber secondOperator) {
        return switch (this) {
            case ANGLE -> "∠" + "(" + firstOperand + ") = " + firstOperand.getAngle();
            case ABS -> "|" + firstOperand + "| = " + firstOperand.getAbs();
            case SIN -> "sin(" + firstOperand + ") = " + firstOperand.sin();
            case COS ->  "cos(" + firstOperand + ") = " + firstOperand.cos();
            case TAN -> "tan(" + firstOperand + ") = " + firstOperand.tan();
            case LOG -> "ln(" + firstOperand + ") = " + firstOperand.logarithm();
            case CEIL -> "⌈" + firstOperand + "⌉ = " + firstOperand.ceil();
            case FLOOR -> "⌊" + firstOperand + "⌋ = " + firstOperand.floor();
            case PERCENTAGE -> "%(" + firstOperand + ") = " + firstOperand.percentage();
            case CONJUGATION -> "(" + firstOperand + ")* = " + firstOperand.conjugate();
            case SQRT -> "√(" + firstOperand + ") = ±(" + firstOperand.sqrt() + ")";
            case RANDOM -> "Random = " + ComplexNumber.random();
            case PLUS -> firstOperand + " + " + secondOperator + " = " + firstOperand.add(secondOperator);
            case MINUS -> firstOperand + " - " + secondOperator + " = " + firstOperand.sub(secondOperator);
            case MUL -> "(" + firstOperand + ") * (" + secondOperator + ")" + " = " + firstOperand.mul(secondOperator);
            case DIV -> "(" + firstOperand + ") / (" + secondOperator + ")" +" = " + firstOperand.div(secondOperator);
            case POWER -> firstOperand + " ^ " + secondOperator + " = " + firstOperand.power(secondOperator);
            case INVERSE -> "1/(" + firstOperand + ") = " + firstOperand.inverse();
            case COSH -> "cosh(" + firstOperand + ") = " + firstOperand.cosh();
            case SINH -> "sinh(" + firstOperand + ") = " + firstOperand.sinh();
            case FACTORIAL -> "(" + firstOperand + ")! = " + firstOperand.factorial();
            default -> throw new UnknownOperatorException("Unknown Operator");
        };
    }

    @Override
    public ComplexNumber calculate(ComplexNumber firstOperand, ComplexNumber secondOperator) {

        return switch (this) {
            case ANGLE -> new ComplexNumber(firstOperand.getAngle(), 0);
            case ABS -> new ComplexNumber(firstOperand.getAbs(), 0);
            case SIN -> firstOperand.sin();
            case COS -> firstOperand.cos();
            case TAN -> firstOperand.tan();
            case LOG -> firstOperand.logarithm();
            case CEIL -> firstOperand.ceil();
            case FLOOR -> firstOperand.floor();
            case PERCENTAGE -> firstOperand.percentage();
            case CONJUGATION -> firstOperand.conjugate();
            case SQRT -> firstOperand.sqrt();
            case RANDOM -> ComplexNumber.random();
            case PLUS -> firstOperand.add(secondOperator);
            case MINUS -> firstOperand.sub(secondOperator);
            case MUL -> firstOperand.mul(secondOperator);
            case DIV -> firstOperand.div(secondOperator);
            case POWER -> firstOperand.power(secondOperator);
            case INVERSE -> firstOperand.inverse();
            case COSH -> firstOperand.cosh();
            case SINH -> firstOperand.sinh();
            case FACTORIAL -> firstOperand.factorial();
            default -> throw new UnknownOperatorException("Unknown Operator");
        };
    }
}

