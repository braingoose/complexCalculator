package de.dhbw.complexCalculator;

/* Interface soll als Grundlage von verschiedenen mathematischen Objekten darstellen.
* Denkbar sind Klassen wie Brüche, Matrizen, komplexe Zahlen etc. */

public interface MathematicalObject<T> {
    T add(T operand1);
    T sub(T operand);
    T mul(T operand);
    T div(T operand);
}

